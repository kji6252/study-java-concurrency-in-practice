package study.java.concurrency.in.practice.c4.delegate2;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 4.3.2 독립 상태 변수
 * 두 개 이상의 변수에게 스레드 안전성을 위임
 */
public class VisualComponent {

    private final List<KeyListener> keyListeners = new CopyOnWriteArrayList<>();
    private final List<MouseListener> mouseListeners = new CopyOnWriteArrayList<>();


    public boolean addKeyListener(KeyListener keyListener) {
        return keyListeners.add(keyListener);
    }

    public boolean addMouseListener(MouseListener mouseListener) {
        return mouseListeners.add(mouseListener);
    }

    public boolean removeKeyListener(KeyListener keyListener) {
        return keyListeners.remove(keyListener);
    }

    public boolean removeMouseListener(MouseListener mouseListener) {
        return mouseListeners.remove(mouseListener);
    }
}
