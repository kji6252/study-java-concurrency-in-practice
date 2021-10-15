package study.java.concurrency.in.practice.c5.synchronizer.cache;

import javax.annotation.concurrent.GuardedBy;
import java.util.Map;
import java.util.concurrent.*;

/**
 * <p>
 * <img src="Memoizer3.png">
 * </p>
 */
public class Memoizer3<K, V> implements Computable<K, V> {

    private final Map<K, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<K, V> c;

    public Memoizer3(Computable<K, V> c) {
        this.c = c;
    }

    @Override
    public V compute(K arg) throws InterruptedException {
        Future<V> vFuture = cache.get(arg);
        if (vFuture == null) {
            Callable<V> vCallable = () -> c.compute(arg);
            FutureTask<V> vFutureTask = new FutureTask<>(vCallable);
            vFuture = vFutureTask;
            cache.put(arg, vFuture);
            vFutureTask.run(); // c.compute는 이 안에서 호출
        }
        try {
            return vFuture.get();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
