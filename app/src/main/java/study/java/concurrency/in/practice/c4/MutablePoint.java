package study.java.concurrency.in.practice.c4;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class MutablePoint {

    public int x, y;

    public MutablePoint() {
        x = 0;
        y = 0;
    }

    public MutablePoint(MutablePoint mutablePoint) {
        this.x = mutablePoint.x;
        this.y = mutablePoint.y;
    }
}
