package com.ste1la.mahjongtournament.Controller;

/**
 * @program: mahjong-tournament
 * @description:
 * @author: ste1la
 * @create: 2025-01-08 16:40
 **/
import com.ste1la.mahjongtournament.Model.Group;
import com.ste1la.mahjongtournament.Model.Player;
import com.ste1la.mahjongtournament.Service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping
    public String addPlayer(@RequestBody Player player) {
        playerService.addPlayer(player);
        return "Player added successfully!";
    }

    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable Long id) {
        System.out.println("Player %s searched successfully!".formatted(id));
        return playerService.getPlayerById(id);
    }

    @GetMapping
    public List<Player> getAllPlayers() {
        System.out.println("All Players searched successfully!");
        return playerService.getAllPlayers();
    }

    @PutMapping("/{id}")
    public String updatePlayerScore(@PathVariable Long id,@RequestBody Player player) {
        playerService.updatePlayerScore(id,player.getName(),player.getSchool(), player.getTotalScore());
        System.out.println("Player %s updated successfully!".formatted(id));
        return "Player score updated successfully!";
    }

    @DeleteMapping("/{id}")
    public String deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
        return "Player deleted successfully!";
    }
}

