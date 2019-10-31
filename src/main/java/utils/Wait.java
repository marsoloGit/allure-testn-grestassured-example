package utils;

public class Wait {

	public static void delay(int seconds) {

		int milliSeconds = seconds * 1000;
		try {
			Thread.sleep(milliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
