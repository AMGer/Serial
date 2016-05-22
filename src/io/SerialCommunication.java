package io;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;

import conf.SerialConf;

public class SerialCommunication extends Communication implements ISerialCommunication {
	ByteBuffer byteBuffer = ByteBuffer.allocate(SerialConf.BUFFER_SIZE);
	InputStream in;
	
	public SerialCommunication(InputStream in) {
		this.in = in;
	}
	
	public void communicate() {
		if (in != null) {
			try {
				while (in.available() > 0) {
					String out = read();
					byteBuffer.clear();
					write(out);
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
	
	String read() {
		String out = null;
		try {
			byte[] head = new byte[2];
			in.read(head);
			if (head[0] == 70 && head[1] == 68) {
				byteBuffer.put(head);
				byte[] buffer = new byte[56];
				in.read(buffer);
				byteBuffer.put(buffer);
				byteBuffer.flip();
				out = new String(byteBuffer.array());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}
	
	void write(String out) {
        BufferedReader in = null;

        try {
            String urlNameString = SerialConf.URL + "?" + "comm=" + out;
            URL realUrl = new URL(urlNameString);
            URLConnection conn = realUrl.openConnection();

            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:26.0) Gecko/20100101 Firefox/26.0");
            conn.connect();

            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            if (in != null) {
                System.out.println(in.readLine());
            }
        } catch (Exception e) {
            System.out.println("Error: SerialWrite!");
            e.printStackTrace();
        } finally {
            try {
                if (in != null) 
                	in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}