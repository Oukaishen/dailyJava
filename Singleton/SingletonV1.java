
// Version 1, not thread-safe. Lazy implementation.
public class SingletonV1 {
	// make the constructor private
	private SingletonV1(){}
	
	// the Singleton object
	private static SingletonV1 instance = null;
	
	// the factory method
	public static SingletonV1 getInstance(){
		if(instance == null){
			// here is not thread-safe.
			/*there may be two thread come in this block at the same time 
			 and create the instance twice
			 */
			instance = new SingletonV1();
		}
		return instance;
	}
	
}

