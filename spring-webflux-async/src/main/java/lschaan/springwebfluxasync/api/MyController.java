package lschaan.springwebfluxasync.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
public class MyController {
    private static final Logger logger = LoggerFactory.getLogger(MyController.class);

    @GetMapping("/endpoint")
    public Mono<String> handleRequest() {
        return Mono.just("Response Body")
            .doOnNext(this::asyncOperation);
    }

    private void asyncOperation(String response) {
        Mono.fromCallable(() -> {
                logger.info("Starting async operation");
                Thread.sleep(5000);
                logger.info("Async operation completed.");
                return null;
            }).subscribeOn(Schedulers.boundedElastic())
            .subscribe();
    }
}
