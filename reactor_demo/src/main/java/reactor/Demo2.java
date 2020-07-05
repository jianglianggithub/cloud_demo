package reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.util.function.BiFunction;

public class Demo2 {
    public static void main(String[] args) {
        Flux<String> flux = Flux.generate(
                () -> 0,
                new BiFunction<Integer, SynchronousSink<String>, Integer>() {
                    @Override
                    public Integer apply(Integer integer, SynchronousSink<String> stringSynchronousSink) {
                        return null;
                    }
                });
    }
}
