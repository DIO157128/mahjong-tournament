package com.ste1la.mahjongtournament.Controller;

import com.ste1la.mahjongtournament.Model.Group;
import com.ste1la.mahjongtournament.Model.Player;
import com.ste1la.mahjongtournament.Service.DTO.CalculatePlayer;
import com.ste1la.mahjongtournament.Service.DTO.Round;
import com.ste1la.mahjongtournament.Service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: mahjong-tournament
 * @description:
 * @author: ste1la
 * @create: 2025-01-08 17:41
 **/
@RestController
@RequestMapping("/api/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping("/save")
    public String addGroup(@RequestBody Group group) {
        groupService.addGroup(group);
        return "Group added successfully!";
    }


    @GetMapping
    public List<Group> getAllGroups() {
        return groupService.getAllGroups();
    }

    @GetMapping("/existing")
    public ResponseEntity<Map<String, Object>> getExistingGroups() {
        List<Round> rounds = groupService.getAllRounds();
        int currentRound = rounds.isEmpty() ? 0 : rounds.size();

        Map<String, Object> response = new HashMap<>();
        response.put("currentRound", currentRound);
        response.put("rounds", rounds);

        return ResponseEntity.ok(response);
    }
    // 随机分组
    @GetMapping("/random")
    public List<List<Player>> randomGrouping() {
        System.out.println("randomGrouping");
        return groupService.randomGrouping();
    }

    // 瑞士轮分组
    @GetMapping("/swiss")
    public List<List<Player>> swissPairing() {
        System.out.println("swissPairing");
        return groupService.swissPairing();
    }

    @PostMapping("/calculate-points")
    public ResponseEntity<List<CalculatePlayer>> calculatePoints(@RequestBody List<CalculatePlayer> players) {
        List<CalculatePlayer> results = groupService.calculateGroupPoints(players);
        return ResponseEntity.ok(results);
    }
}
