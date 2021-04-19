public class CallThreadsExample {
	public static void main(String[] args) {
		for (int i = 0; i < args.length; i++) {
			ThreadsExample newThread = new ThreadsExample(args[i]);
			newThread.start();
			System.out.println("fim no main");
		}
	}
}
