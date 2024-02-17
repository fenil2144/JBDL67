import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class ArrayListExample {

    public static void main(String[] args) {
        ArrayList<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        System.out.println(integerList);
        integerList.remove(0);
        integerList.add(2);
        integerList.add(3);

        integerList.set(0,20);


        Stack<Integer> stackObj = new Stack<>();
        stackObj.add(1);
        stackObj.push(2);

        Iterator<Integer> iterator = integerList.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        for(int i=0; i<integerList.size(); i=i+2){
            integerList.get(i);
        }

    }
}
