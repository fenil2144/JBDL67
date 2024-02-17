import java.util.HashMap;

class Test<T>{
    T obj;
    Test(T obj){
        this.obj = obj;
    }
    public T getObject(){
        return this.obj;
    }
}

public class GenericsExample {

    public static void main(String[] args) {

        //Instance of Integer type
        Test<Integer> iObj = new Test<>(15);
        System.out.println(iObj.getObject());
        iObj.obj = 10;
//        iObj.obj = "String";


        //Instance of String type
        Test<String> sObj = new Test<>("Java");
        System.out.println(sObj.getObject());
        sObj.getObject().contains("a");

        Object obj = new Object();
        obj = "String";
        //Not possible
//        obj.contains("a");
        System.out.println(obj/10);
        System.out.println(obj);
        obj = 10;
        System.out.println(obj/10);
        System.out.println(obj);

    }
}
