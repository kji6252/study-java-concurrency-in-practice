/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package study.java.concurrency.in.practice;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    @Test void appHasAGreeting() {
        App classUnderTest = new App();
        assertNotNull(classUnderTest.getGreeting(), "app should have a greeting");
        BlockingQueue<String> greetingQueue = new LinkedBlockingQueue<>();
        System.out.println("가나다라마바사");
        System.out.println(StringUtils.abbreviate("가나다라마바사아자차카타파하", 4));
        PriorityQueue<String> priorityQueue = new PriorityQueue<>();
        BlockingQueue<String> queue = new SynchronousQueue<>();
        CountDownLatch countDownLatch = new CountDownLatch(1);
    }
}
