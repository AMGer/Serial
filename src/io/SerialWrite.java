package io;

import java.io.OutputStream;

public class SerialWrite implements Runnable {
	private OutputStream out;
	
	public SerialWrite(OutputStream out) {
		this.out = out;
	}
	
	public void run() {
		try {
			int len = 0;
			
			while ((len = System.in.read()) > -1) {
				out.write(len);
			}
		} catch (Exception e) {
			System.out.println("Error: run!!!");
		}
	}
}
