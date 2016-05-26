package conf;

public class SerialConf {
	/**
	 * Windows 串口.
	 */
	public static final String WINDOWS_PORT = "COM5";
	
	/**
	 * Linux 串口.
	 */
	public static final String LINUX_PORT = "/dev/ttyS0";
	
	/**
	 * 波特率.
	 */
	public static final int BAUD = 256000;
	
	/**
	 * 超时.
	 */
	public static final int TIME_OUT = 5000;
	
	/**
	 * 缓冲区.
	 */
	public static final int BUFFER_SIZE = 10240;
	
	/**
	 * 服务器.
	 */
	public static final String URL = "http://localhost:8080/Care/Serial";
}