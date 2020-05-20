package com.lin.userserver.service;

import com.lin.userserver.entity.LoginInfoVo;
import com.lin.userserver.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lin.userserver.entity.vo.LoginVo;
import com.lin.userserver.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-05-15
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(LoginVo loginVo);

    void register(RegisterVo registerVo);

    LoginInfoVo getLoginInfo(String memberId);

    UcenterMember getByOpenid(String openid);

    Integer countRegisterByDay(String day);
}
