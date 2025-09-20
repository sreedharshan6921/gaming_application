package com.NIED.service;

import com.NIED.model.Member;
import com.NIED.model.MemberSearchResponse;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    Optional<MemberSearchResponse> getMemberByPhoneWithHistory(String phone);
    Member registerMember(Member member);
    Optional<Member> getMemberById(String id);
    List<Member> getAllMembers();
    Member updateMemberBalance(String id, double amount);
}