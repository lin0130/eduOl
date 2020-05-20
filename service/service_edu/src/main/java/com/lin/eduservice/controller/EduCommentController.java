//package com.lin.eduservice.controller;
//
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.core.toolkit.StringUtils;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.lin.commonuntils.R;
//import com.lin.eduservice.entity.EduComment;
//import com.lin.eduservice.service.EduCommentService;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * <p>
// * 评论 前端控制器
// * </p>
// *
// * @author testjava
// * @since 2020-05-16
// */
//@RestController
//@RequestMapping("/eduservice/comment")
//@CrossOrigin
//public class EduCommentController {
//
//    @Autowired
//    private EduCommentService commentService;
//    @Autowired
//    private UcenterClient ucenterClient;
//
//    //根据课程id查询评论列表
//    @ApiOperation(value = "评论分页列表")
//    @GetMapping("{page}/{limit}")
//    public R index(
//            @ApiParam(name = "page", value = "当前页码", required = true)
//            @PathVariable Long page,
//
//            @ApiParam(name = "limit", value = "每页记录数", required = true)
//            @PathVariable Long limit,
//
//            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
//                    String courseId) {
//        Page<EduComment> pageParam = new Page<>(page, limit);
//
//        QueryWrapper<EduComment> wrapper = new QueryWrapper<>();
//        wrapper.eq("course_id",courseId);
//
//        commentService.page(pageParam,wrapper);
//        List<EduComment> commentList = pageParam.getRecords();
//
//        Map<String, Object> map = new HashMap<>();
//        map.put("items", commentList);
//        map.put("current", pageParam.getCurrent());
//        map.put("pages", pageParam.getPages());
//        map.put("size", pageParam.getSize());
//        map.put("total", pageParam.getTotal());
//        map.put("hasNext", pageParam.hasNext());
//        map.put("hasPrevious", pageParam.hasPrevious());
//        return R.ok().data(map);
//    }
//
//    @ApiOperation(value = "添加评论")
//    @PostMapping("auth/save")
//    public R save(@RequestBody EduComment comment, HttpServletRequest request) {
//        String memberId = JwtUtils.getMemberIdByJwtToken(request);
//        if(StringUtils.isEmpty(memberId)) {
//            return R.error().code(28004).message("请登录");
//        }
//        comment.setMemberId(memberId);
//
//        UcenterMemberPay ucenterInfo = ucenterClient.getUcenterPay(memberId);
//
//        comment.setNickname(ucenterInfo.getNickname());
//        comment.setAvatar(ucenterInfo.getAvatar());
//
//        commentService.save(comment);
//        return R.ok();
//    }
//}
//
