import comm.SerialComm;
import conf.SerialConf;

public class Serial {
	public static void main(String[] args) {
		new Serial().start();
	}
	
	static void start() {
		try {
			SerialComm serialComm = new SerialComm(SerialConf.WINDOWS_PORT);
			serialComm.open();
		} catch (Exception e) {
			System.out.println("Error: start!!!");
			e.printStackTrace();
		}
	}
}
