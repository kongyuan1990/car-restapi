package com.smartparking.car.restapi.bean;
import java.util.Map;
/**
 * 
 * @author mxs
 * 2017年7月17日
 * @version 1.0.0
 */
public class CarReturn<T> {
    
    private int code;//状态码  1：表示成功   0:表示失败
    private String msg;//要给的提示信息   
    private T content;//响应的内容；
    private Map<String, Object> ext;//额外的数据
    
    public int getCode() {
        return code;
    }
    
    public void setCode(int code) {
        this.code = code;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public T getContent() {
        return content;
    }
    
    public void setContent(T content) {
        this.content = content;
    }
    
    public Map<String, Object> getExt() {
        return ext;
    }
    
    public void setExt(Map<String, Object> ext) {
        this.ext = ext;
    }
    
    /**
     * {
     *    code:1,
     *    msg:"用户注册成功！",
     *    ext:{
     *          a:"请先激活",
     *          b:"http://www.baidu.com"
     *     },
     *    content:[
     *      {username:"zhangsan",pwd:'123'},
     *      {username:"zhangsan",pwd:'123'}
     *      ]
     * }
     * 
     * 
     */
    //两个快速的成功失败方法
    public static<T> CarReturn<T> success(String msg,T content,Map<String, Object> ext){
        CarReturn<T> t = new CarReturn<T>();
        t.setCode(1);
        t.setMsg(msg);
        t.setContent(content);
        t.setExt(ext);
        return t;
    }
    
    public static<T> CarReturn<T> fail(String msg,T content,Map<String, Object> ext){
        CarReturn<T> t = new CarReturn<T>();
        t.setCode(0);
        t.setMsg(msg);
        t.setContent(content);
        t.setExt(ext);
        return t;
    }
    
    

}
