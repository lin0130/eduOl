package com.lin.orderserver.client;

import com.lin.commonuntils.UcenterMemberOder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient("service-ucenter")

public interface UcenterClient {
    //根据课程id查询课程信息
    @PostMapping("/userserver/member/getInfoUc/{id}")
    public UcenterMemberOder getInfo(@PathVariable("id") String id);
}