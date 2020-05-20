package com.lin.eduservice.service;

import com.lin.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lin.eduservice.entity.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-05-06
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> chapterList(String courseId);

    Boolean deleteChapterById(String id);

    void removeChapterByid(String id);
}
