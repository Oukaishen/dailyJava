// Version 2, not thread-safe. Still have a little bit flaw
public class SingletonV2 {
	// make the constructor private
		private SingletonV2(){}
		
		// the Singleton object
		private static SingletonV2 instance = null;
		
		// the factory method
		public static SingletonV2 getInstance(){
			if(instance == null){
				// here is not thread-safe.
				/*there may be two thread come in this block at the same time 
				 and create the instance twice
				 */
				synchronized(SingletonV2.class){
					// double check
					/*if thread A & B both inside the first if
					 * then after A creates the SingletonV2,
					 * B may still initial the second time
					 * so we need the double check
					 * */
					if(instance == null){
						instance = new SingletonV2();
					}
				}
			}
			return instance;
		}
}
