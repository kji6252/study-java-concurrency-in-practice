package study.java.concurrency.in.practice.c5.synchronizer.cache;

import javax.annotation.concurrent.GuardedBy;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * <img src="Memoizer2.png">
 * </p>
 */
public class Memoizer2<K, V> implements Computable<K, V> {

    private final Map<K, V> cache = new ConcurrentHashMap<>();
    private final Computable<K, V> c;

    public Memoizer2(Computable<K, V> c) {
        this.c = c;
    }

    @Override
    public V compute(K arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}
