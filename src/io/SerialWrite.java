package io;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import conf.SerialConf;

public class SerialWrite {
	private String out;
	
	public SerialWrite(String out) {
		this.out = out;
	}
	
	public void write() {
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
