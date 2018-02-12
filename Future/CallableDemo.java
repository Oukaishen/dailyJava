import java.util.concurrent.Callable;

public class CallableDemo implements Callable<Integer>{
	private int sum = 0;

	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Inside the Callable method");
		Thread.sleep(5000);
		for(int i = 0; i< 50; i++){
			sum += i;
		}
		System.out.println("Callable complete");
		return sum;
	}
}
