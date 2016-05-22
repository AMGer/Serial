package comm;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import io.SerialCommunication;

import java.io.InputStream;

import conf.SerialConf;

public class SerialEvent extends Event implements SerialPortEventListener {
	InputStream in;
	SerialPort serialPort;
	
	public void handleEvent() {
		try {
			CommPortIdentifier portId = CommPortIdentifier.getPortIdentifier(SerialConf.WINDOWS_PORT);
//			System.out.println("Port: " + portId.getName());
			
			if (portId.isCurrentlyOwned()) {
				System.out.println("Port busy!");
			} else {
				CommPort commPort = portId.open("whatever it's name", SerialConf.TIME_OUT);
				if (commPort instanceof SerialPort) {
					serialPort = (SerialPort) commPort;
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
			/* Break interrupt, ͨѶ�ж� */
			case SerialPortEvent.BI:
				
			/* Overrun error, ��λ���� */
			case SerialPortEvent.OE:
			
			/* Framing error, ��֡���� */
			case SerialPortEvent.FE:
			
			/* Parity error, У����� */
			case SerialPortEvent.PE:
			
			/* Carrier detect, �ز���� */
			case SerialPortEvent.CD:
			
			/* Clear to send, ������� */
			case SerialPortEvent.CTS:
			
			/* Data set ready, �������þ��� */
			case SerialPortEvent.DSR:
			
			/* Ring indicator, ����ָʾ */
			case SerialPortEvent.RI:
			
			/* OUTPUT_BUFFER_EMPTY, ��������� */
			case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
				
			break;
			
			/* Data Available, ���ݾ���*/
			case SerialPortEvent.DATA_AVAILABLE:
				SerialCommunication serialComm = new SerialCommunication(in);
				serialComm.communicate();
			break;
			default:
				close();
			break;
		}
	}
	
	void close() {
		try {
			serialPort.notifyOnDataAvailable(false);
			serialPort.removeEventListener();
			serialPort.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
