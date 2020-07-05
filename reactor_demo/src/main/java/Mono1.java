import java.util.Date;
import java.util.concurrent.Executor;

public class Mono1<T> {

    EventSource<T> eventSource;

    public static <T> Mono1<T> create(EventSource<T> s) {
        return new Mono1<>(s);
    }


    public Mono1<T> yibu(Executor executor) {
        return create(new EventSource<T>() {
            @Override
            public void call(Sublibe<? super T> sublibe) {
//                executor.execute(()->{
//                    subilber(sublibe);
//                });

                subilber(new Sublibe<T>() {
                    @Override
                    public void onNext(T t) {
                        executor.execute(() -> {
                            sublibe.onNext(t);
                        });
                    }
                });
            }
        });
    }


    public <R> Mono1<R> map(Functions<? super T, ? extends R> mapper) {

        return create(new EventSource<R>() {
            @Override
            public void call(Sublibe<? super R> sublibe) {
                subilber(new Sublibe<T>() {
                    @Override
                    public void onNext(T t) {
                        sublibe.onNext(mapper.function(t));
                    }
                });
            }


        });
    }

    public Mono1(EventSource<T> eventSource) {
        this.eventSource = eventSource;
    }

    public void subilber(Sublibe<? super T> sublibe) {
        eventSource.call(sublibe);
    }


}


@FunctionalInterface
interface EventSource<T> {
    void call(Sublibe<? super T> sublibe);
}


@FunctionalInterface
interface Sublibe<T> {
    void onNext(T t);
}


@FunctionalInterface
interface Functions<T, R> {
    R function(T r);
}