1## 提供方需要做的
1. 提供方share中增加自己的接口，以com.sinochem.crude.trade.模块名.remote为包名
1. 提供方在自己的工程中实现接口，以com.sinochem.crude.trade.模块名.remote.impl为包名
1. 提供方在项目目录下src/build/dubbo/dubbo-provide-beans.xml中增加自己的实现，并指定service接口
    ```xml
        <dubbo:service interface="接口名" group="${dubbo.group}" ref="实现类的name，一般为实现名小写第一个字母">      </dubbo:service>
    ```
1. 提供方使用dubbo这个profile，install自己的项目，并启动
## 调用方需要做的
1. 调用方在自己模块中依赖提供方share模块
1. 调用方项目目录下src/build/dubbo/dubbo-service-beans.xml中增加
 ```xml
        <dubbo:service interface="接口名" group="${dubbo.group}" ref="实现类的name，一般为实现名小写第一个字母">      </dubbo:service>
 ```
1. 在代码中使用@Autowired 注入相应接口,并进行调用即可
```java
 @Autowired
 private XXXService xxxService;
```
## 关于mock
为了便于本地调试，我们可以自己实现一个假接口，避免同时需要启动多个项目，或因为对方接口故障而延误工作。
要实现mock的功能，可以在调用方工程中com.sinochem.crude.trade.模块名.remote.mock中实现接口功能，不需要启动提供方工程即可继续开发。
使用maven的dev这个profile(或者什么不都不选，默认是dev)，可以进行开发。