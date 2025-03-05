# RocketMQ
Spring Boot + RocketMQ

RocketMQ下载地址
https://rocketmq.apache.org/download/

D:\Software\rocketmq-all-5.3.1-bin-release
JDK17

## Windows系统启动
# 一、服务启动步骤
1.启动NameServer​
打开CMD进入bin目录，执行命令：mqnamesrv.cmd
成功后会弹出新窗口显示日志，若看到The Name Server boot success则启动成功
2.启动Broker​
在相同目录下执行（需指定NameServer地址）：mqbroker.cmd -n 127.0.0.1:9876 autoCreateTopicEnable=true
参数autoCreateTopicEnable=true用于自动创建Topic

# 二、内存配置调整（可选）

# 三、功能验证
