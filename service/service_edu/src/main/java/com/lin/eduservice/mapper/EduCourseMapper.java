package com.lin.eduservice.mapper;

import com.lin.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lin.eduservice.entity.vo.CoursePublishVo;
import com.lin.eduservice.entity.vo.CourseWebVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2020-05-06
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    CoursePublishVo selectCoursePublishVoByCourseId(String id);
    //获取客户端课程信息
    CourseWebVo selectInfoWebById(String courseId);
}
