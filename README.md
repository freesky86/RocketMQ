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
mqnamesrv.cmd   
成功后会弹出新窗口显示日志，若看到The Name Server boot success则启动成功   
2.启动Broker​   
在相同目录下执行（需指定NameServer地址）：mqbroker.cmd -n 127.0.0.1:9876 autoCreateTopicEnable=true     
参数autoCreateTopicEnable=true用于自动创建Topic   

## 二、内存配置调整（可选）
若遇到内存不足问题，需修改bin目录下的脚本文件：  
### runserver.cmd​
将默认内存参数调整为： 
set "JAVA_OPT=%JAVA_OPT% -server -Xms512m -Xmx512m -Xmn256m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=320m"    
### runbroker.cmd​
修改为：   
set "JAVA_OPT=%JAVA_OPT% -server -Xms512m -Xmx512m"    
调整后避免因默认内存过高导致启动失败    

## 三、功能验证
1.发送测试消息​   
在CMD中临时设置环境变量后执行：  
set NAMESRV_ADDR=127.0.0.1:9876   
tools.cmd org.apache.rocketmq.example.quickstart.Producer   
发送完成后会显示SendResult [sendStatus=SEND_OK]   
2.消费消息    
执行命令：    
tools.cmd org.apache.rocketmq.example.quickstart.Consumer    
消费者将持续监听消息，按CTRL+C可终止进程    

## 四、可视化控制台（可选）
若需监控服务状态，可下载rocketmq-dashboard的jar包并运行：   
java -jar rocketmq-dashboard-2.0.0.jar



