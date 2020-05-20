package com.lin.eduservice.entity.vo;

import io.swagger.annotations.Api;
import lombok.Data;

@Api
@Data
public class CoursePublishVo {
    private static final long serialVersionUID = 1L;

    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}
