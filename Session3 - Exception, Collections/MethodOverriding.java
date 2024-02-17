class Parent {
    protected void method1() {
        System.out.println("From Parent Class: Method1");
    }

    public final void display() {
        System.out.println("From Parent Class: display");

    }

//    final int finalVariable;
//    finalVariable = 10;

    int temp = 10;
}

class Child extends Parent {
    public void method1() {
        System.out.println("From Child Class: Method1");
    }

    public void childMethod(){
        System.out.println("From Child Class: childMethod");

    }
//    int temp =20;

    //Final methods can't be overridden
//    public final void display() {
//        System.out.println("From Parent Class: display");
//
//    }

}

public class MethodOverriding {

    static{

    }

    public static void main(String[] args) {
        System.out.println("First line");

        final int finalVariable;
        finalVariable = 10;

        Parent parentObj = new Parent();
        parentObj.method1();
        System.out.println(parentObj.temp);

        Child childObj = new Child();
        childObj.method1();
        childObj.childMethod();
        System.out.println(childObj.temp);

        Parent parentRef = new Child();
        parentRef.method1();
        System.out.println(parentRef.temp);
//        parentRef.a;

//        parentRef.childMethod();

//        Child childRef = new Parent();

//static class NestedClass {
//
//}

    }
}
