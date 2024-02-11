import java.io.Serializable;

public class Dog implements Serializable, Cloneable {

    public String name;
    private String breed;
    int age;
    protected String color;

    public Dog(){
        System.out.println("I am empty dog constructor");
    }

    public Dog(int age) {
        this();
        //check if its between 0 to 100.



        this.age = age;
        System.out.println("I am dog constructor with age param");

    }

    public Dog(int age,String name) {
        this(age);
        this.name = name;
        System.out.println("I am dog constructor with age param");

    }



    public static int noOfLegs = 4;

    public void bark(){
        System.out.print("I am barking!");
//        System.out.print(a);
    }
    public void eat(){
        System.out.println("I am eating!");
        int a = 10;
    }
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Dog d = (Dog) obj;
        return name.equalsIgnoreCase(d.name);
    }
//
//    @Override
//    public int hashCode(){
//        return 70;
//    }

}
