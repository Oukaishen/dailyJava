// V3 add the volatile to prevent the JVM re-order.
public class SingletonV3 {
	// make the constructor private
		private SingletonV3(){}
		
		// the Singleton object
		// use the volatile keyword to prevent the JVM optimizing and re-order the code
		private volatile static SingletonV3 instance = null;
		
		// the factory method
		public static SingletonV3 getInstance(){
			if(instance == null){
				// here is not thread-safe.
				/*there may be two thread come in this block at the same time 
				 and create the instance twice
				 */
				synchronized(SingletonV3.class){
					// double check
					/*if thread A & B both inside the first if
					 * then after A creates the SingletonV2,
					 * B may still initial the second time
					 * so we need the double check
					 * */
					if(instance == null){
						instance = new SingletonV3();
					}
				}
			}
			return instance;
		}
}
