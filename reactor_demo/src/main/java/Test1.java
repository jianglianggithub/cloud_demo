import org.reactivestreams.Publisher;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Random;
import java.util.function.Function;

public class Test1 {


    // flatMap 代表将之前的流 中的所有 子流 转换成一个新的流。
    public static void main(String[] args) {
        Flux<String> asdasd = Flux.just("1123asd", "asdasd").flatMap(a ->
                {
                    System.out.println(a);
                    return Flux.fromArray(a.split(""));

                }


        ).distinct();
        Mono<Long> count = asdasd.count();
        count.subscribe(r -> System.out.println(r));

    }
}
