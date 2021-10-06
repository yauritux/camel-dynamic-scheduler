package link.yauritux.camelcron.controller;

import link.yauritux.camelcron.model.Scheduler;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.ProducerTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/schedulers")
public class CamelController {

    private final ProducerTemplate producer;

    public CamelController(ProducerTemplate producer) {
        this.producer = producer;
    }

    @PostMapping("/default/{text}")
    public ResponseEntity<String> createDefault(@PathVariable("text") String message) {
        var defaultScheduler = Scheduler.builder().message(message).build();
        producer.requestBody("direct:fulfillmentCommandRetry", defaultScheduler);
        return ResponseEntity.status(201).body(defaultScheduler.getCronExpression() + " is created");
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Scheduler scheduler) {
        producer.requestBody("direct:fulfillmentCommandRetry", scheduler);
        return ResponseEntity.status(201).body(scheduler.getCronExpression() + " is created");
    }
}
