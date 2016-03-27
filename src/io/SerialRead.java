package io;

import java.io.InputStream;
import java.io.Serializable;

import conf.SerialConf;

public class SerialRead implements Serializable {
	InputStream in;
	byte[] buffer = new byte[SerialConf.BUFFER_SIZE];
	
	public SerialRead(InputStream in) {
		this.in = in;
	}
	
	public String read() {
		int data;
		int len = 0;
		try {
			while ((data = in.read()) > -1) {
				if (data == '\n') {
					break;
				}
				
				//ÅÐ¶ÏÊý¾Ý
				buffer[len++] = (byte)data;
			}
			System.out.println(new String(buffer, 0, len));
		} catch (Exception e) {
			System.out.println("Error: SerialEvent!!!");
			e.printStackTrace();
		}
		return new String(buffer, 0, len);
	}
}
