public class AbstractExample {
}

abstract class Bank{
    abstract int getRateOfInterest();
    abstract void withdraw(int amount);
    abstract void deposit(String accountNumber, int amount);

    public void openAccount(){
        System.out.println("I am opening account");
    }

    public Bank(){

    }
}

class SBI extends Bank{

    @Override
    int getRateOfInterest() {
        return 7;
    }

    @Override
    void withdraw(int amount) {

    }

    @Override
    void deposit(String accountNumber, int amount) {

    }
}

class PNB extends Bank{

    @Override
    int getRateOfInterest() {
        return 6;
    }

    @Override
    void withdraw(int amount) {

    }

    @Override
    void deposit(String accountNumber, int amount) {

    }
}
