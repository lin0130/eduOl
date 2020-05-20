package com.lin.eduservice.controller;


import com.lin.commonuntils.R;
import com.lin.eduservice.entity.subject.OneSubject;
import com.lin.eduservice.service.EduSubjectService;
import com.lin.eduservice.service.impl.EduSubjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-05-05
 */
@RestController
@CrossOrigin
@RequestMapping("/eduservice/edu_subject")
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    @Autowired
    private EduSubjectServiceImpl eduSubjectServiceImpl;
    @PostMapping("addsubject")
    public R addSubject(MultipartFile file){
        //上传过来Excel文件

        eduSubjectService.saveSubject(file,eduSubjectService);
        return R.ok();
    }

    @GetMapping("getallsubject")
    public R getAllSubject(){
        List<OneSubject> list = eduSubjectServiceImpl.getOneTwoSubject();
        return R.ok().data("list",list);
    }
}

