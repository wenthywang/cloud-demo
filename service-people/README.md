# springbootDemo
spring boot 2.0 
restful 接口 demo
运行DemoApplication类即可
测试类放在com.example.hui.rest.RestControllerTest类中

# docker 部署 推送到dockerhost
clean package -DskipTests -Pdocker -DdockerHost=http://111.230.24.31:2375 -f pom.xml

# docker 部署 推送到dockerhost 推送到dockerhub 
clean package -DskipTests -DpushImage -Pdocker -DdockerHost=http://111.230.24.31:2375 -f pom.xml