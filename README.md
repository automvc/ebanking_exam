E-Banking Exam  
=========  

# Introduction of projects involved
## 1.banking  
ebanking service  

## 2.rate  
exchange rate (simulate from the third-porry provider)  

## 3.eureka-server  
Service register and discovery  

## 4.feign-consumer  
services load balance.  

# Starting Steps  
spring cloud微服务启动  
涉及服务注册，服务发现，负载均衡，服务消费。  
## 1. 启动注册服务器(eureka-server)  
java -jar eureka-server-0.8.jar --spring.profiles.active=one  
java -jar eureka-server-0.8.jar --spring.profiles.active=two  
两台启动不要间隔太久，才能都看见对方。  
测试时,也需要启动.  
  
## 2. 启动第三方汇率服务  
要修改数据库连接信息,在applicationContext.xml	  
默认是mysql,不同数据库要作相应修改  
property name="url" value="jdbc:mysql://127.0.0.1:3306/bee?characterEncoding=UTF-8&amp;useSSL=false"/>  

default database name:bee  
username:root  
password:123456  
  
访问url:http://localhost:8089/rate  
获取某天的汇率列表:  
http://localhost:8089/rate/17-07-2022  
  
## 3. 启动服务提供者  
要修改数据库连接信息,在applicationContext.xml	  
默认是mysql,不同数据库要作相应修改  
property name="url" value="jdbc:mysql://127.0.0.1:3306/bee?characterEncoding=UTF-8&amp;useSSL=false"/>  
启动不同端口的多个应用  
jar的springcloud-service要改成自己定义的工程名称  
java -jar springcloud-service-0.8.jar --server.port=8085  
java -jar springcloud-service-0.8.jar --server.port=8086  
如,工程名称改为banking:  
java -jar banking-0.8.jar --server.port=8085  
java -jar banking-0.8.jar --server.port=8086  
  
直接访问(test):http://localhost:8085/account/Kingstar/06-2022  
  
## 4. 启动服务消费者(feign-consumer)  
feign-consumer  
  
## 5. 访问服务消费者工程的url  
http://localhost:9011/account/kingstar/07-2022  
会交替访问两个服务提供者  

# Document & Diagrams  
#### Architecture Diagram  
#### Test Diagram  
#### Swagger Diagram  

[Doc link](https://github.com/automvc/ebanking_exam/tree/master/Doc)  
[Doc link in gitee](https://gitee.com/automvc/ebanking_exam/tree/master/Doc)  

# Query optimization description  
Add index to frequently used query fields,as account table add index on (client_id,month_year),  
exchange table add index on (date).  
Use cache for Stable data.  
put the exchange rate data in Hash structure ,time complexity is O(1).


