package study.java.concurrency.in.practice.c5.synchronizer.futuretask;

import study.java.concurrency.in.practice.domain.ProductInfo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class PreLoader {
    private final FutureTask<ProductInfo> futureTask = new FutureTask<>(this::loadProductInfo);
    private final Thread thread = new Thread(futureTask);

    public void start() {thread.start();}

    public ProductInfo get() throws InterruptedException {
        try {
            return futureTask.get();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private ProductInfo loadProductInfo() {
        return new ProductInfo();
    }
}
