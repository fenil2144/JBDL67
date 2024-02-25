import java.util.concurrent.Semaphore;

class Sender{

    //synchronized method
    public synchronized void send(String msg){
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //synchronized block
//        synchronized{
//            System.out.println("\n"+msg+" sent");
//
//        }
    }

}

class ThreadSend extends Thread{
    private String msg;
    Sender sender;

    ThreadSend(String msg, Sender sender){
        this.msg = msg;
        this.sender = sender;
    }

    public void run(){
        sender.send(msg);
    }
}

public class SynchronizedDemo {

    public static void main(String[] args) {
        Sender sender = new Sender();
        Sender receiver = new Sender();

        ThreadSend s1 = new ThreadSend("Hi", sender);
        ThreadSend s2 = new ThreadSend("Bye", receiver);

        s1.start();
        s2.start();

    }
}
