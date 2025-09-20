package com.NIED.controller;

import com.NIED.model.Member;
import com.NIED.model.MemberSearchResponse;
import com.NIED.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<Member> registerMember(@RequestBody Member member) {
        Member newMember = memberService.registerMember(member);
        return new ResponseEntity<>(newMember, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable String id) {
        Optional<Member> member = memberService.getMemberById(id);
        return member.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> members = memberService.getAllMembers();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @PutMapping("/{id}/update-balance")
    public ResponseEntity<Member> updateMemberBalance(@PathVariable String id, @RequestBody double amount) {
        Member updatedMember = memberService.updateMemberBalance(id, amount);
        if (updatedMember != null) {
            return new ResponseEntity<>(updatedMember, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/search")
    public ResponseEntity<MemberSearchResponse> searchMemberByPhone(@RequestBody String phone) {
        // This line has been corrected
        Optional<MemberSearchResponse> responseOptional = memberService.getMemberByPhoneWithHistory(phone);
        return responseOptional.map(response -> new ResponseEntity<>(response, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}