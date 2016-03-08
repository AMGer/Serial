import conf.SerialConf;
import connect.SerialConnect;

public class Serial {
	public static void main(String[] args) {
		new Serial().start();
	}
	
	private static void start() {
		try {
			SerialConnect sc = new SerialConnect();
			sc.connect(SerialConf.PORT);
		} catch (Exception e) {
			System.out.println("Error: start!!!");
			e.printStackTrace();
		}
	}
}
