import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ExceptionDemo {

    public static void main(String[] args) throws FileNotFoundException, UserNotFoundException {

        //Checked Exception
//        FileReader fileReader = new FileReader("/Users/fenilshah/a.txt");
        try{
            computeDivision(10);
        }  catch(ArithmeticException | ClassCastException arithmeticException){
            System.out.println("I am in catch block catching Arithmetic Exception");
        } catch(NullPointerException nullPointerException){
            System.out.println("I am in catch block catching NullPointer Exception");
        }  finally{
            System.out.println("I am in finally Block");
        }
        System.out.println("After computeDivision method");

        //Try-With-Resources

    }

    public static void funcTryWithResource(){
        try(Scanner scanner = new Scanner(System.in)){
            int number  = scanner.nextInt();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void computeDivision(int number) throws UserNotFoundException{
        int x = 100/number;
        System.out.println("X = "+x);
    }
}
