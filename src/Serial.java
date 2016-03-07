import java.io.InputStream;
import java.util.Enumeration;
import java.util.Observable;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEventListener;
import io.SerialRead;

public class Serial {
	private static CommPortIdentifier portId;
	private static Enumeration portList;
	private InputStream inputStream;
	private SerialPort serialPort;
	private Thread readThread;
	
	public static void main(String[] args) {
		portList = CommPortIdentifier.getPortIdentifiers(); //枚举端口
		while (portList.hasMoreElements()) {
			portId = (CommPortIdentifier) portList.nextElement();
			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) { //串口
				if (portId.getName().equals("")) {
					new SerialRead();
				}
			}
		}
	}
}
