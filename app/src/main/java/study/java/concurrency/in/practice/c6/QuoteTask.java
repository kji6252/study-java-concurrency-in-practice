package study.java.concurrency.in.practice.c6;

import lombok.RequiredArgsConstructor;
import study.java.concurrency.in.practice.c6.domain.TravelCompany;
import study.java.concurrency.in.practice.c6.domain.TravelInfo;
import study.java.concurrency.in.practice.c6.domain.TravelQuote;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;

@RequiredArgsConstructor
public class QuoteTask implements Callable<TravelQuote> {

    private final TravelCompany travelCompany;
    private final TravelInfo travelInfo;

    @Override
    public TravelQuote call() throws Exception {
        return travelCompany.solicitQuote(travelInfo);
    }

    public TravelQuote getFailureQuote(Throwable cause) {
        return null;
    }

    public TravelQuote getTimeOutQuote(CancellationException e) {
        return null;
    }
}
