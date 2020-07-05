package reactor;

import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public class WebClient1 {

    {
        System.out.println("1111");
    }

    public WebClient1() {
        System.out.println("222222");
    }

    public static void main(String[] args) {
        WebClient1 webClient1 = new WebClient1();
        WebClient client = WebClient.create();
        Mono<String> mapFlux = client.post()
                .uri("http://www.baidu.com")
                .retrieve()
                .bodyToMono(String.class);
        System.out.println(mapFlux.block());
    }
}
