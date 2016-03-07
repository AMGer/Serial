package io;

import java.io.OutputStream;
import java.util.Enumeration;

import event.SerialEvent;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

public class SerialWrite implements Runnable {
	private static CommPortIdentifier portId;
	private static Enumeration portList;
	private OutputStream outputStream;
	private SerialPort serialPort;
	private Thread writeThread;
	
	public SerialWrite() {
		try {
			serialPort = (SerialPort) portId.open(" ", 1000); //打开端口
			outputStream = serialPort.getOutputStream();
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
