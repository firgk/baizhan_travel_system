package com.itbaizhan.travel.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbaizhan.travel.bean.Result;
import com.itbaizhan.travel.mapper.MemberMapper;
import com.itbaizhan.travel.pojo.Admin;
import com.itbaizhan.travel.pojo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Value("${project.path}")
    private String projectPath;

    // 注册
    public Result register(Member member) {
        // 1.保存用户
        // 验证用户名是否重复
        QueryWrapper<Member> queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", member.getUsername());
        List<Member> members = memberMapper.selectList(queryWrapper);
        if (members.size() > 0) {
            return new Result(false, "用户名已存在");
        }

        // 加密密码
        String password = member.getPassword();
        password = encoder.encode(password);
        member.setPassword(password);
        // 设置用户状态为 true
        member.setActive(true);
        // 保存用户
        memberMapper.insert(member);
        return new Result(true, "注册成功！");
    }


    public Result login(String name, String password) {
        Member member = null;

        // 根据用户名查询
        if (member == null) {
            QueryWrapper<Member> queryWrapper = new QueryWrapper();
            queryWrapper.eq("username", name);
            member = memberMapper.selectOne(queryWrapper);
        }

        // 没有查询到用户
        if (member == null) {
            return new Result(false, "用户名或密码错误");
        }

        // 验证密码
        boolean flag = encoder.matches(password, member.getPassword());
        if (!flag) {
            return new Result(false, "用户名或密码错误");
        }

        // 验证是否激活
        if (!member.isActive()) {
            return new Result(false, "用户未激活，请登录邮箱激活用户");
        }

        return new Result(true, "登录成功", member);
    }

    public Page<Member> findPage(int page, int size) {
        Page selectPage = memberMapper.selectPage(new Page(page, size), null);
        return selectPage;
    }

    public void del(Integer mid) {
        memberMapper.deleteByMid(mid);
        memberMapper.deleteMemberAllFavourite(mid);
    }

    public void updateStatus(Integer mid) {
        Member member = memberMapper.selectById(mid);
        member.setActive(!member.isActive()); // 状态取反
        memberMapper.updateById(member);
    }

    public void add(Member member) {
        member.setPassword(encoder.encode(member.getPassword()));
        memberMapper.insert2(member);
//        adminMapper.addAdminRole(admin.getAid(),2);
    }
}
