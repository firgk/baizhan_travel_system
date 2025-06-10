package com.itbaizhan.travel.controller.backstage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbaizhan.travel.bean.Result;
import com.itbaizhan.travel.pojo.Admin;
import com.itbaizhan.travel.pojo.Member;
import com.itbaizhan.travel.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/backstage/member")
public class MemberControllerBack {
    @Autowired
    private MemberService memberService;

    @RequestMapping("/all")
    public ModelAndView all(@RequestParam(defaultValue = "1") int page,
                            @RequestParam(defaultValue = "10") int size){
        ModelAndView modelAndView = new ModelAndView();
        Page<Member> memberPage = memberService.findPage(page, size);
        modelAndView.addObject("memberPage",memberPage);
        modelAndView.setViewName("/backstage/member_all");
        return modelAndView;
    }

    @RequestMapping("/add")
    public String add(Member member){
        memberService.add(member);
        return "redirect:/backstage/member/all";
    }


    @RequestMapping("/del")
    public String del(Integer mid){
        memberService.del(mid);
        return "redirect:/backstage/member/all";
    }

    @RequestMapping("/updateStatus")
    public String updateStatus(Integer mid){
        memberService.updateStatus(mid);
        return "redirect:/backstage/member/all";
    }

}
