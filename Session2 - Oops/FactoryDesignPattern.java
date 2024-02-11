interface Atm{
    void Withdraw();
    void deposit();
}

class SBIAtm implements Atm{

    @Override
    public void Withdraw() {

    }

    @Override
    public void deposit() {

    }
}

class PNBAtm implements Atm{

    @Override
    public void Withdraw() {

    }

    @Override
    public void deposit() {

    }
}

class ATMFactory{
    public Atm getAtm(String bankName){
        if(bankName.equalsIgnoreCase("SBI")){
            return new SBIAtm();
        } else if (bankName.equalsIgnoreCase("PNB")){
            return new PNBAtm();
        } else {
            return null;
        }
    }
}

public class FactoryDesignPattern {
    public static void main(String[] args) {
        ATMFactory atmFactory = new ATMFactory();

        String input = "SBI";
        Atm atm = atmFactory.getAtm(input);
        atm.deposit();
    }

}
