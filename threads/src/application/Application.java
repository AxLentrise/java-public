package application;

public class Application {
	public static void main(String [] args) {
		
//		ConsoleThread tConsole = new ConsoleThread();
		
		Thread tiConsole = new Thread(() -> {
			for(int i = 0; i <= 1000; ++i) {
				System.out.printf("[Executions: %4d] - Lambda Thread\n", i);
			}
		});
		
		tiConsole.start();
		
//		tConsole.start();
	}
}
