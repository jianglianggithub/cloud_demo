package reactor;

import org.reactivestreams.Subscription;
import reactor.core.Disposable;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

public class demo extends BaseSubscriber<Object> {

    private AtomicInteger integer = new AtomicInteger(0);

    public static void main(String[] args) {
        Flux.just("1", "2", "3", "4", "5").subscribe(new demo());
    }

    @Override
    public void hookOnSubscribe(Subscription subscription) {
        System.out.println("Subscribed");
        request(1);
    }

    @Override
    protected void hookOnNext(Object value) {
        System.out.println(value);
        if (integer.addAndGet(1) == 3) {


        } else {
            request(1);

        }
    }

    @Override
    protected void hookOnComplete() {
        super.hookOnComplete();
    }

    @Override
    protected void hookOnError(Throwable throwable) {
        super.hookOnError(throwable);
    }
}
