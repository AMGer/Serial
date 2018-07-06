package io;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import conf.SerialConf;

/**
 * <p>SerialCommunication</p> 端口通信类, 继承 <p>Communication</p> 抽象类, 实现了 <p>ISerialCommunication</p> 端口通信接口.
 *
 * @author lishiyun19@163.com
 */
public class SerialCommunication extends Communication implements ISerialCommunication {
	InputStream in;
	String out = null;
	ByteBuffer byteBuffer = ByteBuffer.allocate(SerialConf.BUFFER_SIZE);
	
	public SerialCommunication(InputStream in) {
		this.in = in;
	}
	
	public void communicate() {
		if (in != null) {
			try {
				while (in.available() > 0) { //读取缓冲区所有数据
					out = read();
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

    /**
     * 每次读取定长数据.
     *
     * @return String 数据.
     */
	String read() {
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

    /**
     * 将数据通过 HTTP 请求发送给云端服务器.
     *
     * @param out 数据.
     */
	void write(String out) {
        BufferedReader in = null;

        try {
            String urlNameString = SerialConf.URL + "?" + "comm=" + out;
            URL realUrl = new URL(urlNameString);
            URLConnection conn = realUrl.openConnection();

            //构建 HTTP Request Header
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:26.0) Gecko/20100101 Firefox/26.0");
            conn.connect();

            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
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