package communication;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import io.SerialRead;
import io.SerialWrite;

import java.io.InputStream;

import conf.SerialConf;

public class SerialCommunication implements SerialPortEventListener {
	private String port;
	private InputStream in;
	private SerialPort serialPort;
	
	public SerialCommunication(String port) {
		this.port = port;
	}
	
	public void open() {
		try {
			CommPortIdentifier portId = CommPortIdentifier.getPortIdentifier(port);
			System.out.println("Port: " + portId.getName());
			
			if (portId.isCurrentlyOwned()) {
				System.out.println("Port busy!");
			} else {
				CommPort commPort = portId.open("whatever it's name", SerialConf.TIME_OUT);
				if (commPort instanceof SerialPort) {
					serialPort = (SerialPort)commPort;
					serialPort.setSerialPortParams(SerialConf.BAUD, 
							SerialPort.DATABITS_8, 
							SerialPort.STOPBITS_1, 
							SerialPort.PARITY_EVEN);
					in = serialPort.getInputStream();
					serialPort.notifyOnDataAvailable(true);
					serialPort.addEventListener(this);
				} else {
					commPort.close();
					System.out.println("the port is not serial!");
				}
			}
		} catch (Exception e) {
			serialPort.close();
			System.out.println("Error: SerialOpen!!!");
			e.printStackTrace();
		}
	}
	
	@Override
	public void serialEvent(SerialPortEvent event) {
		switch (event.getEventType()) {
			/* Break interrupt, 通讯中断 */
			case SerialPortEvent.BI:
				
			/* Overrun error, 溢位错误 */
			case SerialPortEvent.OE:
				
			/* Framing error, 传帧错误 */
			case SerialPortEvent.FE:
				
			/* Parity error, 校验错误 */
			case SerialPortEvent.PE:
				
			/* Carrier detect, 载波检测 */
			case SerialPortEvent.CD:
				
			/* Clear to send, 清除发送 */
			case SerialPortEvent.CTS:
				
			/* Data set ready, 数据设置就绪 */
			case SerialPortEvent.DSR:
				
			/* Ring indicator, 响铃指示 */
			case SerialPortEvent.RI:
				
			case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
				System.out.println("Error: buffer empty!");
			break;
			/* Data Available, 数据就绪*/
			case SerialPortEvent.DATA_AVAILABLE:			
				SerialRead serialRead = new SerialRead(in);
				String out = serialRead.read();
				SerialWrite serialWrite = new SerialWrite(out);
				serialWrite.write();
			break;
		}
	}
	
	public void close() {
		try {
			serialPort.notifyOnDataAvailable(false);
			serialPort.removeEventListener();
			serialPort.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
