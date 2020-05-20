package com.lin.eduservice.controller;


import com.lin.commonuntils.R;
import com.lin.eduservice.entity.EduVideo;
import com.lin.eduservice.entity.vo.VideoInfoForm;
import com.lin.eduservice.service.EduVideoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-05-06
 */
@Api
@RestController
@CrossOrigin
@RequestMapping("/eduservice/video")
public class EduVideoController {
    @Autowired
    private EduVideoService eduVideoService;

    @GetMapping("getvideo/{id}")
    public R getVideo(@PathVariable String id){
        EduVideo video = eduVideoService.getById(id);
        if (video!=null) {
            return R.ok().data("video", video);
        }else
        {
            return R.error();
        }
    }

    @PostMapping("addVideo")
    public R addVideo(@RequestBody VideoInfoForm videoInfo){
        eduVideoService.addVideo(videoInfo);
        return R.ok();
    }

    @DeleteMapping("delete/{id}")
    public R deleteVideo(@PathVariable String id)
    {
        eduVideoService.removeVideoById(id);
        return R.ok();
    }

    @PutMapping("update")
    public R updateVideo(@RequestBody EduVideo eduVideo){
        eduVideoService.updateById(eduVideo);
        return R.ok();
    }
}

