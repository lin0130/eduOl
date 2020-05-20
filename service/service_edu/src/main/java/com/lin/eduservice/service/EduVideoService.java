package com.lin.eduservice.service;

import com.lin.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lin.eduservice.entity.vo.VideoInfoForm;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-05-06
 */
public interface EduVideoService extends IService<EduVideo> {
    void addVideo(VideoInfoForm videoInfo);
    boolean removeVideoById(String id);
    boolean removeByCourseId(String courseId);
}
