public class ThreadsExample extends Thread {
	private String msg = null;
	
	public ThreadsExample(String m) {
		msg = m;
	}
	
	public void run() {
		for (int i = 0; i < msg.length(); i++) {
			System.out.println(msg.charAt(i));
			try {
				sleep((int)(Math.random()*100));
			} catch (InterruptedException e) {
				System.err.println(e);
			}
		}
		System.out.println();
	}
}
