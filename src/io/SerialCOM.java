package io;

import java.io.InputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;

import conf.SerialConf;

public class SerialCOM implements Serializable {
	InputStream in;
	ByteBuffer byteBuffer = ByteBuffer.allocate(SerialConf.BUFFER_SIZE);
	
	public SerialCOM(InputStream in) {
		this.in = in;
	}
	
	public void comm() {
		if (in != null) {
			try {
				while (in.available() > 0) {
					/* 判断前导码 */
					byte[] head = new byte[2];
					in.read(head);
					if (head[0] == 70 && head[1] == 68) {
						byteBuffer.put(head);
						
						/* 写缓冲区 */
						byte[] buffer = new byte[56];
						in.read(buffer);
						byteBuffer.put(buffer);
						byteBuffer.flip(); //反转
						String out = new String(byteBuffer.array());
						byteBuffer.clear();
						
						System.out.println(out);
						SerialWrite serialWrite = new SerialWrite(out);
						serialWrite.write();
					}
				}
			} catch (Exception e) {
				System.out.println("Error: SerialEvent!!!");
				e.printStackTrace();
			} finally {
				try {
					byteBuffer.clear();
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}