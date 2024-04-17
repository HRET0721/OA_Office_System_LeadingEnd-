## 用于构建一个基于openjdk:8-jdk-alpine基础镜像的Docker容器
FROM openjdk:17-jdk-alpine
## 设置作者
LABEL authors="HRET 2126366332@qq.com"

## 添加jar包 oa-system-web-1.0.jar是项目打包后的jar包
## oa-system.jar 是在构建镜像里面的名称
ADD target/oa-system-web-1.0.jar oa-system.jar

## 添加端口
EXPOSE 9999

## 设置启动命令
ENTRYPOINT ["java", "-Dfile.encoding=UTF-8", "-jar", "/oa-system.jar"]