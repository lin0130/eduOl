package com.lin.orderserver.service;

import com.lin.orderserver.entity.TPayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-05-16
 */
public interface TPayLogService extends IService<TPayLog> {

    Map createNative(String orderNo);

    Map<String,String> queryPayStatus(String orderNo);

    void updateOrderStatus(Map<String, String> map);
}
