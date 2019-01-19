package DaemonThread;

import java.util.concurrent.TimeUnit;

public class TestDaemonThreads  implements Runnable{


    public static void main(String [] args) throws Exception{
        for(int i = 0; i < 10; i++){
            Thread daemon = new Thread(new TestDaemonThreads());
            daemon.setDaemon(true);
            daemon.start();
            TimeUnit.MILLISECONDS.sleep(10);
        }
        System.out.println("All Daemon Threads");
        TimeUnit.MILLISECONDS.sleep(65);
    }

    @Override
    public void run() {
        try{
            while(true){
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.print(Thread.currentThread() + "" + this);
            }
        }catch (InterruptedException e){
            System.out.println("sleep() interrupted");
        }
    }
}
