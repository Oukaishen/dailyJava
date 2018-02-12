import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableFutureTest {
	public static void main(String[] args){
		ExecutorService es = Executors.newSingleThreadExecutor();
//		CallableDemo demo = new CallableDemo();
		Future<Integer> futureResult = es.submit(new CallableDemo());
		es.shutdown();
		try{
//			Thread.sleep(2000);
			System.out.println("Main Thread is working on something else");
//			System.out.println(futureResult.isDone()); /// this line is ok
			while(!futureResult.isDone()){
				//Print too much will cause the eclipse crash
//				System.out.println("The Future task is not done yet..."); 
//				Thread.sleep(1000);
			}
			System.out.println("The future Task result is "+ futureResult.get());
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("The main Thread ends. ");
	}
}
