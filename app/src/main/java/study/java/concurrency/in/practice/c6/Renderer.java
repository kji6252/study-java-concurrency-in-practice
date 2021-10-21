package study.java.concurrency.in.practice.c6;

import lombok.RequiredArgsConstructor;
import study.java.concurrency.in.practice.c6.domain.ImageData;
import study.java.concurrency.in.practice.c6.domain.ImageInfo;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

/**
 * CompletionService 를 활용해 받아오는 즉시 렌더링
 */
@RequiredArgsConstructor
public class Renderer {

    private final ExecutorService executor;

    void renderPage(CharSequence source) {
        CompletionService<ImageData> completionService = new ExecutorCompletionService<>(executor);
        List<ImageInfo> imageInfos = scanForImageInfo(source);
        for (ImageInfo imageInfo : imageInfos) {
            completionService.submit(imageInfo::download);
        }
        renderText(source);

        for (int i = 0; i < imageInfos.size(); i++) {
            try {
                Future<ImageData> take = completionService.take();
                ImageData imageData = take.get();
                renderImage(imageData);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (ExecutionException e) {
                throw new RuntimeException(e.getCause());
            }
        }
    }

    private void renderImage(ImageData imageData) {

    }

    private void renderText(CharSequence source) {

    }

    private List<ImageInfo> scanForImageInfo(CharSequence source) {
        return Collections.emptyList();
    }
}
