# Spring Boot Demo application

## accounts-web (Frontend)

Simple Java demo app to simulate frontend  

Backend application is available at https://github.com/smonko/demo-accounts-application  

### Build with:
- SpringBoot 3.0.6
- Thymeleaf
- Java JDK 17


### How to use

Build with maven
```
$ mvn clean install -U 
```

Run
```
java -jar accounts-web-0.0.1-SNAPSHOT.jar
```

Run with config
```
java -jar  accounts-web-0.0.1-SNAPSHOT.jar --spring.config.location=../config/application.yml
```

### Feature flags
Configuration options to simulate featurea flags and toogle some functionality

```
features:
  canaryflag: true
  datastream: true
  dbdata: true
  messaging: false
```

**canaryflag** - no real function. Just to demonstrate canary deployment. Check */features*  
**datastream** - call backend api with built-in data set. Must be ***False*** if ***dbdata*** is activated.  
**dbdata** - call backend api with data stored in database. Must be ***False*** if ***datastream*** is activated.  
**messaging** - activate messaging simulation. NOT Implemented YET!  

### Service Discovery
By default application will register to consul agent. To disable this function modify application.yml:
```
spring:
  ...
  cloud:
    consul:
      enabled: false
      ...
      discovery:
        enebled: false
        ...
```

### Copyrights
(C) Stefan Monko (stefan@stefanmonko.sk / monkos@posam.sk)