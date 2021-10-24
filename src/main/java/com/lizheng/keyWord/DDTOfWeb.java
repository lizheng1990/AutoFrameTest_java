package com.lizheng.keyWord;

import com.google.common.io.Files;
import com.lizheng.createDrivers.WebDriverOfFF;
import com.lizheng.createDrivers.WebDriverOfGoogle;
import com.lizheng.common.AutoLogger;
import com.lizheng.common.ExcelWriter;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class DDTOfWeb {
    //成员变量driver，用于各个方法使用浏览器对象进行操作。
    public WebDriver driver;
    //excelwriter对象，用于每个方法执行的时候，完成结果的写入。
    public ExcelWriter results;
    //用于记录当前操作的行号
    public int writeLine;
    //记录写入的列，固定为10
    public static final int RES_COL = 10;
    public static final String PASS = "pass";
    public static final String FAIL = "fail";

    //传参完成excelwriter对象的赋值。
    public DDTOfWeb(ExcelWriter result) {
        results = result;
    }

    //设置当前操作的行号
    public void setLine(int line) {
        writeLine = line;
    }

    /**
     * 打开浏览器：通过传入参数browerType，指定浏览器类型，默认为chrome；并且默认有10秒钟的隐式等待
     *
     * @param browerType 浏览器类型，chrome和firefox
     */
    public void openBrowser(String browerType) {
        try {
            switch (browerType){
                case "chrome":
                    WebDriverOfGoogle googleBrower = new WebDriverOfGoogle();
                    driver = googleBrower.getdriver();
                    //设置一个10秒钟的隐式等待
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    results.writeCell(writeLine, RES_COL, PASS);
                    break;
                case "firefox":
                    WebDriverOfFF FirefoxBrower = new WebDriverOfFF();
                    driver = FirefoxBrower.getdriver();
                    //设置一个10秒钟的隐式等待
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    results.writeCell(writeLine, RES_COL, PASS);
                    break;
                default:
                    WebDriverOfGoogle defaultBrower = new WebDriverOfGoogle();
                    driver = defaultBrower.getdriver();
                    //设置一个10秒钟的隐式等待
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    results.writeCell(writeLine, RES_COL, PASS);
                    break;
            }
            AutoLogger.log.info("打开" + browerType + "浏览器成功！！！");
        } catch (Exception e) {
            results.writeFailCell(writeLine, RES_COL, FAIL);
            AutoLogger.log.error("打开" + browerType + "浏览器失败！！！");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
    }

    /**
     * 强制等待：传入单位为秒
     *
     * @param timeout 传入时间字符串，会默认转换为毫秒
     */
    public void mustWait(String timeout) {
        float seconds = Float.parseFloat(timeout);
        int millis = (int) (seconds * 1000);
        try {
            Thread.sleep(millis);
            results.writeCell(writeLine, RES_COL, PASS);
            AutoLogger.log.info("强制等待" + timeout + "秒成功！！！");
        } catch (InterruptedException e) {
            results.writeFailCell(writeLine, RES_COL, FAIL);
            AutoLogger.log.error("强制等待" + timeout + "秒失败！！！");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
    }

    /**
     * 隐式等待：等待页面在对应的时间加载完毕,传入时间单位为秒，调用一次，整个运行周期有效
     *
     * @param timeout
     */
    public void implicitWait(String timeout) {
        int seconds = Integer.parseInt(timeout);
        try {
            driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
            results.writeCell(writeLine, RES_COL, PASS);
            AutoLogger.log.info("隐式等待" + timeout + "秒成功！！！");
        } catch (Exception e) {
            results.writeFailCell(writeLine, RES_COL, FAIL);
            AutoLogger.log.error("隐式等待" + timeout + "秒失败！！！");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
    }


    /**
     * 显示等待某一个元素可用
     *
     * @param xpath
     * @param timeout 超时时间，单位秒
     */
    public void waitForXpath(String xpath, String timeout) {
        try {
            int seconds = Integer.parseInt(timeout);
            WebDriverWait wdw = new WebDriverWait(driver, seconds);
            wdw.until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver webDriver) {
                    return webDriver.findElement(By.xpath(xpath)).isEnabled();
                }
            });
            results.writeCell(writeLine, RES_COL, PASS);
            AutoLogger.log.info("显示等待xpath\"" + xpath + "\"" + timeout + "秒成功！！！");
        } catch (NumberFormatException e) {
            results.writeFailCell(writeLine, RES_COL, FAIL);
            AutoLogger.log.error("显示等待xpath\"" + xpath + "\"" + timeout + "秒失败！！！");
            saveErrShot("显示等待原始可用失败");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
    }

    /**
     * 显示等待某一个元素文本内容是否匹配
     *
     * @param xpath
     * @param expected 预期包含内容
     * @param timeout  超时时间，单位秒
     */
    public void waitForContent(String xpath, String expected, String timeout) {
        try {
            int seconds = Integer.parseInt(timeout);
            WebDriverWait wdw = new WebDriverWait(driver, seconds);
            wdw.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(xpath), expected));
            results.writeCell(writeLine, RES_COL, PASS);
            AutoLogger.log.info("显示等待xpath\"" + xpath + "\"的内容\"" + expected + "\"" + timeout + "秒成功！！！");
        } catch (NumberFormatException e) {
            results.writeFailCell(writeLine, RES_COL, FAIL);
            AutoLogger.log.error("显示等待xpath\"" + xpath + "\"的内容\"" + expected + "\"" + timeout + "秒失败！！！");
            saveErrShot("显示等待元素文本内容可用失败");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
    }

    /**
     * 设置浏览器分辨率
     *
     * @param width
     * @param height
     */
    public void setBrowerSize(String width, String height) {
        try {
            int xwidth = Integer.parseInt(width);
            int yheight = Integer.parseInt(height);
            driver.manage().window().setSize(new Dimension(xwidth, yheight));
            results.writeCell(writeLine, RES_COL, PASS);
            AutoLogger.log.info("设置浏览器分辨率为：" + width + "*" + height + "成功！！！");
        } catch (NumberFormatException e) {
            results.writeFailCell(writeLine, RES_COL, FAIL);
            AutoLogger.log.error("设置浏览器分辨率为：" + width + "*" + height + "失败！！！");
            saveErrShot("设置浏览器分别率失败");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
    }

    /**
     * 最大化浏览器
     */
    public void maxBrower() {
        try {
            driver.manage().window().maximize();
            results.writeCell(writeLine, RES_COL, PASS);
            AutoLogger.log.info("最大化浏览器成功！！！");
        } catch (Exception e) {
            results.writeFailCell(writeLine, RES_COL, FAIL);
            AutoLogger.log.error("最大化浏览器失败！！！");
            saveErrShot("最大化浏览器失败");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
    }

    /**
     * 访问url
     *
     * @param url 需要带上http/https头
     */
    public void visitURL(String url) {
        try {
            driver.get(url);
            results.writeCell(writeLine, RES_COL, PASS);
            AutoLogger.log.info("访问网页" + url + "成功！！！");
        } catch (Exception e) {
            results.writeFailCell(writeLine, RES_COL, FAIL);
            AutoLogger.log.error("访问网页" + url + "失败！！！");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
    }

    /**
     * 获取页面标题
     *
     * @return
     */
    public String getTitle() {
        String title = "";
        try {
            title = driver.getTitle();
            AutoLogger.log.info("页面标题为： " + driver.getTitle());
            results.writeCell(writeLine, RES_COL, PASS);
            AutoLogger.log.info("获取页面标题成功！！！");
        } catch (Exception e) {
            results.writeFailCell(writeLine, RES_COL, FAIL);
            AutoLogger.log.error("获取页面标题失败！！！");
            saveErrShot("获取页面标题失败");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
        return title;
    }

    /**
     * 通过xpath定位并点击元素
     *
     * @param xpath
     */
    public void click(String xpath) {
        try {
            driver.findElement(By.xpath(xpath)).click();
            results.writeCell(writeLine, RES_COL, PASS);
            AutoLogger.log.info("点击xpath元素：\"" + xpath + "\"成功！！！");
        } catch (Exception e) {
            results.writeFailCell(writeLine, RES_COL, FAIL);
            AutoLogger.log.error("点击xpath元素：\"" + xpath + "\"失败！！！");
            saveErrShot("通过xpath定位点击元素失败");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
    }

    /**
     * 通过xpath定位并双击元素
     *
     * @param xpath
     */
    public void doubleClick(String xpath) {
        try {
            Actions actions = new Actions(driver);
            actions.doubleClick(driver.findElement(By.xpath(xpath))).perform();
            results.writeCell(writeLine, RES_COL, PASS);
            AutoLogger.log.info("双击xpath元素：\"" + xpath + "\"成功！！！");
        } catch (Exception e) {
            results.writeFailCell(writeLine, RES_COL, FAIL);
            AutoLogger.log.error("双击xpath元素：\"" + xpath + "\"失败！！！");
            saveErrShot("通过xpath定位元素双击失败");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
    }

    /**
     * 通过xpath定位并清除内容
     *
     * @param xpath
     */
    public void clear(String xpath) {
        try {
            driver.findElement(By.xpath(xpath)).clear();
            results.writeCell(writeLine, RES_COL, PASS);
            AutoLogger.log.info("通过xpath：\"" + xpath + "\"定位并清除内容成功！！！");
        } catch (Exception e) {
            results.writeFailCell(writeLine, RES_COL, FAIL);
            AutoLogger.log.error("通过xpath：\"" + xpath + "\"定位并清除内容失败！！！");
            saveErrShot("通过xpath定位并清除元素失败");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
    }

    /**
     * 通过xpath定位并输入内容
     *
     * @param xpath
     * @param content
     */
    public void input(String xpath, String content) {
        try {
            driver.findElement(By.xpath(xpath)).clear();
            driver.findElement(By.xpath(xpath)).sendKeys(content);
            results.writeCell(writeLine, RES_COL, PASS);
            AutoLogger.log.info("通过xpath\"" + xpath + "\"定位并输入内容\"" + content + "\"成功！！！");
        } catch (Exception e) {
            results.writeFailCell(writeLine, RES_COL, FAIL);
            AutoLogger.log.error("通过xpath\"" + xpath + "\"定位并输入内容\"" + content + "\"失败！！！");
            saveErrShot("通过xpath定位并输入内容");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
    }

    /**
     * 刷新当前页面
     */
    public void refresh() {
        try {
            driver.navigate().refresh();
            results.writeCell(writeLine, RES_COL, PASS);
            AutoLogger.log.info("刷新当前页面成功！！！");
        } catch (Exception e) {
            results.writeFailCell(writeLine, RES_COL, FAIL);
            AutoLogger.log.error("刷新当前页面失败！！！");
            saveErrShot("刷新当前页面");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
    }

    /**
     * 鼠标悬停到xpath元素
     *
     * @param xpath
     */
    public void hover(String xpath) {
        try {
            Actions act = new Actions(driver);
            act.moveToElement(driver.findElement(By.xpath(xpath))).perform();
            results.writeCell(writeLine, RES_COL, PASS);
            AutoLogger.log.info("悬停到xpath\"" + xpath + "\"成功！！！");
        } catch (Exception e) {
            results.writeFailCell(writeLine, RES_COL, FAIL);
            AutoLogger.log.error("悬停到xpath\"" + xpath + "\"失败！！！");
            saveErrShot("鼠标悬停失败");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
    }

    /**
     * 直接切换窗口
     * 适用于只有两个窗口的情况
     */
    public void switchWindow() {
        try {
            String oldWindow = driver.getWindowHandle();
            Set<String> handles = driver.getWindowHandles();
            for (String handle : handles) {
                if (handle.equals(oldWindow) == false) {
                    driver.switchTo().window(handle);
                }
            }
            results.writeCell(writeLine, RES_COL, PASS);
            AutoLogger.log.info("直接切换窗口成功！！！");
        } catch (Exception e) {
            results.writeFailCell(writeLine, RES_COL, FAIL);
            AutoLogger.log.error("直接切换窗口失败！！！");
            saveErrShot("切换窗口失败");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
    }

    /**
     * 根据页面标题切换窗口
     *
     * @param title 需要切换的窗口标题
     */
    public void switchWindowByTitle(String title) {
        try {
            Set<String> handles = driver.getWindowHandles();
            for (String hanlde : handles) {
                driver.switchTo().window(hanlde);
                if (driver.getTitle().contains(title)) {
                    break;
                }
            }
            results.writeCell(writeLine, RES_COL, PASS);
            AutoLogger.log.info("切换到标题包含\"" + title + "\"的窗口成功！！！");
        } catch (Exception e) {
            results.writeFailCell(writeLine, RES_COL, FAIL);
            AutoLogger.log.error("切换到标题包含\"" + title + "\"的窗口失败！！！");
            saveErrShot("切换窗口失败");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
    }

    /**
     * 通过窗口排序值切换窗口
     *
     * @param index 窗口排序值，从1开始
     */
    public void switchWindowByIndex(String index) {
        try {
            int num = Integer.parseInt(index) - 1;
            results.writeCell(writeLine, RES_COL, PASS);
            List<String> handles = new ArrayList(driver.getWindowHandles());
            driver.switchTo().window(handles.get(num));
            AutoLogger.log.info("切换到第" + index + "个窗口成功！！！");
        } catch (NumberFormatException e) {
            results.writeFailCell(writeLine, RES_COL, FAIL);
            AutoLogger.log.error("切换到第" + index + "个窗口失败！！！");
            saveErrShot("切换窗口失败");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
    }

    /**
     * 通过irame的Id或name属性值来切换iframe
     *
     * @param nameorid Id或name属性值
     */
    public void switchIframe(String nameorid) {
        try {
            driver.switchTo().frame(nameorid);
            results.writeCell(writeLine, RES_COL, PASS);
            AutoLogger.log.info("切换到id或name为\"" + nameorid + "\"的iframe成功！！！");
        } catch (Exception e) {
            results.writeFailCell(writeLine, RES_COL, FAIL);
            AutoLogger.log.error("切换到id或name为\"" + nameorid + "\"的iframe失败！！！");
            saveErrShot("进入iframe失败");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
    }

    /**
     * 通过xpath来切换iframe
     *
     * @param xpath
     */
    public void switchIframeByXpath(String xpath) {
        try {
            driver.switchTo().frame(driver.findElement(By.xpath(xpath)));
            results.writeCell(writeLine, RES_COL, PASS);
            AutoLogger.log.info("切换到xpath为\"" + xpath + "\"的iframe成功！！！");
        } catch (Exception e) {
            results.writeFailCell(writeLine, RES_COL, FAIL);
            AutoLogger.log.error("切换到xpath为\"" + xpath + "\"的iframe失败！！！");
            saveErrShot("进入iframe失败");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
    }

    /**
     * 退出当前Iframe
     */
    public void switchUpIfarme() {
        try {
            driver.switchTo().parentFrame();
            results.writeCell(writeLine, RES_COL, PASS);
            AutoLogger.log.info("退出iframe成功！！！");
        } catch (Exception e) {
            results.writeFailCell(writeLine, RES_COL, FAIL);
            AutoLogger.log.error("退出iframe失败！！！");
            saveErrShot("退出iframe失败");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
    }

    /**
     * 回到主文档
     */
    public void switchOutIframe() {
        try {
            driver.switchTo().defaultContent();
            results.writeCell(writeLine, RES_COL, PASS);
            AutoLogger.log.info("回到主文档成功！！！");
        } catch (Exception e) {
            results.writeFailCell(writeLine, RES_COL, FAIL);
            AutoLogger.log.error("回到主文档失败！！！");
            saveErrShot("回到主文档失败");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
    }

    /**
     * 滑动页面到指定位置
     *
     * @param width  参数可为负数
     * @param height 参数可为负数
     */
    public void scrollToPoint(String width, String height) {
        int x = Integer.parseInt(width);
        int y = Integer.parseInt(height);
        try {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(" + x + "," + y + ")");
            results.writeCell(writeLine, RES_COL, PASS);
            AutoLogger.log.info("滑动到(" + x + "," + y + ")成功！！！");
        } catch (NumberFormatException e) {
            results.writeFailCell(writeLine, RES_COL, FAIL);
            AutoLogger.log.error("滑动到(" + x + "," + y + ")失败！！！");
            saveErrShot("滑动到指定位置失败");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
    }

    /**
     * 滑动到指定xpath元素
     *
     * @param xpath
     */
    public void scrollToXpath(String xpath) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath(xpath)));
            results.writeCell(writeLine, RES_COL, PASS);
            AutoLogger.log.info("滑动到xpath为\"" + xpath + "\"的元素成功！！！");
        } catch (Exception e) {
            results.writeFailCell(writeLine, RES_COL, FAIL);
            AutoLogger.log.error("滑动到xpath为\"" + xpath + "\"的元素失败！！！");
            saveErrShot("滑动到指定位置失败");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
    }

    /**
     * 直接执行js脚本
     *
     * @param jsScript 因为document语法定位元素不好用，当然可以用document.quereSelector通过css选择器定位，如document.querySelector("//input[@class='ecsc-search-input']").value='2222'
     */
    public void runJs(String jsScript) {
        //用强转的方式，将driver转换为js执行器类
        try {
            JavascriptExecutor jsRunner = (JavascriptExecutor) driver;
            jsRunner.executeScript(jsScript);
            results.writeCell(writeLine, RES_COL, PASS);
            AutoLogger.log.info("执行js脚本\"" + jsScript + "\"成功！！！");
        } catch (Exception e) {
            results.writeFailCell(writeLine, RES_COL, FAIL);
            AutoLogger.log.error("执行js脚本\"" + jsScript + "\"失败！！！");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
    }

    /**
     * 通过select的value值选择选项
     *
     * @param xpath
     * @param value
     */
    public void selectByValue(String xpath, String value) {
        try {
            Select sel = new Select(driver.findElement(By.xpath(xpath)));
            sel.selectByValue(value);
            Thread.sleep(500);
            results.writeCell(writeLine, RES_COL, PASS);
            AutoLogger.log.info("通过定位xpath\"" + xpath + "\"并选择select的值\"" + value + "\"成功！！！");
        } catch (Exception e) {
            results.writeFailCell(writeLine, RES_COL, FAIL);
            AutoLogger.log.error("通过定位xpath\"" + xpath + "\"并选择select的值\"" + value + "\"失败！！！");
            saveErrShot("通过select的value值选择失败");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
    }

    /**
     * 通过select的文本内容选择选项
     *
     * @param xpath
     * @param content
     */
    public void selectByContent(String xpath, String content) {
        try {
            Select sel = new Select(driver.findElement(By.xpath(xpath)));
            sel.selectByVisibleText(content);
            Thread.sleep(500);
            results.writeCell(writeLine, RES_COL, PASS);
            AutoLogger.log.info("通过定位xpath\"" + xpath + "\"并选择select的文本内容\"" + content + "\"成功！！！");
        } catch (Exception e) {
            results.writeFailCell(writeLine, RES_COL, FAIL);
            AutoLogger.log.error("通过定位xpath\"" + xpath + "\"并选择select的文本内容\"" + content + "\"失败！！！");
            saveErrShot("通过select的文本内容选择失败");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
    }

    /**
     * 通过select的顺序选择选项
     *
     * @param xpath
     * @param index 从1开始
     */
    public void selectByIndex(String xpath, String index) {
        try {
            int num = Integer.parseInt(index) - 1;
            Select sel = new Select(driver.findElement(By.xpath(xpath)));
            sel.selectByIndex(num);
            Thread.sleep(500);
            results.writeCell(writeLine, RES_COL, PASS);
            AutoLogger.log.info("通过定位xpath\"" + xpath + "\"并选择select的第" + index + "个选项成功！！！");
        } catch (Exception e) {
            results.writeFailCell(writeLine, RES_COL, FAIL);
            AutoLogger.log.error("通过定位xpath\"" + xpath + "\"并选择select的第" + index + "个选项失败！！！");
            saveErrShot("通过select的顺序选择失败");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
    }

    /**
     * 上传文件
     *
     * @param filepath 需要为文件绝对路径
     */
    public void upload(String filepath) {
        try {
//            复制照片地址
            StringSelection stringSelection = new StringSelection(filepath);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
//新建一个Robot类的对象
            Robot robot = new Robot();
            Thread.sleep(1000);
            //按下Ctrl+V
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            //释放Ctrl+V
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_V);
            Thread.sleep(2000);
            //点击回车
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            results.writeCell(writeLine, RES_COL, PASS);
            AutoLogger.log.info("上传文件\"" + filepath + "\"成功！！！");
        } catch (Exception e) {
            results.writeFailCell(writeLine, RES_COL, FAIL);
            AutoLogger.log.error("上传文件\"" + filepath + "\"失败！！！");
            saveErrShot("上传文件失败");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
    }

    /**
     * 断言标题是否包含预期值
     *
     * @param expect
     * @return
     */
    public boolean assertByTitle(String expect) {
        String title = driver.getTitle();
        boolean result = false;
        try {
            if (title.contains(expect)) {
                result = true;
                results.writeCell(writeLine, RES_COL, PASS);
                AutoLogger.log.info("标题中包含预期结果：" + expect);
            } else {
                results.writeCell(writeLine, RES_COL, PASS);
                AutoLogger.log.info("标题中不包含预期结果：" + expect);
                result = false;
            }
        } catch (Exception e) {
            results.writeFailCell(writeLine, RES_COL, FAIL);
            AutoLogger.log.error("断言标题中是否包含预期结果：" + expect + "失败！！！");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
        return result;
    }

    /**
     * 断言xpath元素的值是否包含预期值
     *
     * @param xpath
     * @param expect
     * @return
     */
    public boolean assertByxpath(String xpath, String expect) {
        boolean result = false;
        String content = driver.findElement(By.xpath(xpath)).getText();
        try {
            if (content.contains(expect)) {
                result = true;
                results.writeCell(writeLine, RES_COL, PASS);
                AutoLogger.log.info("xpath\"" + xpath + "\"元素的值包含预期值：" + expect);
            } else {
                results.writeCell(writeLine, RES_COL, PASS);
                AutoLogger.log.info("xpath\"" + xpath + "\"元素的值不包含预期值：" + expect);
                result = false;
            }
        } catch (Exception e) {
            results.writeFailCell(writeLine, RES_COL, FAIL);
            AutoLogger.log.error("断言xpath\"" + xpath + "\"的值中是否包含预期结果：" + expect + "失败！！！");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
        return result;
    }

    /**
     * 断言网页中是否包含预期值
     *
     * @param expect
     * @return
     */
    public boolean assertByPage(String expect) {
        boolean result = false;
        String pageContent = driver.getPageSource();
        try {
            if (pageContent.contains(expect)) {
                results.writeCell(writeLine, RES_COL, PASS);
                AutoLogger.log.info("网页源码中包含" + expect);
                result = true;
            } else {
                results.writeCell(writeLine, RES_COL, PASS);
                AutoLogger.log.info("网页源码中不包含" + expect);
                result = false;
            }
        } catch (Exception e) {
            results.writeFailCell(writeLine, RES_COL, FAIL);
            AutoLogger.log.error("断言网页源码中是否包含" + expect + "失败！！！");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
        return result;
    }

    public void saveScrshot(String filename) {
        try {
            File srcshot = new File(filename);
            TakesScreenshot ts = (TakesScreenshot) driver;
            File tmp = ts.getScreenshotAs(OutputType.FILE);
            Files.copy(tmp, srcshot);
        } catch (Exception e) {
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
    }

    public void saveErrShot(String info) {
        try {
            Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String nowtime = sdf.format(now);
            String fileName = "logs/screenshot/" + nowtime + info + ".png";
            saveScrshot(fileName);
        } catch (Exception e) {
            AutoLogger.log.error("截图失败！！！");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
    }

    /**
     * 关闭当前页面
     */
    public void closeWebPage() {
        try {
            driver.close();
            results.writeCell(writeLine, RES_COL, PASS);
            AutoLogger.log.info("关闭当前页面成功！！！");
        } catch (Exception e) {
            results.writeFailCell(writeLine, RES_COL, FAIL);
            AutoLogger.log.error("关闭当前页面失败！！！");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
    }

    /**
     * 关闭浏览器
     */
    public void closeBrowser() {
        try {
            driver.quit();
            results.writeCell(writeLine, RES_COL, PASS);
            AutoLogger.log.info("关闭浏览器成功！！！");
        } catch (Exception e) {
            results.writeFailCell(writeLine, RES_COL, FAIL);
            AutoLogger.log.error("关闭浏览器失败！！！");
            AutoLogger.log.error(e, e.fillInStackTrace());
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
