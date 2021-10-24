package com.lizheng.createDrivers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lizheng.common.AutoLogger;
import org.apache.http.*;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 该类实现所有请求方法
 */
public class InterDriverOfHttp {
    //用于传递cookie的cookie池。
    private CookieStore cookieBag;
    //加一个用于指示是否使用cookie的标志位
    private boolean useCookie=true;
    //加一个 用于存储头域的Map
    public Map<String,String> headerMap;
    //加一个用于指示是否使用存储的头域Map的标志位
    private boolean useHeader=true;
    // 匹配unicode编码格式的正则表达式。
    private static final Pattern UNIPATTERN = Pattern.compile("\\\\u([0-9a-zA-Z]{4})");

    //以下参数用于下载文件方法
    public static int cache = 10 * 1024;
    //用于判断是否windows和linux
    public static boolean isWindows = true;
    //用于指定层级符号
    public static String splash = "\\";
    //用于指定根目录
    public static String root = "D:";

    //构造方法中，先完成cookieStore的实例化。
    public InterDriverOfHttp(){
        cookieBag=new BasicCookieStore();
        headerMap=new HashMap<>();
    }

    static {
        if (System.getProperty("os.name") != null && System.getProperty("os.name").toLowerCase().contains("windows")) {
            isWindows = true;
            splash = "\\";
            root="D:";
        } else {
            isWindows = false;
            splash = "/";
            root="/search";
        }
    }

    /**
     * 把字符串中的unicode编码转换为中文
     * @param u
     * @return
     */
    public static String DeCode(String u) {
        Matcher m = UNIPATTERN.matcher(u);
        StringBuffer sb = new StringBuffer(u.length());
        while (m.find()) {
            m.appendReplacement(sb,Character.toString((char) Integer.parseInt(m.group(1),16)));
        }
        m.appendTail(sb);
        return sb.toString();
    }

    /**
     * SSLcontext用于绕过ssl验证，使发包的方法能够对https的接口进行请求
     * @return
     */
    private static SSLContext createIgnoreVerifySSL(){
        // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                                           String paramString) {
            }
            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                                           String paramString) {
            }
            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        SSLContext sc = null;
        try {
            sc = SSLContext.getInstance("SSLv3");
            sc.init(null, new TrustManager[] { trustManager }, null);
        } catch (Exception e) {
            AutoLogger.log.error(e,e.fillInStackTrace());
        }
        return sc;
    }

    /**
     * 完成httpclient的创建
     * @return 返回创建好的httpclient对象用于发包
     */
    private CloseableHttpClient createClient(){
        // 采用绕过验证的方式处理https请求
        SSLContext sslcontext = createIgnoreVerifySSL();
        // 设置协议http和https对应的处理socket链接工厂的对象
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", new SSLConnectionSocketFactory(sslcontext)).build();
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        //如果要使用cookiestore，就在创建时带上setdefaultcookieStore方法。
        CloseableHttpClient client;
        // 创建自定义的httpclient对象
        if(useCookie) {
            //如果标志位为真，使用cookie，则带上cookieStore
            client = HttpClients.custom().setConnectionManager(connManager).setDefaultCookieStore(cookieBag).build();
        }else{
            //否则不带cookieStore
            client = HttpClients.custom().setConnectionManager(connManager).build();
        }

        // 当需要进行代理抓包时，启用下面的代码。
        // 设置代理地址，适用于需要用fiddler抓包时使用，不用时切记注释掉这句！
//		HttpHost proxy = new HttpHost("localhost", 8888, "http");
//		CloseableHttpClient client = HttpClients.custom().setProxy(proxy).setConnectionManager(connManager).build();
        return client;
    }

    /**
     * get方法
     * @param url
     * @param param
     * @return
     */
    public String doGet(String url,String param) throws Exception {
        //创建httpclient对象
        CloseableHttpClient client = createClient();
        //使用result接收返回结果
        String result = "";
        String urlWithParam = "";
        if(param == null||param.length()<1){
            urlWithParam = url;
        }else{
            urlWithParam = url + "?" + param;
        }
        HttpGet get = new HttpGet(urlWithParam);
        // 设置连接的超时时间
        // setsocketTImeout指定收发包过程中的超时上线是15秒，connectTime指定和服务器建立连接，还没有发包时的超时上限为10秒。
        RequestConfig config = RequestConfig.custom().setSocketTimeout(15000).setConnectionRequestTimeout(10000).build();
        get.setConfig(config);

        //使用头域
        if(useHeader){
            //遍历用于存储头域的map，把每个键值对，作为头域的键和值进行添加
            for (String headerKey : headerMap.keySet()) {
                get.setHeader(headerKey,headerMap.get(headerKey));
            }
        }
        CloseableHttpResponse response = client.execute(get);
        HttpEntity entity = response.getEntity();
        if(entity != null){
            result = EntityUtils.toString(entity,"utf-8");
        }
        result = DeCode(result);
        EntityUtils.consume(entity);
        response.close();
        client.close();
        return result;
    }

    /**
     *实现指定类型的post请求
     * @param url
     * @param param key=value&key=value/key=value&key=value/{key:value, key:value}
     * @param type text/url/json
     * @return
     */
    public String doPost(String url,String param,String type) throws Exception {
        //创建httpclient对象
        CloseableHttpClient client = createClient();
        //使用result接收返回结果
        String result = "";
        // 创建post方式请求对象
        HttpPost post = new HttpPost(url);
        // 设置连接的超时时间
        // setsocketTImeout指定收发包过程中的超时上线是15秒，connectTime指定和服务器建立连接，还没有发包时的超时上限为10秒。
        RequestConfig config = RequestConfig.custom().setSocketTimeout(15000).setConnectionRequestTimeout(10000).build();
        post.setConfig(config);
        if(param == null||param.length()<1){
        }else{
            // 创建urlencoded格式的请求实体，设置编码为utf8
            StringEntity postParams = new StringEntity(param);
            switch (type){
                case "text":
                    postParams.setContentType("text/xml;charset=utf-8");
                    break;
                case "url":
                    postParams.setContentType("application/x-www-form-urlencoded");
                    break;
                case "json":
                    postParams.setContentType("application/json");
                    break;
                default:
                    postParams.setContentType("application/x-www-form-urlencoded");
            }
            postParams.setContentEncoding("UTF-8");
            // 添加请求体到post请求中
            post.setEntity(postParams);
        }
        //使用头域
        if(useHeader){
            //遍历用于存储头域的map，把每个键值对，作为头域的键和值进行添加
            for (String headerKey : headerMap.keySet()) {
                post.setHeader(headerKey,headerMap.get(headerKey));
            }
        }
        // 执行请求操作，并获取返回包
        CloseableHttpResponse response = client.execute(post);
        // 获取结果实体
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            // 按指定编码转换返回实体为String类型
            result = EntityUtils.toString(entity, "utf-8");
        }
        // 对结果中可能出现的unicode编码进行转换，转成中文
        result = DeCode(result);
        // 释放返回实体
        EntityUtils.consume(entity);
        // 关闭返回包
        response.close();
        client.close();
//        System.out.println(result);
        return result;
    }

    /**
     * 没有请求体的post请求
     * @param url
     * @return
     */
    public String doPostWithoutParam(String url) throws Exception {
        //创建httpclient对象
        CloseableHttpClient client = createClient();
        //使用result接收返回结果
        String result = "";
        // 创建post方式请求对象
        HttpPost post = new HttpPost(url);
        // 设置连接的超时时间
        // setsocketTImeout指定收发包过程中的超时上线是15秒，connectTime指定和服务器建立连接，还没有发包时的超时上限为10秒。
        RequestConfig config = RequestConfig.custom().setSocketTimeout(15000).setConnectionRequestTimeout(10000).build();
        post.setConfig(config);
        //使用头域
        if(useHeader){
            //遍历用于存储头域的map，把每个键值对，作为头域的键和值进行添加
            for (String headerKey : headerMap.keySet()) {
                post.setHeader(headerKey,headerMap.get(headerKey));
            }
        }
        // 执行请求操作，并获取返回包
        CloseableHttpResponse response = client.execute(post);
        // 获取结果实体
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            // 按指定编码转换返回实体为String类型
            result = EntityUtils.toString(entity, "UTF-8");
        }
        // 对结果中可能出现的unicode编码进行转换，转成中文
        result = DeCode(result);
        // 释放返回实体
        EntityUtils.consume(entity);
        // 关闭返回包
        response.close();
        client.close();
        return result;
    }

    /**
     * application/x-www-form-urlencoded格式的post请求
     * @param url
     * @param param 格式为key=value&key=value
     * @return
     */
    public String doPostByUrl(String url,String param) throws Exception {
//        System.out.println(param);
        //创建httpclient对象
        CloseableHttpClient client = createClient();
        //使用result接收返回结果
        String result = "";
        // 创建post方式请求对象
        HttpPost post = new HttpPost(url);
        // 设置连接的超时时间
        // setsocketTImeout指定收发包过程中的超时上线是15秒，connectTime指定和服务器建立连接，还没有发包时的超时上限为10秒。
        RequestConfig config = RequestConfig.custom().setSocketTimeout(15000).setConnectionRequestTimeout(10000).build();
        post.setConfig(config);
        if(param == null||param.length()<1){
        }else{
            // 创建urlencoded格式的请求实体，设置编码为utf8
            StringEntity postParams = new StringEntity(param);
            postParams.setContentType("application/x-www-form-urlencoded");
            postParams.setContentEncoding("UTF-8");
            // 添加请求体到post请求中
            post.setEntity(postParams);
        }
        //使用头域
        if(useHeader){
            //遍历用于存储头域的map，把每个键值对，作为头域的键和值进行添加
            for (String headerKey : headerMap.keySet()) {
                post.setHeader(headerKey,headerMap.get(headerKey));
            }
        }
        // 执行请求操作，并获取返回包
        CloseableHttpResponse response = client.execute(post);
        // 获取结果实体
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            // 按指定编码转换返回实体为String类型
            result = EntityUtils.toString(entity, "UTF-8");
        }
        // 对结果中可能出现的unicode编码进行转换，转成中文
        result = DeCode(result);
        // 释放返回实体
        EntityUtils.consume(entity);
        // 关闭返回包
        response.close();
        client.close();
        return result;
    }

    /**
     * 实现application/json格式的post方法
     * @param url
     * @param param 参数格式为 {key:value, key:value}
     * @return
     */
    public String doPostByJson(String url,String param) throws Exception {
        // 创建httpclient对象
        CloseableHttpClient client = createClient();
        // result为最终返回结果
        String result = "";
        // 创建post方式请求对象
        HttpPost post = new HttpPost(url);
        // 设置连接的超时时间
        // setsocketTImeout指定收发包过程中的超时上线是15秒，connectTime指定和服务器建立连接，还没有发包时的超时上限为10秒。
        RequestConfig config = RequestConfig.custom().setSocketTimeout(15000).setConnectTimeout(10000).build();
        post.setConfig(config);
        if(param == null||param.length()<1){
        }else {
            // 创建urlencoded格式的请求实体，设置编码为utf8
            StringEntity postParams = new StringEntity(param);
            postParams.setContentType("application/json");
            postParams.setContentEncoding("UTF-8");
            // 添加请求体到post请求中
            post.setEntity(postParams);
        }
        //使用头域
        if(useHeader){
            //遍历用于存储头域的map，把每个键值对，作为头域的键和值进行添加
            for (String headerKey : headerMap.keySet()) {
                post.setHeader(headerKey,headerMap.get(headerKey));
            }
        }
        // 执行请求操作，并获取返回包
        CloseableHttpResponse response = client.execute(post);
        // 获取结果实体
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            // 按指定编码转换返回实体为String类型
            result = EntityUtils.toString(entity, "UTF-8");
        }
        // 对结果中可能出现的unicode编码进行转换，转成中文
        result = DeCode(result);
        // 释放返回实体
        EntityUtils.consume(entity);
        // 关闭返回包
        response.close();
        client.close();
        return result;
    }

    /**
     * 实现multipart/form-data格式的post上传文件的方法
     * @param url 上传路径
     * @param paramOfBinaryBody 文件部分参数，格式为{"upload_file":"C:\\Users\\86158\\Pictures\\Camera Roll\\安全座椅750_1.jpg"}
     * @param paramOfTextBody 文本部分参数，格式为{"submit":"提交"}
     * @return
     * 文件和文本参数区分
    "------WebKitFormBoundaryK6zUI3r7TINciZA9
    Content-Disposition: form-data; name="upload_file"; filename="图片.jpg"
    Content-Type: image/jpeg

    文件乱码部分

    ------WebKitFormBoundaryK6zUI3r7TINciZA9
    Content-Disposition: form-data; name="submit"

    提交
    ------WebKitFormBoundaryK6zUI3r7TINciZA9--"
     上面中“显示文件乱码部分”的参数就是文件部分参数，其他的为文本部分参数
     */
    public String doPostFile(String url,String paramOfBinaryBody,String paramOfTextBody) throws Exception {
        //创建httpclient对象
        CloseableHttpClient client = createClient();
        //使用result接收返回结果
        String result = "";
        HttpPost post = new HttpPost(url);
        // 设置连接的超时时间
        // setsocketTImeout指定收发包过程中的超时上线是15秒，connectTime指定和服务器建立连接，还没有发包时的超时上限为10秒。
        RequestConfig config = RequestConfig.custom().setSocketTimeout(15000).setConnectionRequestTimeout(10000).build();
        post.setConfig(config);
        MultipartEntityBuilder meb = MultipartEntityBuilder.create().setMode(HttpMultipartMode.RFC6532);
        //添加文件部分的参数。
        JSONObject paramOfBinaryBodyJson = JSON.parseObject(paramOfBinaryBody);
        for (String key : paramOfBinaryBodyJson.keySet()) {
            File tmpFile = new File(paramOfBinaryBodyJson.getString(key));
            //获取文件名
            String fileName = tmpFile.getName();
            //获取文件扩展名
            String exName = fileName.substring(fileName.lastIndexOf(".") + 1);
            //根据文件扩展名判断ContentType传入方式
            if(exName.equals("jpg")){
                meb.addBinaryBody(key,tmpFile,ContentType.IMAGE_JPEG,fileName);
            }
            else if(exName.equals("png")){
                meb.addBinaryBody(key,tmpFile,ContentType.IMAGE_PNG,fileName);
            }
            else if(exName.equals("gif")){
                meb.addBinaryBody(key,tmpFile,ContentType.IMAGE_GIF,fileName);
            }else {
                //ContentType默认为application/octet-stream
                meb.addBinaryBody(key,tmpFile);
            }
        }
        if(paramOfTextBody==null||paramOfTextBody.length()<1){
        }else{
            //添加文本部分的参数。
            JSONObject paramOfTextBodyJson = JSON.parseObject(paramOfTextBody);
            for (String key : paramOfTextBodyJson.keySet()) {
                meb.addTextBody(key,paramOfTextBodyJson.getString(key));
            }
        }
        HttpEntity fileEntity = meb.build();
        post.setEntity(fileEntity);
        //使用头域
        if(useHeader){
            //遍历用于存储头域的map，把每个键值对，作为头域的键和值进行添加
            for (String headerKey : headerMap.keySet()) {
                post.setHeader(headerKey,headerMap.get(headerKey));
            }
        }
        CloseableHttpResponse response = client.execute(post);
        HttpEntity entity = response.getEntity();
        if(entity != null){
            result = EntityUtils.toString(entity,"utf-8");
        }
        result = DeCode(result);
        EntityUtils.consume(entity);
        response.close();
        client.close();
        return result;
    }

    /**
     * 根据url下载文件，并使用指定的路径和名称作为存储文件的路径和名称
     * @param url
     * @param filepath 绝对路径，如C:\Users\86158\Desktop\test1.png
     * @return
     */
    public String downloadWithName(String url, String filepath) throws Exception {
        //使用result接收返回结果
        String result = "";
        // 创建httpclient对象
        CloseableHttpClient client = createClient();
        HttpGet httpget = new HttpGet(url);
        //使用头域
        if(useHeader){
            //遍历用于存储头域的map，把每个键值对，作为头域的键和值进行添加
            for (String headerKey : headerMap.keySet()) {
                httpget.setHeader(headerKey,headerMap.get(headerKey));
            }
        }
        CloseableHttpResponse response = client.execute(httpget);
        HttpEntity entity = response.getEntity();
        InputStream is = entity.getContent();
        //如果文件路径为空，则使用默认的路径（D://）和文件名进行存储
        if (filepath == null)
            filepath = getFilePath(response);
        File file = new File(filepath);
        file.getParentFile().mkdirs();
        FileOutputStream fileout = new FileOutputStream(file);
        /**
         * 根据实际运行效果 设置缓冲区大小
         */
        byte[] buffer=new byte[cache];
        int ch = 0;
        while ((ch = is.read(buffer)) != -1) {
            fileout.write(buffer,0,ch);
        }
        is.close();
        fileout.flush();
        fileout.close();
        if(entity != null){
            result = EntityUtils.toString(entity,"utf-8");
        }
        result = DeCode(result);
        EntityUtils.consume(entity);
        response.close();
        client.close();
        return result;
    }

    /**
     * 根据url下载文件，文件名从response header头中获取
     * @param url
     * @return
     */
    public String download(String url)  throws Exception {
        return downloadWithName(url, null);
    }

    /**
     * 获取response要下载的文件的默认路径
     * @param response
     * @return
     */
    public static String getFilePath(CloseableHttpResponse response) {
        String filepath = root + splash;
        String filename = getFileName(response);

        if (filename != null) {
            filepath += filename;
        } else {
            filepath += getRandomFileName();
        }
        return filepath;
    }

    /**
     * 获取response header中Content-Disposition中的filename值
     * @param response
     * @return
     */
    public static String getFileName(CloseableHttpResponse response) {
        Header contentHeader = response.getFirstHeader("Content-Disposition");
        String filename = null;
        if (contentHeader != null) {
            HeaderElement[] values = contentHeader.getElements();
            if (values.length == 1) {
                NameValuePair param = values[0].getParameterByName("filename");
                if (param != null) {
                    try {
                        //filename = new String(param.getValue().toString().getBytes(), "utf-8");
                        //filename=URLDecoder.decode(param.getValue(),"utf-8");
                        filename = param.getValue();
                    } catch (Exception e) {
                        AutoLogger.log.error(e, e.fillInStackTrace());
                    }
                }
            }
        }
        return filename;
    }

    /**
     * 获取随机文件名
     * @return
     */
    public static String getRandomFileName() {
        return String.valueOf(System.currentTimeMillis());
    }

    /**
     * 使用cookie
     */
    public void useCookie(){
        useCookie = true;
    }

    /**
     * 不使用cookie
     */
    public void notUseCookie(){
        useCookie = false;
    }

    /**
     *清除cookie中的数据
     */
    public void clearCookie(){
        cookieBag = new BasicCookieStore();
    }

    /**
     * 使用头信息存储header数据
     */
    public void useHeader(){
        useHeader = true;
    }

    /**
     * 将header数据添加到头域中
     * @param key
     * @param value
     */
    public void addHeader(String key,String value){
        headerMap.put(key,value);
    }

    /**
     * 不使用头域进行存储数据
     */
    public void  notUseHeader(){
        useHeader = false;
    }

    /**
     * 清除头域中存储的header数据
     */
    public void clearHeader(){
        headerMap = new HashMap<>();
    }
}
