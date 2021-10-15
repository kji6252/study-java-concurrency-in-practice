package study.java.concurrency.in.practice.c5.synchronizer.cache;

import javax.annotation.concurrent.GuardedBy;
import java.util.concurrent.*;

/**
 * 최종 버전
 */
public class Memoizer<K, V> implements Computable<K, V> {

    private final ConcurrentMap<K, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<K, V> c;

    public Memoizer(Computable<K, V> c) {
        this.c = c;
    }

    @Override
    public V compute(K arg) throws InterruptedException {
        while (true) {
            Future<V> vFuture = cache.get(arg);
            if (vFuture == null) {
                Callable<V> vCallable = () -> c.compute(arg);
                FutureTask<V> vFutureTask = new FutureTask<>(vCallable);
                vFuture = cache.putIfAbsent(arg, vFutureTask);
                if (vFuture == null) {
                    vFuture = vFutureTask;
                    vFutureTask.run(); // c.compute는 이 안에서 호출
                }
            }
            try {
                return vFuture.get();
            } catch (CancellationException e) {
                cache.remove(arg, vFuture);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
