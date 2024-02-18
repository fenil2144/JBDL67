import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamExample {

    public static void main(String[] args) {
        List<Integer> number = Arrays.asList(1,2,3,4,5,6);

        //Find Square of Even numbers present in list
        //Without Lambda - using Annonnymous class
        System.out.println(number.stream().filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                System.out.println("In Test Method with integer="+ integer);
                return integer%2 == 0;
            }
        }).map(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                System.out.println("In Apply Method with integer="+ integer);
                return integer*integer;
            }
        }).toString());
//        System.out.println(integerList);


        List<Integer> collectList = number.stream().filter(integer -> integer % 2 == 0)
                .map(integer -> integer * integer).collect(Collectors.toList());
        System.out.println(collectList);

        BiFunction<Integer,Integer, Integer> bifunc = (a,b) -> a+b;
        bifunc.apply(10,20);

        List<String> names = Arrays.asList("Fenil","Shah","Geeks");
        names.stream().filter(s->s.startsWith("G")).collect(Collectors.toList());

        System.out.println(number.stream().filter(x->x%2==0).reduce(10,(sum,i) ->sum+i));

        List<String> list1 = Arrays.asList("Hello","World");
        List<String> list2 = Arrays.asList("Java","Lambda");
        List<String> flattenMap = Arrays.asList(list1,list2).stream().flatMap(List::stream).collect(Collectors.toList());
        System.out.println(flattenMap);

//        number.stream()



    }
}
