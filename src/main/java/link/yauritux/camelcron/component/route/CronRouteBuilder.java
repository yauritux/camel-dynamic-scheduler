package link.yauritux.camelcron.component.route;

import link.yauritux.camelcron.component.processor.SchedulerProcessor;
import link.yauritux.camelcron.model.Scheduler;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.support.DefaultMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CronRouteBuilder extends RouteBuilder {

    @Value("${scheduler.repeat.count:3}")
    private int repeatCount;

    @Override
    public void configure() throws Exception {
        from("direct:fulfillmentCommandRetry")
                .routeId("fulfillment-command-retry")
                .log("Starting command retry endpoint with message body: ${body}")
                .process(this::buildCamelCronExpression)
                .log("after processed: ${body}")
                .process(new SchedulerProcessor(repeatCount));
    }

    private void buildCamelCronExpression(Exchange exchange) {
        var scheduler = exchange.getMessage().getBody(Scheduler.class);
        Message message = new DefaultMessage(exchange.getContext());
        message.setHeader("cronexp", scheduler.getCronExpression().replaceAll(" ", "+"));
        message.setBody(scheduler.getMessage());
        exchange.setMessage(message);
    }
}