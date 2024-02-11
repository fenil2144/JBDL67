public class MethodOverloading {

    public int sum(int a, int b){
        return (a+b);
    }

    public double sum(double a, double b, double c){
        return (a+b+c);
    }

    public static void main(String[] args) {
        MethodOverloading methodOverloading = new MethodOverloading();
        System.out.println(methodOverloading.sum(10,20));
        System.out.println(methodOverloading.sum(10,20,30));
        System.out.println(methodOverloading.sum(10.0,20.0));


    }

//    public void main(int a){
//
//    }
}
