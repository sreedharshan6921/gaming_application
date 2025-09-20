package com.NIED.model;


import lombok.Data;

import java.util.List;

@Data
public class MemberSearchResponse {
    private Member member;
    private List<Recharge> rechargeHistory;
    private List<Transaction> playedHistory;
}