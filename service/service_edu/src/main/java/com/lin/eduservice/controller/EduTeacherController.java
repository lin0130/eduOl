package com.lin.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lin.commonuntils.R;
import com.lin.eduservice.entity.EduCourse;
import com.lin.eduservice.entity.EduTeacher;
import com.lin.eduservice.entity.vo.TeacherQuery;
import com.lin.eduservice.service.EduCourseService;
import com.lin.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-05-02
 */
@Api(description = "讲师管理")
@RestController
@CrossOrigin
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;

    @Autowired
    private EduCourseService eduCourseService;

    //http://localhost:8001/eduservice/edu-teacher/findAll
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public R findAllTeacher() {
        List<EduTeacher> eduTeachers = eduTeacherService.list(null);
        return R.ok().data("AllTeachers", eduTeachers);
    }

    //逻辑删除
    @ApiOperation(value = "根据ID逻辑删除讲师")
    @DeleteMapping("{id}")
    public R removeTeache(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id) {
        boolean flag = eduTeacherService.removeById(id);
        if (flag == true) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //分页查询讲师
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeachers(@PathVariable Long current, @PathVariable Long limit) {
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        //调用方法是底层封装 把分页所有数据都封装到pageTeacher对象中去
        eduTeacherService.page(pageTeacher, null);
        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();
        return R.ok().data("total", total).data("rows", records);
    }

    //条件分页查询方法
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable Long current, @PathVariable Long limit,
                                  @RequestBody(required = false) TeacherQuery teacherQuery) {//Required request body is missing: required = false表示可以传入空值
        Page<EduTeacher> pageTeachers = new Page<>(current, limit);
        QueryWrapper<EduTeacher> eduTeacherQueryWrapper = new QueryWrapper<>();
        if (teacherQuery != null) {
            String name = teacherQuery.getName();
            Integer level = teacherQuery.getLevel();
            String begin = teacherQuery.getBegin();
            String end = teacherQuery.getEnd();
            //多条件动态组合查询
            if (!StringUtils.isEmpty(name)) {
                eduTeacherQueryWrapper.like("name", name);
            }
            if (null != level) {
                eduTeacherQueryWrapper.eq("level", level);
            }
            if (!StringUtils.isEmpty(begin)) {
                eduTeacherQueryWrapper.ge("gmt_create", begin);
            }
            if (!StringUtils.isEmpty(end)) {
                eduTeacherQueryWrapper.le("gmt_create", end);
            }
        }
        eduTeacherQueryWrapper.orderByDesc("gmt_create");
        eduTeacherService.page(pageTeachers, eduTeacherQueryWrapper);
        long total = pageTeachers.getTotal();
        List<EduTeacher> records = pageTeachers.getRecords();
        return R.ok().data("total", total).data("rows", records);

    }

    //添加讲师接口
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean save = eduTeacherService.save(eduTeacher);
        if (save == true) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //根据id查讲师
    @GetMapping("getTeacher/{id}")
    public R getTeacherById(@PathVariable String id) {
        EduTeacher teacher = eduTeacherService.getById(id);
        return R.ok().data("teacher", teacher);
    }

    //修改讲师
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean update = eduTeacherService.updateById(eduTeacher);
        if (update == true) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ApiOperation(value = "分页讲师列表")
    @GetMapping(value = "{page}/{limit}")
    public R pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit){

        Page<EduTeacher> pageParam = new Page<EduTeacher>(page, limit);

        Map<String, Object> map = eduTeacherService.pageListWeb(pageParam);

        return  R.ok().data(map);
    }

    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping(value = "tc/{id}")
    public R getTCById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){

        //查询讲师信息
        EduTeacher teacher = eduTeacherService.getById(id);

        //根据讲师id查询这个讲师的课程列表
        List<EduCourse> courseList = eduCourseService.selectByTeacherId(id);

        return R.ok().data("teacher", teacher).data("courseList", courseList);
    }
}

