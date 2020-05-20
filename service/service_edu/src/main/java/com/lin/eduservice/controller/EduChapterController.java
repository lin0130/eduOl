package com.lin.eduservice.controller;


import com.lin.commonuntils.R;
import com.lin.eduservice.entity.EduChapter;
import com.lin.eduservice.entity.EduCourse;
import com.lin.eduservice.entity.EduCourseDescription;
import com.lin.eduservice.entity.vo.ChapterVo;
import com.lin.eduservice.entity.vo.CourseInfoVo;
import com.lin.eduservice.service.EduChapterService;
import com.lin.eduservice.service.EduCourseDescriptionService;
import com.lin.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-05-06
 */
@RestController
@CrossOrigin
@Api(description = "课程章节接口")
@RequestMapping("/eduservice/chapter")
public class EduChapterController {
    @Autowired
    private EduChapterService eduChapterService;

    @Autowired
    private EduCourseService eduCourseService;

    @GetMapping("list/{courseId}")
    public R getChapters(@PathVariable String courseId){
        List<ChapterVo> chapterVos = eduChapterService.chapterList(courseId);
        return R.ok().data("chapters",chapterVos);
    }

    @GetMapping("getbyid/{courseId}")
    public R getCourseById(@PathVariable String courseId){
        CourseInfoVo courseInfoVo = eduCourseService.getCourseInfoByid(courseId);

        return R.ok().data("courseInfo",courseInfoVo);
    }

    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo)
    {
        eduCourseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }

    @PostMapping("addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter){
        eduChapterService.save(eduChapter);
        return R.ok();
    }

    @GetMapping("getChapterById/{id}")
    public R getChapterById(@PathVariable String id){
        EduChapter chapter = eduChapterService.getById(id);

        return R.ok().data("chapter",chapter);
    }

    @PutMapping("updateById/{id}")
    public R updateChapterId(@PathVariable String id,
                             @RequestBody EduChapter eduChapter){
        eduChapter.setId(id);
        eduChapterService.updateById(eduChapter);
        return R.ok();
    }

    @DeleteMapping("deletChapter/{id}")
    public R deletChapter(@PathVariable String id){
       Boolean result = eduChapterService.deleteChapterById(id);
       if (result){
           return R.ok();
       }else {
           return R.error().message("删除失败");
       }
    }
}

