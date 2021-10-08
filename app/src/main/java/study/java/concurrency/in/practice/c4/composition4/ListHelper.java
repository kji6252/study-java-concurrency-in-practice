package study.java.concurrency.in.practice.c4.composition4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListHelper<E> {
    public List<E> list = Collections.synchronizedList(new ArrayList<>());

//    @NotThreadSafe
    public synchronized boolean putIfAbsentNotThreadSafe(E e) {
        boolean absent = !list.contains(e);
        if (absent) {
            list.add(e);
        }
        return absent;
    }

//    @ThreadSafe
    public boolean putIfAbsent(E e) {
        synchronized (list) {
            boolean absent = !list.contains(e);
            if (absent) {
                list.add(e);
            }
            return absent;
        }
    }
}
