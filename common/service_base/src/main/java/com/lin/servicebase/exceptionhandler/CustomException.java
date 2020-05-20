package com.lin.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor//有惨构造
@NoArgsConstructor//无参构造
public class CustomException extends RuntimeException {
    private Integer code;
    private String msg;
}
