package com.lin.eduservice.service;

import com.lin.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lin.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-05-05
 */
public interface EduSubjectService extends IService<EduSubject> {

    //添加课程方法
    void saveSubject(MultipartFile file,EduSubjectService eduSubjectService);

    List<OneSubject> getOneTwoSubject();
}
