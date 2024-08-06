package threads;

public class ConsoleThread extends Thread {
	
	@Override
	public void run() {
		for(int i = 0; i <= 1000; ++i) {
			System.out.printf("[Executions: %4d] - Print running on a Thread\n", i);
		}
	}
}
