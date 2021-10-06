package link.yauritux.camelcron.component.route;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;

public final class SchedulerBuilder extends RouteBuilder {

    private int repeatCount;
    private String cronExpression;
    private String message;
    private int counter = 1;

    public SchedulerBuilder(CamelContext context, int repeatCount, String cronExpression, String message) {
        super(context);
        this.repeatCount = repeatCount;
        this.cronExpression = cronExpression;
        this.message = message;
    }

    @Override
    public void configure() throws Exception {
        from(String.format(
                "scheduler:fulfillment/commandRetry?startScheduler=false&repeatCount=%d&scheduler=quartz&scheduler.cron=%s",
                repeatCount,
                cronExpression)
        ).routeId("fulfillment-command-scheduler")
                .transform(constant(message))
                .bean("counterProcessor");
    }
}