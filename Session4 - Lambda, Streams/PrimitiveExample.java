import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class PrimitiveExample {

    public static void main(String[] args) {
        int[] integers = {1,2,3,4,5,6};
        IntStream intStream = Arrays.stream(integers);

        intStream.min().getAsInt();

        //Converting Primitive Stream to List
        intStream.boxed().collect(Collectors.toList());

        DoubleStream.of(1.0,2.0,3.0,4.0,5.0).mapToObj(Double:: valueOf).collect(Collectors.toList());

        //Convert Primitive Stream to arrays
        intStream.toArray();
    }
}
