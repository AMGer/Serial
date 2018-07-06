## Serial  
中科大工程实践大作业代号 Serial，通过 Java 实现 Windows 程序串口通信，并将数据通过 HTTP 请求实时发送到云端服务器。

## Prerequisite
先决条件  
一般来说，串口通信通常由 C/C++ 程序实现会更加方便，但由于我个人更熟悉 Java，因此，本实例通过加载 RXTXcomm.jar 包顺利实现了 Java 串口通信程序。当然，前提是首先必须得保证 Windows 或者 Linux 系统上有闲置可用的串口端口。

配置文件  
本实例通信数据是模拟手环在用户不同运动状态下与云服务器的通信数据，例如：
```
FD1112000D6F0001808ABE112233445566F139F24C01F401010E010081FD1112000D6F0001808ABE112233445566F17AF25501F4010A3A010181FD1112000D6F0001808ABE112233445566F13EF21101
```
数据呈现杂乱无章状态，可能完整也可能不完整，这就需要开发者考虑各种各样的情形。

##Params  
本实例主要的配置参数为：  
```
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
```

##Run  
运行程序你只需打开串口端口以后，使用如下命令即可执行：  
```
java -jar serial.jar
```

