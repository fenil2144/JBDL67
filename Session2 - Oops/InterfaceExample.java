public class InterfaceExample {

    public static void main(String[] args) {
//        Language language = new Language();
        ProgrammingLanguage programmingLanguage = new ProgrammingLanguage();
//        programmingLanguage.getName();
    }
}

interface Language{

    void getName(String name);
    void getNumberOfCharachters();

    default void getCountOfVariables(){
        System.out.println("I am basic implementation");
    }

    int tempVariable = 10;
}

class ProgrammingLanguage implements Language{

    @Override
    public void getName(String name) {

    }

    @Override
    public void getNumberOfCharachters() {

    }

    public void getSyntaxHints(){

    }
}
