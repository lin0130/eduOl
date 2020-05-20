package com.lin.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lin.eduservice.entity.EduChapter;
import com.lin.eduservice.entity.EduVideo;
import com.lin.eduservice.entity.vo.ChapterVo;
import com.lin.eduservice.entity.vo.VideoVo;
import com.lin.eduservice.mapper.EduChapterMapper;
import com.lin.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lin.eduservice.service.EduVideoService;
import com.lin.servicebase.exceptionhandler.CustomException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-05-06
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public List<ChapterVo> chapterList(String courseId) {

        QueryWrapper<EduChapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id",courseId);
        List<EduChapter> chapters = baseMapper.selectList(chapterQueryWrapper);

        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id",courseId);
        List<EduVideo> videos = eduVideoService.list(videoQueryWrapper);

        List<ChapterVo> finalList = new ArrayList<>();

        for (EduChapter chapter : chapters) {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter,chapterVo);
            finalList.add(chapterVo);

            List<VideoVo> videoVos = new ArrayList<>();
            for (EduVideo video : videos) {
                if (video.getChapterId().equals(chapter.getId())) {
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video,videoVo);
                    videoVos.add(videoVo);
                }
            }
            chapterVo.setChildren(videoVos);
        }

        return finalList;
    }

    @Override
    public Boolean deleteChapterById(String id) {
        QueryWrapper<EduVideo> eduVideoQueryWrapper = new QueryWrapper<>();
        eduVideoQueryWrapper.eq("chapter_id",id);
        int count = eduVideoService.count(eduVideoQueryWrapper);
        if (count>0) {
           throw new CustomException(20001,"当前章节含有小节 不能删除");
        }else {
            int result = baseMapper.deleteById(id);
            return result > 0;
        }
    }

    @Override
    public void removeChapterByid(String id) {
        QueryWrapper<EduChapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id",id);
        baseMapper.delete(chapterQueryWrapper);
    }
}
