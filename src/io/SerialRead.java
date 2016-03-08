package io;
import java.io.InputStream;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class SerialRead implements SerialPortEventListener {
	private InputStream in;
	private byte[] buffer = new byte[1024];
	
	public SerialRead(InputStream in) {
		this.in = in;
	}
	
	public void serialEvent(SerialPortEvent arg0) {
		int data;
		try {
			int len = 0;
			
			while ((data = in.read()) > -1) {
				if (data == '\n') {
					break;
				}
				buffer[len++] = (byte)data;
				System.out.print(new String(buffer, 0, len));
			}
		} catch (Exception e) {
			System.out.println("Error: SerialEvent!!!");
			e.printStackTrace();
		}
	}
}
