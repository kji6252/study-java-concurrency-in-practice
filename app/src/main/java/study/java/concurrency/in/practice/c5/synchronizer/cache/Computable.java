package study.java.concurrency.in.practice.c5.synchronizer.cache;

public interface Computable<K,V> {
    V compute(K arg) throws InterruptedException;
}
