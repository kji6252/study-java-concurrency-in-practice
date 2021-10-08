package study.java.concurrency.in.practice.c4.delegate2;

import javax.annotation.concurrent.Immutable;

@Immutable
public class Point {
    public final int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
