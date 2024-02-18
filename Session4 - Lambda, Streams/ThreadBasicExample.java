class ThreadA extends Thread{
    public void run(){
        System.out.println("ThreadA "+Thread.currentThread());
        for(int i=0;i<20;i++)
        {
            System.out.println("ThreadA class with i= "+i);
        }
    }
}

class ThreadB extends Thread{
    public void run(){
        System.out.println("ThreadB "+Thread.currentThread());
        for(int i=0;i<20;i++)
        {
            System.out.println("ThreadB class with i= "+i);
        }
    }

}

class ThreadRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("ThreadRunnable "+Thread.currentThread());
        for(int i=0;i<20;i++)
        {
            System.out.println("ThreadRunnable class with i= "+i);
        }
    }
}

public class ThreadBasicExample {

    public static void main(String[] args) {
        System.out.println("ThreadBasicExample "+Thread.currentThread());

        ThreadA threadA = new ThreadA();
        ThreadB threadB = new ThreadB();

        try {
            threadA.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        threadB.setPriority(10);

        threadA.start();
//        threadA.run();
        threadB.start();
        ThreadRunnable threadRunnable = new ThreadRunnable();
        Thread threadC = new Thread(threadRunnable);
        threadC.run();
        threadC.setDaemon(true);
        System.out.println("Total Memory available to JVM "+Runtime.getRuntime().totalMemory()/1024 +"KB");
        System.out.println("Free Memory available to JVM "+Runtime.getRuntime().freeMemory());


    }
}
