import java.io.PrintStream;

import static java.lang.System.out;

public class Main extends Dog{
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, CloneNotSupportedException {

//        PrintStream ps = new PrintStream();
//        ps.print("Something!");

//        public final static PrintStream out;

        System.out.println("Hello and welcome!");
//
//        A aclassObj = new A();
//        aclassObj.aclassMethod();

        Dog dogObj = new Dog();

//        dogObj.bark();
//
//        Dog dogObj2 = (Dog) Class.forName("Dog").newInstance();
//        Dog dogObj3 = (Dog) dogObj.clone();
//        System.out.println(dogObj.hashCode());
//        System.out.println(dogObj3.hashCode());
//        System.out.println(dogObj.equals(dogObj3));
//
//        Dog dogObj5 = dogObj;
//        System.out.println(dogObj.equals(dogObj5));
//        System.out.println(dogObj.hashCode());
//        System.out.println(dogObj5.hashCode());


        Dog dogObj6 = new Dog();
        dogObj6.name="Tommy";
        Dog dogObj7 = new Dog();
        dogObj7.name="Sharky";

        System.out.println("DogObj6 "+dogObj6.equals(dogObj7));
        System.out.println(dogObj6.hashCode());
        System.out.println(dogObj7.hashCode());


        Dog dogObj4 = dogObj;


    }
}