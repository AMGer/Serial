package conf;

public class SerialConf {
	/**
	 * Windows 串口.
	 */
	public static final String WINDOWS_PORT = "COM3";
	
	/**
	 * Linux 串口.
	 */
	public static final String LINUX_PORT = "/dev/ttyS0";
	
	/**
	 * 波特率.
	 */
	public static final int BAUD = 38400;
	
	/**
	 * 超时.
	 */
	public static final int TIME_OUT = 5000;
	
	/**
	 * 缓存空间.
	 */
	public static final int BUFFER_SIZE = 400000;
	
	/**
	 * 服务器.
	 */
	public static final String URL = "http://192.168.1.47:8080/Care/Serial";
}