package com.itbaizhan.travel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itbaizhan.travel.pojo.Member;

public interface MemberMapper extends BaseMapper<Member> {
    void deleteByMid(Integer mid);
    void deleteMemberAllFavourite(Integer mid);

    void insert2(Member member);
}
