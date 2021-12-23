# springcloud demo
    基于springcoud Finchley.SR1 版本搭建
    基于springboot 2.0.4.RELEASE 版本搭建
   - 添加了eureka-sever demo  
   - 添加了springboot-demo工程（还没改造成cloud项目）  
   - 使用父子模块管理
   - 优化了logback日志打印  
   - cloud logback 默认初始化2次（1.logback自身初始化 2.cloud初始化logback） 暂时不做修改
   
 # docker 部署 推送到dockerhost
   clean package -DskipTests -Pdocker -DdockerHost=http://111.230.24.31:2375 -f pom.xml
   
 # docker 部署 推送到dockerhost 推送到dockerhub 
   clean package -DskipTests -DpushImage -Pdocker -DdockerHost=http://111.230.24.31:2375 -f pom.xml

