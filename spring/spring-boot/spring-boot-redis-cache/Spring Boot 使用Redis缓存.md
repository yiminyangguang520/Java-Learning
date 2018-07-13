# Spring Boot 使用Redis缓存

## 缓存存储

Spring 提供了很多缓存管理器，例如：

- SimpleCacheManager
- EhCacheCacheManager
- CaffeineCacheManager
- GuavaCacheManager
- CompositeCacheManager
  这里我们要用的是除了核心的Spring框架之外，Spring Data提供的缓存管理器：**RedisCacheManager**

在Spring Boot中通过@enablecaching注解自动化配置合适的缓存管理器（CacheManager），默认情况下Spring Boot根据下面的顺序自动检测缓存提供者：

- Generic
- JCache (JSR-107)
- EhCache 2.x
- Hazelcast
- Infinispan
- Redis
- Guava
- Simple

但是因为我们之前已经配置了redisTemplate了，Spring Boot无法就无法自动给RedisCacheManager设置redisTemplate了，所以接下来要自己配置CacheManager 。

1. 首先修改RedisConfig配置类，添加@enablecaching注解，并继承CachingConfigurerSupport，重写CacheManager 方法

```
...
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
		redisTemplate.setConnectionFactory(factory);
		redisTemplate.afterPropertiesSet();
		setSerializer(redisTemplate);
		return redisTemplate;
	}

	private void setSerializer(RedisTemplate<String, String> template) {
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(jackson2JsonRedisSerializer);
	}

@Bean
	public CacheManager cacheManager(RedisTemplate redisTemplate) {
		RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
		// 设置缓存过期时间，秒
		rcm.setDefaultExpiration(60);
		return rcm;
	}
...

```

Spring提供了如下注解来声明缓存规则：

> - [@Cacheable](https://github.com/cacheable) triggers cache population
> - @cacheevict triggers cache eviction
> - @cacheput updates the cache without interfering with the method execution
> - [@caching](https://github.com/caching) regroups multiple cache operations to be applied on a method
> - @cacheconfig shares some common cache-related settings at class-level

| 注　　解                                     | 描　　述                                     |
| ---------------------------------------- | ---------------------------------------- |
| [@Cacheable](https://github.com/cacheable) | 表明Spring在调用方法之前，首先应该在缓存中查找方法的返回值。如果这个值能够找到，就会返回缓存的值。否则的话，这个方法就会被调用，返回值会放到缓存之中 |
| @cacheput                                | 表明Spring应该将方法的返回值放到缓存中。在方法的调用前并不会检查缓存，方法始终都会被调用 |
| @cacheevict                              | 表明Spring应该在缓存中清除一个或多个条目                  |
| [@caching](https://github.com/caching)   | 这是一个分组的注解，能够同时应用多个其他的缓存注解                |
| @cacheconfig                             | 可以在类层级配置一些共用的缓存配置                        |

[@Cacheable](https://github.com/cacheable)和@cacheput有一些共有的属性：

| 属　　性      | 类　　型     | 描　　述                                 |
| --------- | -------- | ------------------------------------ |
| value     | String[] | 要使用的缓存名称                             |
| condition | String   | SpEL表达式，如果得到的值是false的话，不会将缓存应用到方法调用上 |
| key       | String   | SpEL表达式，用来计算自定义的缓存key                |
| unless    | String   | SpEL表达式，如果得到的值是true的话，返回值不会放到缓存之中    |

1. 在一个请求方法上加上[@Cacheable](https://github.com/cacheable)注解，测试下效果

```
	@Cacheable(value="testallCache")
	@RequestMapping(value = "/redis/user/{userId}", method = RequestMethod.GET)
	public User getUser(@PathVariable() Integer userId) {
		User user = userService.getUserById(userId);
		return user;
	}

```

1. 然后访问这个请求，控制台就报错啦。

```
java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.String
	at org.springframework.data.redis.serializer.StringRedisSerializer.serialize(StringRedisSerializer.java:33)
	at org.springframework.data.redis.cache.RedisCacheKey.serializeKeyElement(RedisCacheKey.java:74)
	at org.springframework.data.redis.cache.RedisCacheKey.getKeyBytes(RedisCacheKey.java:49)
	at org.springframework.data.redis.cache.RedisCache$1.doInRedis(RedisCache.java:176)
	at org.springframework.data.redis.cache.RedisCache$1.doInRedis(RedisCache.java:172)
	at org.springframework.data.redis.core.RedisTemplate.execute(RedisTemplate.java:207)

```

原因如下：
先看一下Redis缓存默认的Key生成策略

> - If no params are given, return SimpleKey.EMPTY.
> - If only one param is given, return that instance.
> - If more the one param is given, return a SimpleKey containing all parameters.

从上面的生成策略可以知道，上面的缓存testallCache使用的key是整形的userId参数，但是我们之前在redisTemplate里设置了template.setKeySerializer(new StringRedisSerializer());，所以导致类型转换错误。虽然也可以使用SpEL表达式生成Key（详见[这里](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/cache.html#cache-spel-context)），但是返回结果还是需要是string类型（比如#root.methodName就是，#root.method就不是），更通用的办法是重写keyGenerator定制Key生成策略。

1. 修改RedisConfig类，重写keyGenerator方法：

```
	@Bean
	public KeyGenerator keyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object target, Method method, Object... params) {
				StringBuilder sb = new StringBuilder();
				sb.append(target.getClass().getName());
				sb.append(":" + method.getName());
				for (Object obj : params) {
					sb.append(":" + obj.toString());
				}
				return sb.toString();
			}
		};
	}

```

1. 再次进行刚才的请求（分别以1,2作为userId参数），浏览器结果如下图：
   [![image](https://user-images.githubusercontent.com/24689696/28450188-5702cddc-6e18-11e7-9613-693b8a56dbb0.png)](https://user-images.githubusercontent.com/24689696/28450188-5702cddc-6e18-11e7-9613-693b8a56dbb0.png)
   [![image](https://user-images.githubusercontent.com/24689696/28450181-4889f654-6e18-11e7-87b4-a2e86e4464d7.png)](https://user-images.githubusercontent.com/24689696/28450181-4889f654-6e18-11e7-87b4-a2e86e4464d7.png)
   使用redisclient工具查看下：
   [![image](https://user-images.githubusercontent.com/24689696/28450229-868e9a18-6e18-11e7-89c3-e0efce33b5f5.png)](https://user-images.githubusercontent.com/24689696/28450229-868e9a18-6e18-11e7-89c3-e0efce33b5f5.png)
   [![image](https://user-images.githubusercontent.com/24689696/28450272-d26193f0-6e18-11e7-83bf-743a196f024d.png)](https://user-images.githubusercontent.com/24689696/28450272-d26193f0-6e18-11e7-83bf-743a196f024d.png)
   可以看到Redis里保存了：

- 两条string类型的键值对：key就是上面方法生成的结果，value就是user对象序列化成json的结果
- 一个有序集合：其中key为[@Cacheable](https://github.com/cacheable)里的value+~keys，分数为0，成员为之前string键值对的key

这时候把userId为1的用户的username改为ansel（原来是ansel1），再次进行<https://localhost:8443/redis/user/1> 请求，发现浏览器返回结果仍是ansel1，证明确实是从Redis缓存里返回的结果。
[![image](https://user-images.githubusercontent.com/24689696/28450526-637a9002-6e1a-11e7-9de8-0bb896834878.png)](https://user-images.githubusercontent.com/24689696/28450526-637a9002-6e1a-11e7-9de8-0bb896834878.png)
[![image](https://user-images.githubusercontent.com/24689696/28450627-0a35e91e-6e1b-11e7-9a21-e885a1a66d50.png)](https://user-images.githubusercontent.com/24689696/28450627-0a35e91e-6e1b-11e7-9a21-e885a1a66d50.png)

## 缓存更新与删除

1. 更新与删除Redis缓存需要用到@cacheput和@cacheevict。这时候我发现如果使用上面那种key的生成策略，以用户为例：它的增删改查方法无法保证生成同一个key（方法名不同，参数不同），所以修改一下keyGenerator，使其按照缓存名称+userId方式生成key：

```
	@Bean
	public KeyGenerator keyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object target, Method method, Object... params) {
				StringBuilder sb = new StringBuilder();
				String[] value = new String[1];
				// sb.append(target.getClass().getName());
				// sb.append(":" + method.getName());
				Cacheable cacheable = method.getAnnotation(Cacheable.class);
				if (cacheable != null) {
					value = cacheable.value();
				}
				CachePut cachePut = method.getAnnotation(CachePut.class);
				if (cachePut != null) {
					value = cachePut.value();
				}
				CacheEvict cacheEvict = method.getAnnotation(CacheEvict.class);
				if (cacheEvict != null) {
					value = cacheEvict.value();
				}
				sb.append(value[0]);
				for (Object obj : params) {
					sb.append(":" + obj.toString());
				}
				return sb.toString();
			}
		};
	}

```

1. 接下来编写user的增删改查方法：

```
	@CachePut(value = "user", key = "#root.caches[0].name + ':' + #user.userId")
	@RequestMapping(value = "/redis/user", method = RequestMethod.POST)
	public User insertUser(@RequestBody User user) {
		user.setPassword(SystemUtil.MD5(user.getPassword()));
		userService.insertSelective(user);
		return user;
	}
	
	@Cacheable(value = "user")
	@RequestMapping(value = "/redis/user/{userId}", method = RequestMethod.GET)
	public User getUser(@PathVariable Integer userId) {
		User user = userService.getUserById(userId);
		return user;
	}
	//#root.caches[0].name:当前被调用方法所使用的Cache, 即"user"
	@CachePut(value = "user", key = "#root.caches[0].name + ':' + #user.userId")
	@RequestMapping(value = "/redis/user", method = RequestMethod.PUT)
	public User updateUser(@RequestBody User user) {
		user.setPassword(SystemUtil.MD5(user.getPassword()));
		userService.updateByPrimaryKeySelective(user);
		return user;
	}

	@CacheEvict(value = "user")
	@RequestMapping(value = "/redis/user/{userId}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable Integer userId) {
		userService.deleteByPrimaryKey(userId);
	}

```

因为新增和修改传递的参数为user对象，keyGenerator无法获取到userId，只好使用SpEL显示标明key了。

## 然后进行测试：

### 进行insert操作：

[![image](https://user-images.githubusercontent.com/24689696/28456869-73657dee-6e36-11e7-924f-2e0d2207ada6.png)](https://user-images.githubusercontent.com/24689696/28456869-73657dee-6e36-11e7-924f-2e0d2207ada6.png)

### 插入后，进行get请求：

[![image](https://user-images.githubusercontent.com/24689696/28456948-bc8d1a0e-6e36-11e7-970a-a04014f563de.png)](https://user-images.githubusercontent.com/24689696/28456948-bc8d1a0e-6e36-11e7-970a-a04014f563de.png)

### 查看Redis存储：

[![image](https://user-images.githubusercontent.com/24689696/28456590-73b2642a-6e35-11e7-891a-6703b497bd9a.png)](https://user-images.githubusercontent.com/24689696/28456590-73b2642a-6e35-11e7-891a-6703b497bd9a.png)
[![image](https://user-images.githubusercontent.com/24689696/28456635-9d86f626-6e35-11e7-96ab-193c69870bf5.png)](https://user-images.githubusercontent.com/24689696/28456635-9d86f626-6e35-11e7-96ab-193c69870bf5.png)

------

### 进行update操作：

[![image](https://user-images.githubusercontent.com/24689696/28456744-e7f9786e-6e35-11e7-9a03-136d976eaf33.png)](https://user-images.githubusercontent.com/24689696/28456744-e7f9786e-6e35-11e7-9a03-136d976eaf33.png)

### 更新后，进行get请求：

[![image](https://user-images.githubusercontent.com/24689696/28456770-0594be2e-6e36-11e7-9f24-7d7c957dec4e.png)](https://user-images.githubusercontent.com/24689696/28456770-0594be2e-6e36-11e7-9f24-7d7c957dec4e.png)

### 查看Redis存储：

[![image](https://user-images.githubusercontent.com/24689696/28456711-c935dcce-6e35-11e7-898a-99bf83690959.png)](https://user-images.githubusercontent.com/24689696/28456711-c935dcce-6e35-11e7-898a-99bf83690959.png)

------

### 进行delete操作：

[![image](https://user-images.githubusercontent.com/24689696/28457206-d65a4974-6e37-11e7-9872-ffcf886410e1.png)](https://user-images.githubusercontent.com/24689696/28457206-d65a4974-6e37-11e7-9872-ffcf886410e1.png)

### 查看Redis存储：

[![image](https://user-images.githubusercontent.com/24689696/28457248-fb344a92-6e37-11e7-992a-4e1eb4b915d3.png)](https://user-images.githubusercontent.com/24689696/28457248-fb344a92-6e37-11e7-992a-4e1eb4b915d3.png)
发现user:3的记录已经没有了，只剩user:1，user:2了

------

一直很想知道网上很多用之前那种keyGenerator方法的，他们是怎么进行缓存更新和删除的，有知道的可以告知下。

https://github.com/x113773/testall/issues/18