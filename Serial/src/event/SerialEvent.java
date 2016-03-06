package event;
import java.io.InputStream;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class SerialEvent implements SerialPortEventListener {
	private InputStream inputStream;
	
	@Override
	public void serialEvent(SerialPortEvent event) {
		switch (event.getEventType()) {
			case SerialPortEvent.BI: /*Break interrupt,通讯中断*/
			case SerialPortEvent.OE: /*Overrun error，溢位错误*/
			case SerialPortEvent.FE: /*Framing error，传帧错误*/
			case SerialPortEvent.PE: /*Parity error，校验错误*/
			case SerialPortEvent.CD: /*Carrier detect，载波检测*/
			case SerialPortEvent.CTS: /*Clear to send，清除发送*/
			case SerialPortEvent.DSR: /*Data set ready，数据设备就绪*/
			case SerialPortEvent.RI: /*Ring indicator，响铃指示*/
			case SerialPortEvent.OUTPUT_BUFFER_EMPTY: /*Output buffer is empty，输出缓冲区清空*/
				break;
			case SerialPortEvent.DATA_AVAILABLE: 
				byte[] buffer = new byte[20];
				try {
					while (inputStream.available() > 0) {
						int num = inputStream.read(buffer);
					}
					System.out.println(new String(buffer));
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
		}
	}
}
