import comm.SerialEvent;

/**
 * <p>Serial</p> Java 串口通信程序入口类.
 *
 * @author lishiyun19@163.com
 * @date 2016-05-20 16:50
 */
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