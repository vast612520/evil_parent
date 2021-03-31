package com.bewind.evil.exception;

/**
 * Description: 自定义异常
 *
 * 区分系统与自定义的异常
 * 终止已经不符合业务逻辑的代码
 *
 */
public class EvilException extends RuntimeException{
    public EvilException(String message){
        super(message);
    }
}
