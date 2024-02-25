import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FactorialMT extends Thread{

    int number;
    BigInteger result;

    FactorialMT(int number){
        this.number = number;
    }

    public static void main(String[] args) {
        Integer[] numbers = {10000,2000,30000,50000, 65000, 15000, 42000};

        List<FactorialMT> threads;
        long start = System.currentTimeMillis();
        threads = Arrays.stream(numbers)
                .map(x -> {
                            FactorialMT thread = new FactorialMT(x);
                            thread.start();
                            return thread;
                        }

                        ).collect(Collectors.toList());


        long end = System.currentTimeMillis();
        System.out.println("Total time: "+(end-start));


    }

    @Override
    public void run(){

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.result = calculateFactorial();
    }

    public BigInteger calculateFactorial(){
        BigInteger result = BigInteger.ONE;
        for(int i = 2; i<=this.number; i++){
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

}
