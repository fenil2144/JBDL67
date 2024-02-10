public class Dog implements Cloneable{



    public String name;
    private String breed;
    private int age;
    private String color;

    public void bark(){
        System.out.print("I am barking!");
    }
    public void eat(){
        System.out.println("I am eating!");
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

    @Override
    public int hashCode(){
        return 70;
    }

}
