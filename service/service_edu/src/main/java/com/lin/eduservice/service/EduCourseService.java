package com.lin.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lin.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lin.eduservice.entity.vo.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-05-06
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfoByid(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo getCoursePublishVoById(String id);

    void pageQuery(Page<EduCourse> page, CourseQuery courseQuery);

    Boolean removeCourseById(String id);

    List<EduCourse> selectByTeacherId(String id);

    Map<String,Object> pageListWeb(Page<EduCourse> pageParam, CourseQueryVo courseQuery);

    CourseWebVo selectInfoWebById(String courseId);

    void updatePageViewCount(String id);
}
