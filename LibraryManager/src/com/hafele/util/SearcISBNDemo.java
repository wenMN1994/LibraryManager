package com.hafele.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import net.sf.json.JSONObject;

/**
* @author Dragon Wen E-mail:18475536452@163.com
* @version 创建时间：2017年10月13日 下午3:47:04
* 图书ISBN查询调用代码
* 该工具类中使用了两个API分别为 聚合数据API和极速数据API，这两个API实现同样的功能——通过图书编号获取图书信息，免费次数有限
*/
public class SearcISBNDemo {
    public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
 
    //配置您申请的KEY － 聚合数据API
    public static final String APPKEY_JUHE ="acfdff0a5cb840678cc0d2553402913f";//该API免费次数已用完请自行到https://www.juhe.cn/申请appkey
 
    //配置您申请的KEY － 极速数据API
    public static final String APPKEY_JISU ="86499d759f8a2f82";//该API免费次数已用完请自行到https://www.jisuapi.com申请appkey
    
    //图书ISBN检索 － 聚合数据API
    public static String getRequestJuhe(String ISBN){
        String result =null;
        String url ="http://feedback.api.juhe.cn/ISBN";//请求接口地址
        Map<String, Object> params = new HashMap<String, Object>();//请求参数
        params.put("key",APPKEY_JUHE);//应用APPKEY(应用详细页查询)
        params.put("sub",ISBN);//图书ISBN编码
 
        try {
            result = netJuhe(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if(object.getInt("error_code")==0){
                System.out.println(object.get("result"));
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
                JOptionPane.showMessageDialog(null, "无法查询到该图书信息。");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return result;
    }
    
  //图书ISBN检索 － 极速数据API
    public static String getRequestJisu(String ISBN){
        String result =null;
        String url ="http://api.jisuapi.com/isbn/query";//请求接口地址
        Map<String, Object> params = new HashMap<String, Object>();//请求参数
        params.put("isbn",ISBN);//图书ISBN编码
        params.put("appkey",APPKEY_JISU);//应用APPKEY(应用详细页查询)           
 
        try {
            result = netJisu(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if(object.getInt("status")==0){
                System.out.println(object.get("result"));
            }else{
                System.out.println(object.get("status")+":"+object.get("msg"));
                JOptionPane.showMessageDialog(null, "无法查询到该图书信息。");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return result;
    }
    
    /**
     *
     * @param strUrl 请求地址
     * @param params 请求参数
     * @param method 请求方法
     * @return  网络请求字符串
     * @throws Exception
     * 聚合数据API
     */
    public static String netJuhe(String strUrl, Map<String, Object> params,String method) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if(method==null || method.equals("GET")){
                strUrl = strUrl+"?"+urlencode(params);
            }
            URL url = new URL(strUrl);
            System.out.println(url);
            conn = (HttpURLConnection) url.openConnection();
            if(method==null || method.equals("GET")){
                conn.setRequestMethod("GET");
            }else{
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);//指定代理地址
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (params!= null && method.equals("POST")) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                        out.writeBytes(urlencode(params));
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }
    
    /**
    *
    * @param strUrl 请求地址
    * @param params 请求参数
    * @param method 请求方法
    * @return  网络请求字符串
    * @throws Exception
    * 聚合数据API
    */
   public static String netJisu(String strUrl, Map<String, Object> params,String method) throws Exception {
       HttpURLConnection conn = null;
       BufferedReader reader = null;
       String rs = null;
       try {
           StringBuffer sb = new StringBuffer();
           if(method==null || method.equals("GET")){
               strUrl = strUrl+"?"+urlencode(params);
           }
           URL url = new URL(strUrl);
           System.out.println(url);
           conn = (HttpURLConnection) url.openConnection();
           if(method==null || method.equals("GET")){
               conn.setRequestMethod("GET");
           }else{
               conn.setRequestMethod("POST");
               conn.setDoOutput(true);
           }
           conn.setUseCaches(false);
           conn.setConnectTimeout(DEF_CONN_TIMEOUT);
           conn.setReadTimeout(DEF_READ_TIMEOUT);
           conn.setInstanceFollowRedirects(false);
           conn.connect();
           if (params!= null && method.equals("POST")) {
               try {
                   DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                       out.writeBytes(urlencode(params));
               } catch (Exception e) {
                   // TODO: handle exception
               }
           }
           InputStream is = conn.getInputStream();
           reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
           String strRead = null;
           while ((strRead = reader.readLine()) != null) {
               sb.append(strRead);
           }
           rs = sb.toString();
       } catch (IOException e) {
           e.printStackTrace();
       } finally {
           if (reader != null) {
               reader.close();
           }
           if (conn != null) {
               conn.disconnect();
           }
       }
       return rs;
   }
 
    //将map型转为请求参数型
    @SuppressWarnings("rawtypes")
	public static String urlencode(Map<String,Object> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
