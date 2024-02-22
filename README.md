# Multi Tenant Solution for SAAS (Micro Services)

Multienty integrates multiple mature middleware to provide you with a one-stop solution.

This project is based on Springboot, with a version requirement of ***2.6.13***. Please ensure that your development environment matches this project before use.


## Database

Multienty integrates ***Shardingsphere***, ***DynamicDatasource***, and ***Flyway***, providing stable support for database sharding, data encryption, data migration, multiple data sources, and read/write separation.

## Code Generate
In the controller of the basic library, we have encapsulated the ***/code/generate*** interface. Common CURD operations can generate code with just one click by calling this interface. Similarly, code generation rules can be configured in Nacos and support hot updates without the need to restart the application.

Multienty can recognize the sharding rules configured in ***shardingsphere*** and automatically generate sharding based source code.

In addition, we also provide various page code generation for backend management UI. you can see ***/vue/code/generate***

## More features
For more features, you can contact the author or read the source code yourself.
```
wx: kutasms
email:7437280@qq.com
```
## List of third-party components
- redis 
- druid 1.2.9
- dubbo 3.0.8
- dynamic datasource 4.2.0
- mybatis-plus 3.5.5
- redisson 3.9.1
- flyway 7.15.0
- nacos 2.2.0
- shardingsphere 5.2.1
- sentinel 1.8.6
- seata 1.6.1

## Get start

- maven
    ```
    <dependency>
        <groupId>com.chia</groupId>
        <artifactId>multienty-spring-boot-starter</artifactId>
        <version>2024.1.1</version>
    </dependency>
    ```

## Contribute

Welcome interested friends to participate and work together to improve this project.