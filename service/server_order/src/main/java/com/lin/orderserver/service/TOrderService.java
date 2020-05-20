package com.lin.orderserver.service;

import com.lin.orderserver.entity.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-05-16
 */
public interface TOrderService extends IService<TOrder> {

    String saveOrder(String courseId, String userId);
}
