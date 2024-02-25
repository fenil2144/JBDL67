class ThreadJoin extends Thread{

    @Override
    public void run(){
        for(int i = 0;i<2;i++){
            try {
                Thread.sleep(400);
                System.out.println("Current thread: "+Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
//    @Override
//    public synchronized void start() {
//        run();
//    }
}

public class ThreadJoinExample {
    public static void main(String[] args) {
        ThreadJoin t1 = new ThreadJoin();
        ThreadJoin t2 = new ThreadJoin();
        ThreadJoin t3 = new ThreadJoin();

        //Thread 1 starts
        t1.start();
//        t1.setDaemon(true);

//        try {
//            //start second thread after when first thread has died
//            t1.join();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        t2.start();

        try {
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        t3.start();
    }

}
