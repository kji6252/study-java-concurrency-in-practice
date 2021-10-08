package study.java.concurrency.in.practice.c4.composition4;

import javax.annotation.concurrent.ThreadSafe;
import java.util.AbstractList;
import java.util.List;

/**
 * 재구성 기법으로 putIfAbsent 메소드 구현
 */
@ThreadSafe
public class ImprovedList<T> extends AbstractList<T> {

    private final List<T> list;

    public ImprovedList(List<T> list) {
        this.list = list;
    }

    public synchronized boolean putIfAbsent(T t) {
        boolean contains = list.contains(t);
        if (!contains) {
            list.add(t);
        }
        return !contains;
    }

    @Override
    public synchronized T get(int index) {
        return list.get(index);
    }

    @Override
    public synchronized int size() {
        return list.size();
    }
}
