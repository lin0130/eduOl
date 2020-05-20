package com.lin.eduservice.client;

import com.lin.commonuntils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
        import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "service-vod", fallback = VodFileDegradeFeignClient.class)
@Component
public interface
VodClient {
    @DeleteMapping(value = "/admin/vod/video/{videoId}")
    R removeVideo(@PathVariable("videoId") String videoId);

    @DeleteMapping(value = "/admin/vod/video/delete-batch")
    R removeVideoList(@RequestParam("videoIdList") List<String> videoIdList);
}
