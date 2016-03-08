package connect;

import java.io.InputStream;
import java.io.OutputStream;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import io.SerialRead;
import io.SerialWrite;

public class SerialConnect {
	public void connect(String portName) {
		try {
			CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
			if (portIdentifier.isCurrentlyOwned()) {
				System.out.println("port is currently owned!!!");
			} else {
				CommPort commPort = portIdentifier.open(this.getClass().getName(), 1000);
				if (commPort instanceof SerialPort) {
					SerialPort serialPort = (SerialPort) commPort;
					serialPort.setSerialPortParams(38400, 
												SerialPort.DATABITS_8, 
												SerialPort.STOPBITS_1, 
												SerialPort.PARITY_EVEN);
					InputStream in = serialPort.getInputStream();
					OutputStream out = serialPort.getOutputStream();
					
					(new Thread(new SerialWrite(out))).start();
					serialPort.addEventListener(new SerialRead(in));
					serialPort.notifyOnDataAvailable(true);
				} else {
					System.out.println("port is serials!!!");
				}
			}
		} catch (Exception e) {
			System.out.println("Error: SerialConnect!!!");
			e.printStackTrace();
		}
	}
}
