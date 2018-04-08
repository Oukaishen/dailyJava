import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Print1Thread implements Runnable{

	private Object lock;
	
	public Print1Thread(Object obj){
		lock = obj;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!Thread.interrupted()){
			synchronized(lock){
				while( !AB12byWait.flag ) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("1");
				AB12byWait.flag = false;
				lock.notify();
			}
		}
	}
	
}

class Print2Thread implements Runnable{

	private Object lock;
	
	public Print2Thread(Object obj){
		lock = obj;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!Thread.interrupted()){
			synchronized(lock){
				while( AB12byWait.flag ) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("2");
				AB12byWait.flag = true;
				lock.notify();
			}
		}
	}
	
}


public class AB12byWait {
	public static boolean flag;
	public static void main(String [] args) throws InterruptedException{
		ExecutorService exec = Executors.newCachedThreadPool();
		Object lock = new Object();
		exec.execute(new Print1Thread(lock));
		exec.execute(new Print2Thread(lock));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
	}
}
