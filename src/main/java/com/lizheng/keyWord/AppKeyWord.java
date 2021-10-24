package com.lizheng.keyWord;

import com.google.common.io.Files;
import com.lizheng.common.AutoLogger;
import com.lizheng.createDrivers.AppDriverOfAndroid;
import com.lizheng.createDrivers.AppDriverOfBrowser;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.functions.ExpectedCondition;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class AppKeyWord {
    // 声明成员变量driver，在所有的方法当中都需要调用
    public AndroidDriver driver;

    public AndroidDriver getDriver(){
        return driver;
    }

    /**
     * 执行cmd命令
     * @param CMDstr
     */
    public void runCmd(String CMDstr){
        String cmd = "cmd /c start " + CMDstr;
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(cmd);
            AutoLogger.log.info("执行cmd命令： " + CMDstr + "成功！！！");
        } catch (IOException e) {
            AutoLogger.log.error("执行cmd命令： " + CMDstr + "失败！！！");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
    }

    /**
     * 通过cmd命令启动appium命令行版
     * @param port 指定的appium端口
     * @param time 指定启动appium的等待时间，单位秒
     */
    public void startAppium(String port,String time){
        try {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd+HH-mm-ss");
            // 当前时间的字符串
            String createdate = sdf.format(date);
            // 拼接文件名，形式为：工作目录路径+执行时间+AppiumLog.txt
            String appiumLogFile = "logs/" + createdate + "AppiumLog.txt";
            // -g 参数指定日志文件保存的路径  ——local-timezone指定本地时间  --log-timestamp 日志中记录时间戳
            String startAppiumCMD = "appium -p " + port + " -g " + appiumLogFile + " --local-timezone --log-timestamp";
            runCmd(startAppiumCMD);
            int t = 1000;
            t = Integer.parseInt(time);
            Thread.sleep(t * 1000);
            AutoLogger.log.info("在"+port+"端口启动appiumserver服务并等待" + time + "秒钟成功！！！");
        } catch (Exception e) {
            AutoLogger.log.error("在"+port+"端口启动appiumserver服务并等待" + time + "秒钟失败！！！");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
    }

    /**
     * 启动待测app
     * @param device deviceName，通过adb devices获取
     * @param version 设备版本号
     * @param appPackage app包名
     * @param appActivity appActivity
     * @param appiumServerPort  需要连接的appium端口，与startAppium中的端口号一致
     * @param time 需要等到app启动的时间，单位秒
     */
    public void runAPP(String device, String version, String appPackage, String appActivity,
                       String appiumServerPort, String time) {
        try {
            AppDriverOfAndroid app = new AppDriverOfAndroid(device,version,appPackage,appActivity,appiumServerPort);
            driver = app.getDriver();
            //等待app应用完成启动
            int t = 1000;
            t = Integer.parseInt(time);
            Thread.sleep(t * 1000);
            AutoLogger.log.info("启动待测App成功！！！");
        } catch (Exception e) {
            AutoLogger.log.error("启动待测App失败！！！");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
    }

    /**
     * 启动app自带浏览器
     * @param device
     * @param version
     * @param appiumServerPort
     * @param time 需要等到app启动的时间，单位秒
     */
    public void runBrowser(String device, String version, String appiumServerPort, String time){
        try {
            AppDriverOfBrowser appBro = new AppDriverOfBrowser(device, version, appiumServerPort);
            driver = appBro.getDriver();
            //等待app应用完成启动
            int t = 1000;
            t = Integer.parseInt(time);
            Thread.sleep(t * 1000);
            AutoLogger.log.info("启动app自带浏览器成功！！！");
        } catch (Exception e) {
            AutoLogger.log.error("启动app自带浏览器失败！！！");
            AutoLogger.log.error(e, e.fillInStackTrace());
            saveScrShot("启动APP自带浏览器");
        }
    }

    /**
     * 访问url
     * @param url 需要带上http/https头
     */
    public void visitURL(String url){
        try {
            driver.get(url);
            AutoLogger.log.info("访问网页" + url + "成功！！！");
        } catch (Exception e) {
            AutoLogger.log.error("访问网页" + url + "失败！！！");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
    }

    /**
     * 强制等待：传入单位为秒
     * @param timeout 传入时间字符串，会默认转换为毫秒
     */
    public void mustWait(String timeout){
        try {
            float seconds = Float.parseFloat(timeout);
            int millis = (int)(seconds*1000);
            Thread.sleep(millis);
            AutoLogger.log.info("强制等待" + timeout + "秒成功！！！");
        } catch (InterruptedException e) {
            AutoLogger.log.error("强制等待" + timeout + "秒失败！！！");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
    }

    /**
     * 隐式等待：等待页面在对应的时间加载完毕,传入时间单位为秒，调用一次，整个运行周期有效
     * @param timeout
     */
    public void implicitWait(String timeout){
        int seconds = Integer.parseInt(timeout);
        try {
            driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
            AutoLogger.log.info("隐式等待" + timeout + "秒成功！！！");
        } catch (Exception e) {
            AutoLogger.log.error("隐式等待" + timeout + "秒失败！！！");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
    }


    /**
     * 显示等待某一个元素可用
     * @param xpath
     * @param timeout 超时时间，单位秒
     */
    public void waitForXpath(String xpath,String timeout){
        try {
            int seconds = Integer.parseInt(timeout);
            WebDriverWait wdw = new WebDriverWait(driver,seconds);
            wdw.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
            AutoLogger.log.info("显示等待xpath\"" + xpath + "\"" + timeout + "秒成功！！！");
        } catch (Exception e) {
            AutoLogger.log.error("显示等待xpath\"" + xpath + "\"" + timeout + "秒失败！！！");
            saveScrShot("显示等待原始可用失败");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
    }

    /**
     * 显示等待某一个元素文本内容是否匹配
     * @param xpath
     * @param expected 预期包含内容
     * @param timeout 超时时间，单位秒
     */
    public void waitForContent(String xpath,String expected,String timeout){
        try {
            int seconds = Integer.parseInt(timeout);
            WebDriverWait wdw = new WebDriverWait(driver,seconds);
            wdw.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(xpath),expected));
            AutoLogger.log.info("显示等待xpath\"" + xpath + "\"的内容\"" + expected + "\"" + timeout + "秒成功！！！");
        } catch (Exception e) {
            AutoLogger.log.error("显示等待xpath\"" + xpath + "\"的内容\"" + expected + "\"" + timeout + "秒失败！！！");
            saveScrShot("显示等待元素文本内容可用失败");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
    }

    /**
     * 调用adb命令点击指定坐标
     * @param xAxis
     * @param yAxis
     */
    public void adbTap(String xAxis, String yAxis) {
        try {
            runCmd("adb shell input tap " + xAxis + " " + yAxis);
            AutoLogger.log.info("通过adb点击(" + xAxis + "," + yAxis + ")成功！！！");
        } catch (Exception e) {
            AutoLogger.log.error("通过adb点击(" + xAxis + "," + yAxis + ")失败！！！");
            AutoLogger.log.error(e, e.fillInStackTrace());
            saveScrShot("调用adb命令点击指定坐标");
        }
    }

    /**
     * 调用adb滑动
     * @param xstart
     * @param yStart
     * @param xEnd
     * @param yEnd
     * @param seconds 滑动需要的时间，单位秒
     */
    public void adbSwipe(String xstart, String yStart, String xEnd, String yEnd, String seconds) {
        try {
            float time = Float.parseFloat(seconds);
            int millis = (int)(time*1000);
            this.runCmd("adb shell input swipe " + xstart + " " + yStart + " " + xEnd + " " + yEnd + " " + millis);
            AutoLogger.log.info("通过adb执行滑动成功！！！");
        } catch (Exception e) {
            AutoLogger.log.error("通过adb执行滑动失败!!!");
            AutoLogger.log.error(e, e.fillInStackTrace());
            saveScrShot("调用adb滑动");
        }
    }

    /**
     * 通过adb命令输入文本
     * @param text
     */
    public void adbText(String text) {
        try {
            runCmd("adb shell input text "+text);
            AutoLogger.log.info("通过adb命令输入文本成功！！！");
        }catch (Exception e){
            AutoLogger.log.error("通过adb命令输入文本失败!!!");
            AutoLogger.log.error(e, e.fillInStackTrace());
            saveScrShot("通过adb命令输入文本");
        }
    }

    /**
     * 调用adb模拟按键
     * @param keycode
     */
    public void adbPressKey(String keycode) {
        try {
            int k = Integer.parseInt(keycode);
            String cmd = " adb shell input keyevent " + k;
            runCmd(cmd);
            Thread.sleep(1000);
            AutoLogger.log.error("通过adb执行按键事件成功！！！");
        } catch (Exception e) {
            AutoLogger.log.error("通过adb执行按键事件失败！！！");
            AutoLogger.log.error(e, e.fillInStackTrace());
            saveScrShot("调用adb模拟按键");
        }
    }

    /**
     * 通过appium的方法进行滑屏
     * @param iniX
     * @param iniY
     * @param finX
     * @param finY
     */
    public void appiumSwipe(String iniX, String iniY, String finX, String finY) {
        try {
            // string型的参数转换为int型
            int x = Integer.parseInt(iniX);
            int y = Integer.parseInt(iniY);
            int x1 = Integer.parseInt(finX);
            int y1 = Integer.parseInt(finY);
            TouchAction action = new TouchAction(driver);
            // 设置起点和终点
            PointOption pressPoint = PointOption.point(x, y);
            PointOption movePoint = PointOption.point(x1, y1);
            //设置动作持续时间：按压500毫秒
            WaitOptions waitOption = WaitOptions.waitOptions(Duration.ofMillis(500));
            // 滑动操作由按压500毫秒->移动到终点->松开组成。
            action.press(pressPoint).waitAction(waitOption).moveTo(movePoint).release().perform();
            AutoLogger.log.info("执行Appium滑动方法成功！！！");
        } catch (Exception e) {
            AutoLogger.log.error("执行Appium滑动方法失败！！！");
            AutoLogger.log.error(e, e.fillInStackTrace());
            saveScrShot("通过appium的方法进行滑屏");
        }
    }

    /**
     * 使用appium的方法点击坐标
     * @param x
     * @param y
     */
    public void appiumTap(String x, String y) {
        try {
            int xAxis = Integer.parseInt(x);
            int yAxis = Integer.parseInt(y);
            TouchAction action = new TouchAction(driver);
            PointOption pressPoint = PointOption.point(xAxis, yAxis);
            // action类分解动作，先点击，然后松开
            action.tap(pressPoint).release().perform();
            AutoLogger.log.info("执行Appium点击坐标方法成功！！！");
        } catch (NumberFormatException e) {
            AutoLogger.log.error("执行Appium点击坐标方法失败！！！");
            AutoLogger.log.error(e, e.fillInStackTrace());
            saveScrShot("使用appium的方法点击坐标");
        }
    }

    /**
     * 通过xpath定位并点击元素
     * @param xpath
     */
    public void click(String xpath){
        try {
            driver.findElementByXPath(xpath).click();
            AutoLogger.log.info("点击xpath元素：\"" + xpath + "\"成功！！！");
        } catch (Exception e) {
            AutoLogger.log.error("点击xpath元素：\"" + xpath + "\"失败！！！");
            AutoLogger.log.error(e, e.fillInStackTrace());
            saveScrShot("通过xpath定位并点击元素");
        }
    }

    /**
     * 通过id定位并点击元素
     * @param id
     */
    public void clickById(String id){
        try {
            driver.findElementById(id).click();
            AutoLogger.log.info("点击元素id：\"" + id + "\"成功！！！");
        } catch (Exception e) {
            AutoLogger.log.error("点击元素id：\"" + id + "\"失败！！！");
            AutoLogger.log.error(e, e.fillInStackTrace());
            saveScrShot("通过id定位并点击元素");
        }
    }

    /**
     * 通过AccessibilityId定位并点击元素
     * @param accid
     */
    public void clickByAccId(String accid){
        try {
            driver.findElementByAccessibilityId(accid).click();
            AutoLogger.log.info("点击元素id：\"" + accid + "\"成功！！！");
        } catch (Exception e) {
            AutoLogger.log.error("点击元素id：\"" + accid + "\"失败！！！");
            AutoLogger.log.error(e, e.fillInStackTrace());
            saveScrShot("通过AccessibilityId定位并点击元素");
        }
    }

    /**
     * 使用appium方法长按坐标
     * @param xpoint
     * @param ypoint
     * @param seconds 单位秒
     */
    public void appiumHold(String xpoint, String ypoint, String seconds) {
        try {
            // string转int
            int xAxis = Integer.parseInt(xpoint);
            int yAxis = Integer.parseInt(ypoint);
            int t = Integer.parseInt(seconds);
            // 指定长按的坐标
            PointOption pressPoint = PointOption.point(xAxis, yAxis);
            // 长按的时间，使用java提供的time类库中的duration类
            Duration last = Duration.ofSeconds(t);
            WaitOptions wait = WaitOptions.waitOptions(last);
            TouchAction action = new TouchAction(driver);
            // 长按坐标->指定按住的时间进行等待
            action.longPress(pressPoint).waitAction(WaitOptions.waitOptions(last)).perform();
            AutoLogger.log.info("执行Appium滑动方法成功！！！");
        } catch (NumberFormatException e) {
            AutoLogger.log.error("执行Appium滑动方法失败！！！");
            AutoLogger.log.error(e, e.fillInStackTrace());
            saveScrShot("使用appium方法长按坐标");
        }
    }

    /**
     * 长按页面上的某一个xpath元素
     * @param xpath
     * @param seconds 单位秒
     */
    public void appiumHold(String xpath, String seconds) {
        try {
            int t = Integer.parseInt(seconds);
            Duration last = Duration.ofSeconds(t);
            TouchAction action = new TouchAction(driver);
            // 定位到一个元素，并且在该元素上长按指定的时长
            action.longPress(LongPressOptions.longPressOptions()
                    .withElement(ElementOption.element(driver.findElementByXPath(xpath))).withDuration(last))
                    .waitAction(WaitOptions.waitOptions(last)).perform();
            AutoLogger.log.info("点击xpath元素\"" + xpath + "\"" + seconds + "秒成功！！！");
        } catch (NumberFormatException e) {
            AutoLogger.log.error("点击xpath元素\"" + xpath + "\"" + seconds + "秒失败！！！");
            AutoLogger.log.error(e, e.fillInStackTrace());
            saveScrShot("长按页面上的某一个xpath元素");
        }
    }

    /**
     * 通过xpath定位并清除内容
     * @param xpath
     */
    public void clear(String xpath){
        try {
            driver.findElementByXPath(xpath).clear();
            AutoLogger.log.info("通过xpath：\"" + xpath + "\"定位并清除内容成功！！！");
        } catch (Exception e) {
            AutoLogger.log.error("通过xpath：\"" + xpath + "\"定位并清除内容失败！！！");
            AutoLogger.log.error(e, e.fillInStackTrace());
            saveScrShot("通过xpath定位并清除内容");
        }
    }

    /**
     * 通过id定位并清除内容
     * @param id
     */
    public void clearById(String id){
        try {
            driver.findElementById(id).clear();
            AutoLogger.log.info("通过id：\"" + id + "\"定位并清除内容成功！！！");
        } catch (Exception e) {
            AutoLogger.log.error("通过id：\"" + id + "\"定位并清除内容失败！！！");
            AutoLogger.log.error(e, e.fillInStackTrace());
            saveScrShot("通过id定位并清除内容");
        }
    }

    /**
     * 通过通AccessibilityId元素定位并清除内容
     * @param accid
     */
    public void clearByAccId(String accid){
        try {
            driver.findElementByAccessibilityId(accid).clear();
            AutoLogger.log.info("通过AccessibilityId：\"" + accid + "\"定位并清除内容成功！！！");
        } catch (Exception e) {
            AutoLogger.log.error("通过AccessibilityId：\"" + accid + "\"定位并清除内容失败！！！");
            AutoLogger.log.error(e, e.fillInStackTrace());
            saveScrShot("通过通AccessibilityId元素定位并清除内容");
        }
    }

    /**
     * 通过xpath定位并输入内容
     * @param xpath
     * @param content
     */
    public void input(String xpath,String content){
        try {
            driver.findElementByXPath(xpath).clear();
            driver.findElementByXPath(xpath).sendKeys(content);
            AutoLogger.log.info("通过xpath\"" + xpath + "\"定位并输入内容\"" + content + "\"成功！！！");
        } catch (Exception e) {
            AutoLogger.log.error("通过xpath\"" + xpath + "\"定位并输入内容\"" + content + "\"失败！！！");
            AutoLogger.log.error(e, e.fillInStackTrace());
            saveScrShot("通过xpath定位并输入内容");
        }
    }

    /**
     * 通过id定位并输入内容
     * @param id
     * @param content
     */
    public void inputById(String id,String content){
        try {
            driver.findElementById(id).clear();
            driver.findElementById(id).sendKeys(content);
            AutoLogger.log.info("通过id\"" + id + "\"定位并输入内容\"" + content + "\"成功！！！");
        } catch (Exception e) {
            AutoLogger.log.error("通过id\"" + id + "\"定位并输入内容\"" + content + "\"失败！！！");
            AutoLogger.log.error(e, e.fillInStackTrace());
            saveScrShot("通过id定位并输入内容");
        }
    }

    /**
     * 通过AccessibilityId定位并输入内容
     * @param accid
     * @param content
     */
    public void inputByAccId(String accid,String content){
        try {
            driver.findElementByAccessibilityId(accid).clear();
            driver.findElementByAccessibilityId(accid).sendKeys(content);
            AutoLogger.log.info("通过AccessibilityId\"" + accid + "\"定位并输入内容\"" + content + "\"成功！！！");
        } catch (Exception e) {
            AutoLogger.log.error("通过AccessibilityId\"" + accid + "\"定位并输入内容\"" + content + "\"失败！！！");
            AutoLogger.log.error(e, e.fillInStackTrace());
            saveScrShot("通过AccessibilityId定位并输入内容");
        }
    }

    /**
     * 刷新当前页面
     */
    public void refresh(){
        try {
            driver.navigate().refresh();
            driver.navigate().refresh();
            AutoLogger.log.info("刷新当前页面成功！！！");
        } catch (Exception e) {
            AutoLogger.log.error("刷新当前页面失败！！！");
            saveScrShot("刷新当前页面");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
    }

    /**
     * 获取当前手机中所有的context并输出
     */
    public void printcontexts() {
        AutoLogger.log.info(driver.getContextHandles());
    }

    /**
     * 切换context
     *
     * @param contextName
     */
    public void switchContext(String contextName) {
        try {
            driver.context(contextName);
            AutoLogger.log.info("切换context到" + contextName + "成功！！！");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            AutoLogger.log.error("切换context到" + contextName + "失败！！！");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
    }

    /**
     * 实现显式等待的方法
     * @param xpath
     * @param seconds 单位秒
     */
    public void explicityWait(String xpath,String seconds) {
        try {
            int time = Integer.parseInt(seconds);
            WebDriverWait eWait = new WebDriverWait(driver, time);
            eWait.until(new ExpectedCondition<WebElement>() {
                public WebElement apply(WebDriver d) {
                    return d.findElement(By.xpath(xpath));
                }
            });
            AutoLogger.log.info("显示等待成功！！！");
        } catch (Exception e) {
            AutoLogger.log.error("显示等待失败！！！");
            AutoLogger.log.error(e, e.fillInStackTrace());
            saveScrShot("实现显式等待的方法");
        }
    }

    /**
     * 错误截图
     * @param method
     */
    public void saveScrShot(String method) {
        // 获取当前的执行时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        // 当前时间的字符串
        String createdate = sdf.format(date);
        // 拼接文件名，形式为：工作目录路径+方法名+执行时间.png
        String scrName = "logs/screenshot/" + createdate + method + ".png";
        // 以当前文件名创建文件
        File scrShot = new File(scrName);
        // 将截图保存到临时文件
        File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(tmp, scrShot);
        } catch (IOException e) {
            AutoLogger.log.error("截图失败！！！");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
    }

    /**
     * 关闭手机浏览器
     */
    public void closeBrowser() {
        try {
            driver.quit();
            AutoLogger.log.info("关闭手机浏览器成功！！！");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            AutoLogger.log.error("关闭手机浏览器失败！！！");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
    }

    /**
     * 关闭app
     */
    public void quitApp() {
        try {
            driver.closeApp();
            AutoLogger.log.info("关闭app成功！！！");
        } catch (Exception e) {
            AutoLogger.log.error("关闭app失败！！！");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
    }

    /**
     * 通过taskkill命令杀掉appiumserver的进程。
     */
    public void killAppium() {
        try {
            runCmd("taskkill /F /IM node.exe");
            AutoLogger.log.info("关闭appiumserver服务成功！！！");
        } catch (Exception e) {
            AutoLogger.log.error("关闭appiumserver服务失败！！！");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
    }

}
