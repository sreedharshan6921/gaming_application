package com.NIED.service;

import com.NIED.model.Member;
import com.NIED.model.Recharge;
import com.NIED.repository.MemberRepository;
import com.NIED.repository.RechargeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RechargeService {

    @Autowired
    private RechargeRepository rechargeRepository;

    @Autowired
    private MemberRepository memberRepository;

    public Recharge performRecharge(String memberId, double amount) {
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            // Update member's balance
            member.setBalance(member.getBalance() + amount);
            memberRepository.save(member);

            // Create and save the recharge record
            Recharge recharge = new Recharge();
            recharge.setMemberId(memberId);
            recharge.setAmount(amount);
            return rechargeRepository.save(recharge);
        }
        return null; // Handle case where member is not found
    }
}