public class SingletonDesign {

    private SingletonDesign(){

    }
    private static SingletonDesign instance;
    public static SingletonDesign getInstance(){
        if(instance == null){
            instance = new SingletonDesign();
        }
        return instance;
    }
}
