package study.java.concurrency.in.practice.c4.composition4;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Vector;

/**
 * 기존 Vector 클래스를 상속받아 putIfAbsent 메소드를 추가
 * @param <E>
 */
@ThreadSafe
public class BetterVector<E> extends Vector<E> {
    public synchronized boolean putIfAbsent(E e) {
        boolean absent = !contains(e);
        if (absent) {
            add(e);
        }
        return absent;
    }
}
