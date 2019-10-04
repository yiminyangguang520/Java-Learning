package com.lee.async.controller;

import io.ostenant.springboot.sample.service.WebAsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

import static java.lang.String.format;
import static java.lang.System.out;
import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

@RestController
public class WebAsyncController {
    private final WebAsyncService asyncService;
    private final static String ERROR_MESSAGE = "Task error";
    private final static String TIME_MESSAGE = "Task timeout";

    @Autowired
    @Qualifier("taskExecutor")
    private ThreadPoolTaskExecutor executor;

    @Autowired
    public WebAsyncController(WebAsyncService asyncService) {
        this.asyncService = asyncService;
    }

    @GetMapping("/completion")
    public WebAsyncTask<String> asyncTaskCompletion() {
        // 打印处理线程名
        out.println(format("请求处理线程：%s", currentThread().getName()));

        // 模拟开启一个异步任务，超时时间为10s
        WebAsyncTask<String> asyncTask = new WebAsyncTask<>(10 * 1000L, () -> {
            out.println(format("异步工作线程：%s", currentThread().getName()));
            // 任务处理时间5s，不超时
            sleep(5 * 1000L);
            return asyncService.generateUUID();
        });

        // 任务执行完成时调用该方法
        asyncTask.onCompletion(() -> out.println("任务执行完成"));
        out.println("继续处理其他事情");
        return asyncTask;
    }

    @GetMapping("/exception")
    public WebAsyncTask<String> asyncTaskException() {
        // 打印处理线程名
        out.println(format("请求处理线程：%s", currentThread().getName()));

        // 模拟开启一个异步任务，超时时间为10s
        WebAsyncTask<String> asyncTask = new WebAsyncTask<>(10 * 1000L, () -> {
            out.println(format("异步工作线程：%s", currentThread().getName()));
            // 任务处理时间5s，不超时
            sleep(5 * 1000L);
            throw new Exception(ERROR_MESSAGE);
        });

        // 任务执行完成时调用该方法
        asyncTask.onCompletion(() -> out.println("任务执行完成"));
        asyncTask.onError(() -> {
            out.println("任务执行异常");
            return ERROR_MESSAGE;
        });

        out.println("继续处理其他事情");
        return asyncTask;
    }

    @GetMapping("/timeout")
    public WebAsyncTask<String> asyncTaskTimeout() {
        // 打印处理线程名
        out.println(format("请求处理线程：%s", currentThread().getName()));

        // 模拟开启一个异步任务，超时时间为10s
        WebAsyncTask<String> asyncTask = new WebAsyncTask<>(10 * 1000L, () -> {
            out.println(format("异步工作线程：%s", currentThread().getName()));
            sleep(15 * 1000L);
            return TIME_MESSAGE;
        });

        // 任务执行完成时调用该方法
        asyncTask.onCompletion(() -> out.println("任务执行完成"));
        asyncTask.onTimeout(() -> {
            out.println("任务执行超时");
            return TIME_MESSAGE;
        });

        out.println("继续处理其他事情");
        return asyncTask;
    }

    @GetMapping("/threadPool")
    public WebAsyncTask<String> asyncTaskThreadPool() {
        return new WebAsyncTask<>(10 * 1000L, executor,
                () -> {
                    out.println(format("异步工作线程：%s", currentThread().getName()));
                    return asyncService.generateUUID();
                });
    }
}
