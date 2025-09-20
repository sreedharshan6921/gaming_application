package com.NIED.controller;

import com.NIED.model.Game;
import com.NIED.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        Game newGame = gameService.createGame(game);
        return new ResponseEntity<>(newGame, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {
        List<Game> games = gameService.getAllGames();
        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable String id) {
        Optional<Game> game = gameService.getGameById(id);
        return game.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}