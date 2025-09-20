package com.NIED.service;

import com.NIED.model.Member;
import com.NIED.model.MemberSearchResponse;
import com.NIED.model.Recharge;
import com.NIED.model.Transaction;
import com.NIED.repository.MemberRepository;
import com.NIED.repository.RechargeRepository;
import com.NIED.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private RechargeRepository rechargeRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Member registerMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Optional<Member> getMemberById(String id) {
        return memberRepository.findById(id);
    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member updateMemberBalance(String id, double amount) {
        Optional<Member> memberOptional = memberRepository.findById(id);
        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            member.setBalance(member.getBalance() + amount);
            return memberRepository.save(member);
        }
        return null; // Or throw an exception
    }

    @Override
    public Optional<MemberSearchResponse> getMemberByPhoneWithHistory(String phone) {
        Optional<Member> memberOptional = memberRepository.findByPhone(phone);
        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            // Fetch and set history
            List<Recharge> recharges = rechargeRepository.findByMemberId(member.getId());
            List<Transaction> transactions = transactionRepository.findByMemberId(member.getId());

            MemberSearchResponse response = new MemberSearchResponse();
            response.setMember(member);
            response.setRechargeHistory(recharges);
            response.setPlayedHistory(transactions);

            return Optional.of(response);
        }
        return Optional.empty();
    }
}