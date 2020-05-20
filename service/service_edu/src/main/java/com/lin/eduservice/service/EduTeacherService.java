package com.lin.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lin.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-05-02
 */
public interface EduTeacherService extends IService<EduTeacher> {

    Map<String,Object> pageListWeb(Page<EduTeacher> pageParam);
}
