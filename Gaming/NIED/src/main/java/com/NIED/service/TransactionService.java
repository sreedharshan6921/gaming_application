package com.NIED.service;

import com.NIED.model.Collection;
import com.NIED.model.Game;
import com.NIED.model.Member;
import com.NIED.model.Transaction;
import com.NIED.repository.CollectionRepository;
import com.NIED.repository.GameRepository;
import com.NIED.repository.MemberRepository;
import com.NIED.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private CollectionRepository collectionRepository;

    public Transaction recordGamePlay(String memberId, String gameId) {
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        Optional<Game> gameOptional = gameRepository.findById(gameId);

        if (memberOptional.isPresent() && gameOptional.isPresent()) {
            Member member = memberOptional.get();
            Game game = gameOptional.get();

            // Check if member has enough balance
            if (member.getBalance() >= game.getPrice()) {
                // Deduct from member's balance
                member.setBalance(member.getBalance() - game.getPrice());
                memberRepository.save(member);

                // Create and save transaction record
                Transaction transaction = new Transaction();
                transaction.setMemberId(memberId);
                transaction.setGameId(gameId);
                transaction.setAmount(game.getPrice());
                transactionRepository.save(transaction);

                // Update daily collection
                updateDailyCollection(game.getPrice());

                return transaction;
            }
        }
        return null; // Handle cases where member/game is not found or balance is insufficient
    }

    private void updateDailyCollection(double amount) {
        // Here, we can find today's collection document and update its amount.
        // A more robust implementation would use a more specific query
        // or ensure that only one collection document exists per day.
        
        Optional<Collection> todayCollectionOptional = collectionRepository.findById("today's_date_id"); // Placeholder logic
        if (todayCollectionOptional.isPresent()) {
            Collection todayCollection = todayCollectionOptional.get();
            todayCollection.setAmount(todayCollection.getAmount() + amount);
            collectionRepository.save(todayCollection);
        } else {
            // Create a new collection document for today
            Collection newCollection = new Collection();
            newCollection.setDate(new Date());
            newCollection.setAmount(amount);
            collectionRepository.save(newCollection);
        }
    }
}