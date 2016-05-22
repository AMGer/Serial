import comm.SerialEvent;

public class Serial {
	public static void main(String[] args) {
		try {
			SerialEvent serialEvent = new SerialEvent();
			serialEvent.handleEvent();
		} catch (Exception e) {
			System.out.println("Error: handle!!!");
			e.printStackTrace();
		}
	}
}