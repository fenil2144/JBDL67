package TestPackage;

class Animal {

    String name;
    int age;

    public void eat(){
        System.out.println("I am eating");
    }
    public void run(){
        System.out.print("I am running");
    }
}

class Dog extends Animal{
    public void eat(){
        System.out.println("I am eating");
    }
}

class Cat extends Animal{

}

public class InheritanceExample{

    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.age = 10;
        dog.eat();

        Animal animalObj = new Animal();
        Animal animalObj2 = new Dog();

        Cat cat = new Cat();
        cat.eat();

    }



}
