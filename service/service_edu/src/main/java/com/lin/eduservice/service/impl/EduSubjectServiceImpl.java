package com.lin.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lin.eduservice.entity.EduSubject;
import com.lin.eduservice.entity.excel.SubjectData;
import com.lin.eduservice.entity.subject.OneSubject;
import com.lin.eduservice.entity.subject.TwoSubject;
import com.lin.eduservice.listener.SubjectExcelListener;
import com.lin.eduservice.mapper.EduSubjectMapper;
import com.lin.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-05-05
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    //添加课程分类
    @Override
    public void saveSubject(MultipartFile file,EduSubjectService eduSubjectService) {
        try {
            InputStream in = file.getInputStream();
            EasyExcel.read(in,SubjectData.class,new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OneSubject> getOneTwoSubject() {
        //查询一级分类
        QueryWrapper<EduSubject> oneSubjectWrapper = new QueryWrapper<>();
        //parent_id为0
        oneSubjectWrapper.eq("parent_id",0);
        List<EduSubject> oneSubjects = baseMapper.selectList(oneSubjectWrapper);
        //查询二级分类
        QueryWrapper<EduSubject> twoSubjectWrapper = new QueryWrapper<>();
        //id为其一级分类的id 即parent_id不为0
        twoSubjectWrapper.ne("parent_id",0);
        List<EduSubject> twoSubjects = baseMapper.selectList(twoSubjectWrapper);

        List<OneSubject> finalSubjectList = new ArrayList<>();

        for (EduSubject oneSubject : oneSubjects) {
            OneSubject subject = new OneSubject();
            BeanUtils.copyProperties(oneSubject,subject);
            finalSubjectList.add(subject);
            List<TwoSubject> tfinalSubjectList = new ArrayList<>();//这个位置害死我了 放在一层循环外就错了
            for (EduSubject twoSubject : twoSubjects) {
                if (twoSubject.getParentId().equals(oneSubject.getId())){
                    TwoSubject tSubject = new TwoSubject();
                    BeanUtils.copyProperties(twoSubject,tSubject);
                    tfinalSubjectList.add(tSubject);
                }
            }
            subject.setChildren(tfinalSubjectList);
        }
        return finalSubjectList;
    }
}
