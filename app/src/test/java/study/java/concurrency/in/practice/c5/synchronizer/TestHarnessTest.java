package study.java.concurrency.in.practice.c5.synchronizer;

import org.junit.jupiter.api.Test;
import study.java.concurrency.in.practice.c5.synchronizer.latch.TestHarness;

class TestHarnessTest {

    @Test
    void timeTasks() throws InterruptedException {
        TestHarness testHaness = new TestHarness();
        System.out.println(testHaness.timeTasks(10, () -> {
            System.out.println("12");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
    }
}