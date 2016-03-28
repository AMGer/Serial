package io;

import java.io.InputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;

import conf.SerialConf;

public class SerialRead implements Serializable {
	InputStream in;
	
	public SerialRead(InputStream in) {
		this.in = in;
	}
	
	public String read() {
		int data = 0;
		String out = null;
		ByteBuffer buffer = ByteBuffer.allocate(SerialConf.BUFFER_SIZE);
		
		try {
			while ((data = in.read()) > -1) {
				if (data == '\n') {
					break;
				}
				buffer.put((byte) data);
			}
			buffer.flip();
			out = new String(buffer.array());
			
			//debug
			System.out.println(new String(buffer.array()));
			buffer.clear();
		} catch (Exception e) {
			System.out.println("Error: SerialEvent!!!");
			e.printStackTrace();
		}
		return out;
	}
}