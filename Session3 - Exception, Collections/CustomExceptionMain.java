class UserNotFoundException extends Exception{
    public UserNotFoundException(String exceptionMessage){
        super(exceptionMessage);
    }
}

public class CustomExceptionMain {

    public static void main(String[] args) throws UserNotFoundException {
        try{
//            encountered the usecase where user is not found
            throw new UserNotFoundException("User doesn't exist in the system");
        }catch(UserNotFoundException userNotFoundException){
            System.out.println(userNotFoundException.getMessage());
        }
//        System.out.println("After UserNotFoundException");

    }
}
