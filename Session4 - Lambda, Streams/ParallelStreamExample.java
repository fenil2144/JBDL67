import java.util.Arrays;
import java.util.List;

public class ParallelStreamExample {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8);
        numbers.parallelStream().filter(integer -> {
            System.out.println("In Filter function: "+integer+" "+Thread.currentThread().getName());
            return integer%2 == 0;
        }).map(integer -> {
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("In Map function: "+Thread.currentThread().getName());
            return integer*integer;
        }).forEachOrdered(x->{
            System.out.println("In For Each function: "+Thread.currentThread().getName());
        });

        System.out.println("Avlbl Processors: "+Runtime.getRuntime().availableProcessors());

        List<String> list = Arrays.asList("J","A","V","A","B","A","C","K","E","N","D");
        list.parallelStream().forEach(System.out::print);
        //CDAANEVBJAK
        //CVAJENDAABK
    }
}
