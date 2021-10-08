package study.java.concurrency.in.practice.c4.delegate2;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 4.3.3 위임할 때의 문제점
 * 숫자 범위를 나타내는 클래스. 의존성 조건을 정확하게 처리하지 못하고 있다.
 * 절때 사용하지 말것
 */
public class NumberRange {
    //의존성 조건 : lower <= upper
    private final AtomicInteger lower = new AtomicInteger(0);
    private final AtomicInteger upper = new AtomicInteger(0);

    public void setLower(int i) {
        if (i > upper.get()) {
            throw new IllegalArgumentException("can't set lower to " + i + " > upper");
        }
        lower.set(i);
    }

    public void setUpper(int i) {
        if (i < lower.get()) {
            throw new IllegalArgumentException("can't set lower to " + i + " > upper");
        }
        upper.set(i);
    }

    public boolean isInRange(int i) {
        return (i >= lower.get() && i <= upper.get());
    }
}
