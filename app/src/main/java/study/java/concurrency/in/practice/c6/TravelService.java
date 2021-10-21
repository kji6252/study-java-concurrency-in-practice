package study.java.concurrency.in.practice.c6;

import lombok.RequiredArgsConstructor;
import study.java.concurrency.in.practice.c6.domain.TravelCompany;
import study.java.concurrency.in.practice.c6.domain.TravelInfo;
import study.java.concurrency.in.practice.c6.domain.TravelQuote;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * 제한된 시간 안에 정보를 가져오도록 요청하는 코드
 */
@RequiredArgsConstructor
public class TravelService {

    private final ExecutorService executorService;

    public List<TravelQuote> getRankedTravelQuotes(TravelInfo travelInfo,
                                                   Set<TravelCompany> travelCompanies,
                                                   Comparator<TravelQuote> ranking,
                                                   long time,
                                                   TimeUnit timeUnit) throws InterruptedException {
        List<QuoteTask> tasks = travelCompanies.stream()
                                               .map(travelCompany -> new QuoteTask(travelCompany, travelInfo))
                                               .collect(Collectors.toUnmodifiableList());

        List<Future<TravelQuote>> futures = executorService.invokeAll(tasks, time, timeUnit);
        Iterator<QuoteTask> taskIterator = tasks.iterator();

        List<TravelQuote> travelQuotes = new ArrayList<>(tasks.size());
        for (Future<TravelQuote> future : futures) {
            QuoteTask quoteTask = taskIterator.next();

            try {
                travelQuotes.add(future.get());
            } catch (ExecutionException e) {
                travelQuotes.add(quoteTask.getFailurQuote(e.getCause()));
            } catch (CancellationException e) {
                travelQuotes.add(quoteTask.getTimeOutQuote(e));
            }

        }

        travelQuotes.sort(ranking);
        return travelQuotes;

    }
}
