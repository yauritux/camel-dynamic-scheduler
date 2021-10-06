package link.yauritux.camelcron.component.processor;

import link.yauritux.camelcron.component.route.SchedulerBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

@Slf4j
public class SchedulerProcessor implements Processor {

    private int repeatCount;

    public SchedulerProcessor(int repeatCount) {
        this.repeatCount = repeatCount;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        String message = (String) exchange.getMessage().getBody();
        log.info("Executing camel processor with message body {}", message);
        CamelContext currentContext = exchange.getContext();
        String cronExpression = exchange.getMessage().getHeader("cronexp").toString();
        currentContext.addRoutes(
                new SchedulerBuilder(
                    currentContext,
                    repeatCount, cronExpression, exchange.getMessage().getBody(String.class)
                )
        );
    }
}
