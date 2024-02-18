@FunctionalInterface
interface FunctionalInterfaceI{
    public int getSum(int a, int b);

//    public int getSum2(int a, int b);

    default int getSquare3(int a){
        return a*a;
    }
}

public class FunctionalInterfaceExample {
    public static void main(String[] args) {
//
//        FunctionalInterfaceI functionalInterfaceI = new FunctionalInterfaceI() {
//            @Override
//            public int getSum(int a, int b) {
//                return a+b;
//            }
//        };
//        System.out.println(functionalInterfaceI.getSquare3(10));

        // (Comma Seperated Aruments) -> {Body}
        FunctionalInterfaceI lambdaReference = (a,b) -> {return (a+b);};
        System.out.println(lambdaReference.getSquare3(10));
        System.out.println(lambdaReference.getSum(10,20));


    }
}

class FunctionalInterfaceImplementationClass implements FunctionalInterfaceI{

    @Override
    public int getSum(int a, int b) {
        return a+b;
    }
}
