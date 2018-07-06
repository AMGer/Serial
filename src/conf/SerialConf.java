package conf;

/**
 * <p>SerialConf</p> Java 串口通信配置文件.
 *
 * @author lishiyun19@163.com
 */
public class SerialConf {
	/**
	 * Windows 串口.
	 */
	public static final String WINDOWS_PORT = "COM5";
	
	/**
	 * Linux 串口.
	 */
//	public static final String LINUX_PORT = "/dev/ttyS0";
	
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
	 * 测试服务器.
	 */
//	public static final String URL = "http://localhost:8080/Care/Serial";
}