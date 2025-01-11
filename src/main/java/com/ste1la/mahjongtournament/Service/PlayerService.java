package com.ste1la.mahjongtournament.Service;

/**
 * @program: mahjong-tournament
 * @description:
 * @author: ste1la
 * @create: 2025-01-08 16:39
 **/

import com.ste1la.mahjongtournament.Model.Group;
import com.ste1la.mahjongtournament.Model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ste1la.mahjongtournament.Repository.PlayerDAO;
import java.util.*;

@Service
public class PlayerService {
    @Autowired
    private PlayerDAO playerDAO;

    public void addPlayer(Player player) {
        playerDAO.insertPlayer(player);
    }

    public Player getPlayerById(Long id) {
        return playerDAO.selectPlayerById(id);
    }

    public List<Player> getAllPlayers() {
        return playerDAO.selectAllPlayers();
    }

    public void updatePlayerScore(Long id, String name, String school, boolean isSleeping, double totalScore) {
        Player player = playerDAO.selectPlayerById(id);
        if (player != null) {
            player.setName(name);
            player.setSchool(school);
            player.setSleeping(isSleeping);
            player.setTotalScore(totalScore);
            playerDAO.updatePlayerScore(player);
        }
    }

    public void deletePlayer(Long id) {
        playerDAO.deletePlayerById(id);
    }

}
