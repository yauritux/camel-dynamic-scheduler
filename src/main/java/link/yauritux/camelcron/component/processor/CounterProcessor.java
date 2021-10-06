package link.yauritux.camelcron.component.processor;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CounterProcessor implements Processor {

    private int counter = 1;

    @Override
    public void process(Exchange exchange) throws Exception {
        log.info("Processing {}. Retry number: {}", exchange.getMessage().getBody(), counter++);
    }
}
