package com.lin.userserver.controller;


import com.lin.commonuntils.R;
import com.lin.commonuntils.UcenterMemberOder;
import com.lin.commonuntils.untils.JwtUntils;
import com.lin.servicebase.exceptionhandler.CustomException;
import com.lin.userserver.entity.LoginInfoVo;
import com.lin.userserver.entity.UcenterMember;
import com.lin.userserver.entity.vo.LoginVo;
import com.lin.userserver.entity.vo.RegisterVo;
import com.lin.userserver.service.UcenterMemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-05-15
 */
@CrossOrigin
@RestController
@RequestMapping("/userserver/member")
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService memberService;

    @ApiOperation(value = "会员登录")
    @PostMapping("login")
    public R login(@RequestBody LoginVo loginVo) {
        String token = memberService.login(loginVo);
        return R.ok().data("token", token);
    }

    @ApiOperation(value = "会员注册")
    @PostMapping("register")
    public R register(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return R.ok();
    }

    @ApiOperation(value = "根据token获取登录信息")
    @GetMapping("auth/getLoginInfo")
    public R getLoginInfo(HttpServletRequest request){
        try {
            String memberId = JwtUntils.getMemberIdByJwtToken(request);
            LoginInfoVo loginInfoVo = memberService.getLoginInfo(memberId);
            return R.ok().data("item", loginInfoVo);
        }catch (Exception e){
            e.printStackTrace();
            throw new CustomException(20001,"error");
        }
    }

    //根据token字符串获取用户信息
    @PostMapping("getInfoUc/{id}")
    public UcenterMemberOder getInfo(@PathVariable String id) {
        //根据用户id获取用户信息
        UcenterMember ucenterMember = memberService.getById(id);
        UcenterMemberOder memeber = new UcenterMemberOder();
        BeanUtils.copyProperties(ucenterMember,memeber);
        return memeber;
    }

    @GetMapping(value = "countregister/{day}")
    public R registerCount(
            @PathVariable String day){
        Integer count = memberService.countRegisterByDay(day);
        return R.ok().data("countRegister", count);
    }
}

