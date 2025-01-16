import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {


        LocalDate today = LocalDate.now();
        System.out.println(today);

        LocalDate five5 = LocalDate.of(2025, Month.JUNE,21);
        System.out.println(five5);

        long howMuchLeft = five5.getDayOfYear() - today.getDayOfYear();
        System.out.println(howMuchLeft);
        System.out.println(today.get(ChronoField.DAY_OF_MONTH));

        LocalDateTime todayTime = LocalDateTime.now();

        System.out.println(todayTime);

        System.out.println(today.getEra().getDisplayName(TextStyle.FULL, Locale.getDefault()));
        System.out.println(todayTime.getHour() + " " + todayTime.getMinute());

        System.out.println(Locale.getDefault().getCountry());

        var formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);

        System.out.println(LocalDateTime.now().format(formatter.withLocale(Locale.getDefault())));

    }
}