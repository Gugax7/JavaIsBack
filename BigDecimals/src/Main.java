import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Main {
    public static void main(String[] args) {

        BigDecimal number = new BigDecimal("22");
        number = number.setScale(20, RoundingMode.CEILING);
        BigDecimal secondNumber = number.divide(new BigDecimal("7"), new MathContext(200));

        System.out.println(number + " " + number.unscaledValue() + " " + number.scale() + " " + number.precision());
        System.out.println(secondNumber);
    }
}