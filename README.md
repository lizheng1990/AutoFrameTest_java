DEMO
===========================

###########环境依赖


###########部署步骤
1. 
2. 
3. 

###########目录结构描述
├── Readme.md                   // help
├── app                         // 应用
├── config                      // 配置
│   ├── default.json
│   ├── dev.json                // 开发环境
│   ├── experiment.json         // 实验
│   ├── index.js                // 配置控制
│   ├── local.json              // 本地
│   ├── production.json         // 生产环境
│   └── test.json               // 测试环境
├── data
├── doc                         // 文档
├── environment
├── gulpfile.js
├── locales
├── logger-service.js           // 启动日志配置
├── node_modules
├── package.json
├── app-service.js              // 启动应用配置
├── static                      // web静态资源加载
│   └── initjson
│       └── config.js         // 提供给前端的配置
├── test
├── test-service.js
└── tools



###########说明
1.如果chrome浏览器不是默认路径，则需要取消注释browerDriver下GoogleDriver中options.setBinary，并修改的浏览器程序位置
2.如果想取消chrome浏览器的缓存，则注释browerDriver下GoogleDriver中options.addArguments
3.如果Firfox浏览器的运行路径不是默认路径，则需要取消注释browerDriver下FirefoxDriver中System.setProperty对应的路径
4.如果要更新driver程序，路径为AutoTest\webDrivers
5.浏览器默认有一个10秒钟的隐式等待，如果先修改或取消，更改keyWord中类里面对应打开浏览器的方法的driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
6.读取excel文件内容的时候，有可能读出来认为单元格没有内容，是个null。不让它读成null，把excel中的单元格全部都添加一下框线。
7.appium浏览器自动化配置
    1、系统设置中找到系统应用，查到Android System Webview查看它的版本，然后下载对应的chromedriver版本。
    2、将对应的chromedriver放到appium，注意还有命令行版的appium（appium -help查看）的对应位置下：
        Appium安装位置\resources\app\node_modules\appium\node_modules\appium-chromedriver\chromedriver\win
        C:\Users\86158\AppData\Roaming\npm\node_modules\appium\node_modules\appium-chromedriver\chromedriver\win
8.http请求超时时间设置在createDrivers下InterDriverOfHttp中的请求方法中配置，默认为15秒。
9.接口测试中进行参数传递的格式为{{参数名}}

## Allure TestNG Example

### Getting Started

To generate Allure Report you should perform following steps:

```bash
$ git clone git@github.com:allure-examples/allure-testng-example.git
$ ./mvnw clean test site
```

Report will be generated to `target/site/allure-maven-plugin` folder. To open the report you can use the following command:

```bash
$ ./mvnw io.qameta.allure:allure-maven:serve
```

There is another way of generating the report. The generated report can be opened here "target/site/allure-maven-plugin/index.html". The command to generate the report is the following:

```bash
$ ./mvnw io.qameta.allure:allure-maven:report
```

### More

* [Documentation](https://docs.qameta.io/allure/2.0/)
* [Issue Tracking](https://github.com/allure-framework/allure2/issues?labels=&milestone=&page=1&state=open)
* Gitter chat room: [https://gitter.im/allure-framework/allure-core](https://gitter.im/allure-framework/allure-core)
* StackOverflow tag: [Allure](http://stackoverflow.com/questions/tagged/allure)