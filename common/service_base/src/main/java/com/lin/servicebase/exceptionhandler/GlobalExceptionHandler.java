package com.lin.servicebase.exceptionhandler;

import com.lin.commonuntils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler  {
    //指定出现什么异常执行方法
    @ExceptionHandler(Exception.class)
    @ResponseBody//返回数据
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("执行了全局异常处理。。。。");
    }

    @ExceptionHandler(CustomException.class)
    @ResponseBody//返回数据
    public R error(CustomException e){
        log.error(e.getMessage());
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }

}
