package io;
import java.io.InputStream;
import java.util.Enumeration;

import event.SerialEvent;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEventListener;

public class SerialRead implements Runnable {
	private static CommPortIdentifier portId;
	private static Enumeration portList;
	private InputStream inputStream;
	private SerialPort serialPort;
	private Thread readThread;
	
	public SerialRead() {
		try {
			serialPort = (SerialPort) portId.open(" ", 1000); //打开端口
			inputStream = serialPort.getInputStream();
			serialPort.addEventListener(new SerialEvent()); //注册监听事件
			serialPort.setSerialPortParams(38400, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_EVEN);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			
		}
	}
}
