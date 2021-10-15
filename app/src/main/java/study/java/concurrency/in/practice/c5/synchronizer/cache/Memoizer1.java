package study.java.concurrency.in.practice.c5.synchronizer.cache;

import javax.annotation.concurrent.GuardedBy;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * <img src="Memoizer1.png">
 * </p>
 */
public class Memoizer1<K, V> implements Computable<K, V> {

    @GuardedBy("this")
    private final Map<K, V> cache = new HashMap<>();
    private final Computable<K, V> c;

    public Memoizer1(Computable<K, V> c) {
        this.c = c;
    }

    @Override
    public synchronized V compute(K arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}
