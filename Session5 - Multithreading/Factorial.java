import java.math.BigInteger;
import java.util.Arrays;

public class Factorial {

    public static void main(String[] args) {
        Integer[] numbers = {10000,2000,30000,50000, 65000, 15000, 42000};

        long start = System.currentTimeMillis();
        System.out.println(start);
        //100 * 99 * 88 * .... 1
        calaculateFactorial(100);

        Arrays.stream(numbers)
                .map(Factorial::calaculateFactorial)
                .forEach(System.out::println);
        long end = System.currentTimeMillis();
        System.out.println("Total time: "+(end-start));

    }

    public static BigInteger calaculateFactorial(int number){
        BigInteger result = BigInteger.ONE;
        for(int i = 2; i<=number; i++){
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}
