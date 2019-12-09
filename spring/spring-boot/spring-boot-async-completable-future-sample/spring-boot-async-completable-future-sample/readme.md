最近在实现一个聚合搜索的需求时，由于需要从五个索引中查询数据，然后再将搜索结果组合返回给前端app展现，显然这个地方不能再用同步的方式来操作了，如果有一个索引查询出现耗时较长，那么其余的请求都会排同步等待这一个慢查询，这个时候就考虑采用线程池+异步任务来实现这个聚合搜索的功能，顺便借助这次异步实现来加强下并发编程的学习。

### 一、SpringBoot中的异步操作
异步操作根据是否有返回值可以派生为Callable、Future两类接口，我们知道在阿里巴巴的开发规约中并不推荐直接从当前线程中实例化一个线程来进行异步操作，这样主要是考虑JVM线程资源是宝贵的开销，线程应当取之于“线程池”，用完即当归还于“线程池”，而线程池的生命周期也是交给Spring容器来托管最佳，如果每个请求都随意的挥霍线程资源，没有一个统一调度的容器池，服务器将不堪重负，因此线程池资源需要首先进行合理配置。

#### 1.配置Springboot线程池
采用外部配置的形式将线程池参数进行初始化，然后注入到Spring容器中。关于自动配置的操作在这篇文章已经说明《[SpringBoot自定义配置的正确使用姿势](https://www.jianshu.com/p/8940d38a126f)》，不在赘述，主要是能够将线程池的配置放到配置文件中，变成可运维可调整的资源。
```java
@Configuration
@EnableAsync
public class AsyncConfig {

    @Autowired
    private ExcutorProperties excutorProperties;

    @Bean
    public Executor taskExecutor() {
        // Spring 默认配置是核心线程数大小为1，最大线程容量大小不受限制，队列容量也不受限制。
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程数
        executor.setCorePoolSize(excutorProperties.getCorePoolSize());
        // 最大线程数
        executor.setMaxPoolSize(excutorProperties.getMaxPoolSize());
        // 队列大小
        executor.setQueueCapacity(excutorProperties.getMaxPoolSize());
        // 当最大池已满时，此策略保证不会丢失任务请求，但是可能会影响应用程序整体性能。
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setThreadNamePrefix("My ThreadPoolTaskExecutor-");
        executor.initialize();
        return executor;
    }
}
```
这里需要注意下ThreadPoolTaskExecutor 饱和策略，有四种方式：
- ThreadPoolExecutor.AbortPolicy：抛出 RejectedExecutionException来拒绝新任务的处理。
- ThreadPoolExecutor.CallerRunsPolicy：调用执行自己的线程运行任务。这种策略会降低对于新任务提交速度，影响程序的整体性能。另外，这个策略喜欢增加队列容量。如果您的应用程序可以承受此延迟并且你不能任务丢弃任何一个任务请求的话，你可以选择这个策略。
- ThreadPoolExecutor.DiscardPolicy： 不处理新任务，直接丢弃掉。
- ThreadPoolExecutor.DiscardOldestPolicy： 此策略将丢弃最早的未处理的任务请求。


### 2.注解异步方法
在刚开始学Java多线程基础的时候，常常是在main方法中实例化一个线程或者Runnable接口的形式来实现异步操作，但是在Spring中，不建议直接在方法内部再去实例化新线程（Thread或Runnable），通常要将需要异步的操作抽象成一个方法，同时给这个方法加上了`@Async`注解来告诉 Spring 它是一个异步的方法。另外，这个方法的返回值 `CompletableFuture.completedFuture(results)`这代表我们需要返回结果，也就是说程序必须把任务执行完成之后再返回给用户。

模拟一个多异步请求的场景，一个搜索接口如下，根据用户输入的内容去反馈搜索结构
```java
public interface ISearchService {
    /**
     * 搜索接口定义
     * @param text 内容
     * @return
     */
    SearchResult search(String text);
}

```
而具体的实现按照类型进行区分，可以分为文本、新闻、图片以及音乐等等类型，还可以继续扩展：

![搜索的具体实现](https://upload-images.jianshu.io/upload_images/8926909-9a7ba93eb22736e6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

每一个类型的搜索由服务本身提供，聚合搜索的调用者并不关心。聚合搜索去异步查询不同接口时为了避免编写大量的if else代码，这里可以采用策略模式，根据传入的类型的服务来实现具体的调用，即便新增搜索接口时也不需要对以下代码重构。同时为了显示声明一个异步操作，查询方法需要加上`@Async`注解即可，返回的结果用`CompletableFuture`包装起来。
```java
@Service
public class AsyncService {

    @Autowired
    private final Map<String, ISearchService> searchServiceMap = new ConcurrentHashMap<>();

    public AsyncService(Map<String, ISearchService> searchServiceMap) {
        this.searchServiceMap.clear();
        searchServiceMap.forEach((k, v)-> this.searchServiceMap.put(k, v));
    }

    /**
     * 异步查询
     * @param type 采用何种查询类型
     * @param text 查询内容
     * @return 将结果放入future中可采用异步回调获取
     */
    @Async
    public CompletableFuture<SearchResult> search(String type, String text){
        SearchResult result = searchServiceMap.get(type).search(text);
        return CompletableFuture.completedFuture(result);
    }

}
```
### 3.异步回调与阻塞等待
异步方法提供以后，就可以在其他服务中进行调用。在聚合查询这里场景是，需要异步并行的去查询多个类型的接口，查完以后组装成一个统一的结果返回客户端，这样类似的开发场景也比较常见，比如需要主线程等待多个线程完成以后才能继续下一阶段的任务，或线程之间相互等待全部完成才弄继续，或某线程的执行依赖另外一个线程的结果，Java对这些场景都提供了很好的支持——JUC并发包，主线程等待多个线程执行可以采用`CountDonwLaunch`、线程相互等待可以使用`CycleBarier`、线程互调可以采用`CompletableFuture`。
此处即是采用的`CompletableFuture`来实现异步回调，并行搜索了每种类型的结果以后，通过CompletableFuture回调函数放到一个线程安全的Map中。因为每种查询类型的耗时不同，只有等最后一个查询结束以后才能放行，这个地方可以用`CompletableFuture.allOf().join()`来实现阻塞等待。
```java
@Service
public class SearchFacade {

    @Resource
    private AsyncService asyncService;

    @Autowired
    private final Map<String, ISearchService> searchServiceMap = new ConcurrentHashMap<>();

    public SearchFacade(Map<String, ISearchService> searchServiceMap) {
        this.searchServiceMap.clear();
        searchServiceMap.forEach((k, v)-> this.searchServiceMap.put(k, v));
    }

    /**
     * 聚合搜索
     * @param context
     * @return
     */
    public SearchResult searchAll(String context){
        log.info("search method begin, context={}", context);
        //聚合结果Map，线程安全
        Map<String, Object> resultMap = new ConcurrentHashMap<>(searchServiceMap.size());
        try{
            //Future集合
            List<CompletableFuture<SearchResult>> futureList = new ArrayList<>();
            //遍历执行异步查询
            for(Map.Entry<String, ISearchService> entry : searchServiceMap.entrySet()){
                String type = entry.getKey();
                CompletableFuture<SearchResult> future = asyncService.search(type, context);
                futureList.add(future);
                //异步回调：聚合搜索结果
                future.thenAccept(searchResult -> resultMap.put(type, searchResult.getData()));
            }
            //阻塞等待最后一个返回
            CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()])).join();
            log.info("<=====search all end=====>\n resultMap = {}, time = {}", resultMap, System.currentTimeMillis());
            return new SearchResult(resultMap);
        }catch (Exception e){
            log.error("method error, e={}", e);
        }
        return new SearchResult(500, "service error");
    }
}
```
**注意：**
1. 多个线程操作同一个变量需考虑线程安全问题，此处组装结果的Map就采用的 `ConcurrentHashMap`结构。
2. JDK1.8中CompletableFuture继承自Future接口，通过get()方法也能获取请求结果，但是为阻塞的。

### 4.测试验证
SpringBoot服务起来后，请求聚合搜索接口，可以看到接口的入口处打印了的NIO线程号——[nio-8080-exec-2]，后面分别从线程池中取了ecutor-thread-1、2、3、4等四个线程来进行了异步搜索操作：

![运行结果图](https://upload-images.jianshu.io/upload_images/8926909-e29bf623212a236d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

在代码中由于是执行完成后才打印的时间，所以搜索查询的耗时有大有小，而且是先完成的先打印，耗时较长的则靠后，直到最后一个查询完成后，整个聚合搜索才算完毕，输出了查询结果。

最后附上Github源码地址：https://github.com/tisonkong/JavaAssemble/tree/master/basic

### 参考列表：
1. SnailClimb.[SpringBoot 异步编程指南](https://www.cnblogs.com/snailclimb/p/11645644.html)
2. 醉眼识朦胧.[使用CompletableFuture优化你的代码执行效率](https://www.cnblogs.com/fingerboy/p/9948736.html)