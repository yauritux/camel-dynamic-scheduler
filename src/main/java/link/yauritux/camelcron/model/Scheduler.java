package link.yauritux.camelcron.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Scheduler {
    private String seconds;
    private String minutes;
    private String hours;
    private String dayOfMonth;
    private String month;
    private String dayOfWeek;
    private String message;

    public String getCronExpression() {
        var cronExpression = new StringBuilder();
        if (seconds != null && !seconds.trim().equals("")) cronExpression.append("0/" + seconds);
        else cronExpression.append("*");
        if (minutes != null && !minutes.trim().equals("")) cronExpression.append(" 0/" + minutes);
        else cronExpression.append(" *");
        if (hours != null && !hours.trim().equals("")) cronExpression.append(" 0/" + hours);
        else cronExpression.append(" *");
        if (dayOfMonth != null && !dayOfMonth.trim().equals("")) cronExpression.append(" " + dayOfMonth);
        else cronExpression.append(" *");
        if (month != null && !month.trim().equals("")) cronExpression.append(" " + month);
        else cronExpression.append(" *");
        if (dayOfWeek != null && !dayOfWeek.trim().equals("")) cronExpression.append(" " + dayOfWeek);
        else cronExpression.append(" ?");
        return cronExpression.toString();
    }
}
