# RocketMQ
Spring Boot + RocketMQ

RocketMQ下载地址
https://rocketmq.apache.org/download/

D:\Software\rocketmq-all-5.3.1-bin-release
JDK17

# Windows系统启动
## 一、服务启动步骤
1.启动NameServer​   
打开CMD进入bin目录，执行命令：   
#### mqnamesrv.cmd   
成功后会弹出新窗口显示日志，若看到The Name Server boot success则启动成功   
若想在本机测试集群环境，可以在另一个端口启动nameserver，命令如下：   
#### mqnamesrv.cmd -p 9877   
成功后会弹出新窗口显示日志，若看到The Name Server boot success则启动成功   

2.启动Broker​   
在相同目录下执行（需指定NameServer地址）：   
#### mqbroker.cmd -n 127.0.0.1:9876 autoCreateTopicEnable=true     
参数autoCreateTopicEnable=true用于自动创建Topic   
如果上述方式启动broker失败，可尝试在broker.conf文件中添加以下配置：   
##### NameServer 地址
namesrvAddr=127.0.0.1:9876
如果是集群环境，要配置多个nameserver地址，用逗号分隔，如：namesrvAddr=127.0.0.1:9876,127.0.0.1:9877
##### 存储路径
storePathRootDir=D:/Software/rocketmq-all-5.3.1-bin-release/store
storePathCommitLog=D:/Software/rocketmq-all-5.3.1-bin-release/store/commitlog  
确保上面的路径存在，且有读写权限   
然后执行命令：   
mqbroker.cmd -n 127.0.0.1:9876 -c D:\Software\rocketmq-all-5.3.1-bin-release\conf\broker.conf autoCreateTopicEnable=true
成功后会弹出新窗口显示日志，若看到The broker[broker-a, 192.168.1.101:10911] boot success则启动成功   

如果希望本机启动多个Broker，可在broker.conf文件中设置不同的端口号，然后在启动命令中指定端口号：   
#### mqbroker.cmd -n 127.0.0.1:9876 -c D:\Software\rocketmq-all-5.3.1-bin-release\conf\broker.conf -p 10911 autoCreateTopicEnable=true   
#### mqbroker.cmd -n 127.0.0.1:9876 -c D:\Software\rocketmq-all-5.3.1-bin-release\conf\broker.conf -p 10912 autoCreateTopicEnable=true   
或者 定义新的broker1.conf, broker2.conf文件，然后启动命令中指定配置文件：   
#### mqbroker.cmd -n 127.0.0.1:9876 -c D:\Software\rocketmq-all-5.3.1-bin-release\conf\broker1.conf autoCreateTopicEnable=true   
#### mqbroker.cmd -n 127.0.0.1:9876 -c D:\Software\rocketmq-all-5.3.1-bin-release\conf\broker2.conf autoCreateTopicEnable=true   
broker1.conf和broker2.conf文件中需要设置不同的端口号和存储路径以及Broker name，比如  
##### Broker 名称
brokerName = broker-b
##### 端口号
listenPort=10921
##### 存储路径
storePathRootDir=D:/Software/rocketmq-all-5.3.1-bin-release/store1
storePathCommitLog=D:/Software/rocketmq-all-5.3.1-bin-release/store1/commitlog

## 二、内存配置调整（可选）
若遇到内存不足问题，需修改bin目录下的脚本文件：  
### runserver.cmd​
将默认内存参数调整为：    
#### set "JAVA_OPT=%JAVA_OPT% -server -Xms512m -Xmx512m -Xmn256m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=320m"    
### runbroker.cmd​
修改为：   
#### set "JAVA_OPT=%JAVA_OPT% -server -Xms512m -Xmx512m"    
调整后避免因默认内存过高导致启动失败    

## 三、功能验证
1.发送测试消息​   
在CMD中临时设置环境变量后执行：  
#### set NAMESRV_ADDR=127.0.0.1:9876   
#### tools.cmd org.apache.rocketmq.example.quickstart.Producer   
发送完成后会显示SendResult [sendStatus=SEND_OK]   
2.消费消息    
执行命令：    
#### tools.cmd org.apache.rocketmq.example.quickstart.Consumer    
消费者将持续监听消息，按CTRL+C可终止进程    

## 四、可视化控制台（可选）
若需监控服务状态，可下载 rocketmq-dashboard 2.0.0 版本的jar包并运行：   
#### java -jar rocketmq-dashboard-2.0.0.jar
访问 http://127.0.0.1:8080 即可查看Broker、Topic等实时数据

### 注意事项：    
不要关闭NameServer和Broker的CMD窗口，否则服务会终止     
JDK 17兼容RocketMQ 5.x版本，若遇到启动异常可检查JAVA_HOME环境变量是否指向JDK 17安装路径    
若需远程访问，需开放防火墙的9876（NameServer）、10909/10911（Broker）端口      
