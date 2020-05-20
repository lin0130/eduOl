package com.lin.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lin.commonuntils.R;
import com.lin.commonuntils.untils.JwtUntils;
import com.lin.eduservice.client.OrderClient;
import com.lin.eduservice.entity.EduCourse;
import com.lin.eduservice.entity.EduTeacher;
import com.lin.eduservice.entity.vo.*;
import com.lin.eduservice.service.EduChapterService;
import com.lin.eduservice.service.EduCourseService;
import com.lin.eduservice.service.EduTeacherService;
import com.lin.eduservice.service.impl.EduCourseServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-05-06
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {
    @Autowired
    private EduCourseServiceImpl eduCourseService;

    @Autowired
    private EduTeacherService teacherService;

    @Autowired
    private EduChapterService eduChapterService;

    @Autowired
    private OrderClient orderClient;
    //添加课程基本信息
    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        String id = eduCourseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId",id);
    }

    @ApiOperation(value = "根据ID获取课程发布信息")
    @GetMapping("course-publish-info/{id}")
    public R getCoursePublishVoById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id){
        CoursePublishVo courseInfoForm = eduCourseService.getCoursePublishVoById(id);
        return R.ok().data("item", courseInfoForm);
    }

    @PutMapping("publish/{id}")
    public R publishCourse(@PathVariable String id){
        EduCourse eduCourse = new EduCourse();
        eduCourse.setStatus("Normal");
        eduCourse.setId(id);
        eduCourseService.updateById(eduCourse);
        return R.ok();
    }

    @DeleteMapping("remove/{id}")
    public R removeCourseById(@PathVariable String id){
        Boolean result = eduCourseService.removeCourseById(id);
        if (result) {
            return R.ok();
        }else {
            return R.error();
        }
    }

    @GetMapping("courselist/{current}/{limit}")
    public R courseList(@PathVariable Long current,
                        @PathVariable Long limit,
                        @RequestBody(required = false) CourseQuery courseQuery){
        Page<EduCourse> page = new Page<>(current,limit);
        eduCourseService.pageQuery(page,courseQuery);

        long total = page.getTotal();
        List<EduCourse> records = page.getRecords();
        return R.ok().data("total",total).data("rows",records);
    }

    @GetMapping("index")
    public R index() {
        //查询前8条热门课程
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 8");
        List<EduCourse> eduList = eduCourseService.list(wrapper);

        //查询前4条名师
        QueryWrapper<EduTeacher> wrapperTeacher = new QueryWrapper<>();
        wrapperTeacher.orderByDesc("id");
        wrapperTeacher.last("limit 4");
        List<EduTeacher> teacherList = teacherService.list(wrapperTeacher);

        return R.ok().data("courseList",eduList).data("teacherList",teacherList);
    }

    @ApiOperation(value = "分页课程列表")
    @PostMapping(value = "{page}/{limit}")
    public R pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
            @RequestBody(required = false) CourseQueryVo courseQuery){
        Page<EduCourse> pageParam = new Page<EduCourse>(page, limit);
        Map<String, Object> map = eduCourseService.pageListWeb(pageParam, courseQuery);
        return  R.ok().data(map);
    }


    @ApiOperation(value = "根据ID查询课程")
    @GetMapping(value = "{courseId}")
    public R getById(
            @ApiParam(name = "courseId", value = "课程ID", required = true)
            @PathVariable String courseId,HttpServletRequest request){

        //查询课程信息和讲师信息
        CourseWebVo courseWebVo = eduCourseService.selectInfoWebById(courseId);

        //查询当前课程的章节信息
        List<ChapterVo> chapterVoList = eduChapterService.chapterList(courseId);

        boolean buyCourse = orderClient.isBuyCourse(JwtUntils.getMemberIdByJwtToken(request), courseId);

        return R.ok().data("course", courseWebVo).data("chapterVoList", chapterVoList).data("isbuy",buyCourse);
    }

    //根据课程id查询课程信息
    @GetMapping("getCourseInfoOrder/{courseId}")
    public CourseInfoVo getCourseInfoDto(@PathVariable String courseId) {
        CourseInfoVo courseInfoForm = eduCourseService.getCourseInfoByid(courseId);
        CourseInfoVo courseInfo = new CourseInfoVo();
        BeanUtils.copyProperties(courseInfoForm,courseInfo);
        return courseInfo;
    }
}

