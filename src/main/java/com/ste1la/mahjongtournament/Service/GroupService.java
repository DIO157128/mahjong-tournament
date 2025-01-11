package com.ste1la.mahjongtournament.Service;

import com.ste1la.mahjongtournament.Model.Group;
import com.ste1la.mahjongtournament.Model.Player;
import com.ste1la.mahjongtournament.Service.DTO.CalculatePlayer;
import com.ste1la.mahjongtournament.Service.DTO.Round;
import com.ste1la.mahjongtournament.Repository.GroupDAO;
import com.ste1la.mahjongtournament.Repository.PlayerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: mahjong-tournament
 * @description:
 * @author: ste1la
 * @create: 2025-01-08 17:38
 **/
@Service
public class GroupService {
    @Autowired
    private GroupDAO groupDAO;

    @Autowired
    private PlayerDAO playerDAO;

    public void addGroup(Group group) {
        // 查询是否存在相同的分组
        Group existingGroup = groupDAO.selectGroupByNumber(group.getGroupId(), group.getRoundNumber());

        if (existingGroup != null) {
            // 如果存在分组，更新分组信息
            // 减去旧的马点
            subtractOldScoresFromPlayers(existingGroup);

            // 更新分组信息
            groupDAO.updateGroup(group);

            // 加上新的马点
            addNewScoresToPlayers(group);
        } else {
            // 如果不存在分组，插入新分组
            groupDAO.insertGroup(group);

            // 直接加上新的马点
            addNewScoresToPlayers(group);
        }
    }
    /**
     * 从玩家总分中减去旧的马点
     *
     * @param group 旧的分组信息
     */
    private void subtractOldScoresFromPlayers(Group group) {
        if (!group.getPlayer1Name().toLowerCase().contains("dummy player")) {
            System.out.println(playerDAO.updateTotalScoreByName(group.getPlayer1Name(), -group.getPlayer1ConvertedScore()));
        }
        if (group.getPlayer2Name()==null){
            return;
        }
        if (!group.getPlayer2Name().toLowerCase().contains("dummy player")) {
            playerDAO.updateTotalScoreByName(group.getPlayer2Name(), -group.getPlayer2ConvertedScore());
        }
        if (!group.getPlayer3Name().toLowerCase().contains("dummy player")) {
            playerDAO.updateTotalScoreByName(group.getPlayer3Name(), -group.getPlayer3ConvertedScore());
        }
        if (!group.getPlayer4Name().toLowerCase().contains("dummy player")) {
            playerDAO.updateTotalScoreByName(group.getPlayer4Name(), -group.getPlayer4ConvertedScore());
        }
    }

    /**
     * 将新的马点加到玩家总分中
     *
     * @param group 新的分组信息
     */
    private void addNewScoresToPlayers(Group group) {
        if (!group.getPlayer1Name().toLowerCase().contains("dummy")) {
            playerDAO.updateTotalScoreByName(group.getPlayer1Name(), group.getPlayer1ConvertedScore());
        }
        if (group.getPlayer2Name()==null){
            return;
        }
        if (!group.getPlayer2Name().toLowerCase().contains("dummy")) {
            playerDAO.updateTotalScoreByName(group.getPlayer2Name(), group.getPlayer2ConvertedScore());
        }
        if (!group.getPlayer3Name().toLowerCase().contains("dummy")) {
            playerDAO.updateTotalScoreByName(group.getPlayer3Name(), group.getPlayer3ConvertedScore());
        }
        if (!group.getPlayer4Name().toLowerCase().contains("dummy")) {
            playerDAO.updateTotalScoreByName(group.getPlayer4Name(), group.getPlayer4ConvertedScore());
        }
    }
    public List<Group> getAllGroups() {
        return groupDAO.selectAllGroups();
    }
    public List<Round> getAllRounds() {
        // Fetch all groups from the database, grouped by round
        List<Group> allGroups = groupDAO.selectAllGroups();

        // Map groups to rounds
        Map<Integer, List<Group>> groupedByRound = allGroups.stream()
                .collect(Collectors.groupingBy(Group::getRoundNumber));

        // Convert to List<Round>
        List<Round> rounds = groupedByRound.entrySet().stream()
                .sorted(Map.Entry.comparingByKey()) // Sort by round number
                .map(entry -> new Round(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        return rounds;
    }
    // 随机分组（初始分组）
    public List<List<Player>> randomGrouping() {
        List<Player> players = playerDAO.selectAllPlayers();
        Collections.shuffle(players); // 打乱顺序

        return createGroups(players);
    }

    // 瑞士轮分组
    public List<List<Player>> swissPairing() {
        List<Player> players = playerDAO.selectAllPlayers();
        players.sort(Comparator.comparingDouble(Player::getTotalScore).reversed()); // 按马点降序排序

        return createGroups(players);
    }

    // 创建分组并处理非 4 倍数的情况
    private List<List<Player>> createGroups(List<Player> players) {
        int groupSize = 4;
        players.removeIf(Player::isSleeping);
        // 处理非 4 倍数的情况
        int remainder = players.size() % groupSize;
        if (remainder == 1) {
            // 4k+1: 最后一名玩家轮空
            System.out.println("Player " + players.get(players.size() - 1).getName() + " will be idle this round.");
        } else if (remainder == 2 || remainder == 3) {
            // 4k+2 或 4k+3: 添加 dummy 玩家
            int playersToAdd = groupSize - remainder; // 需要添加的 dummy 玩家数量
            for (int i = 0; i < playersToAdd; i++) {
                Player dummyPlayer = new Player();
                dummyPlayer.setName("Dummy Player " + (i + 1));
                dummyPlayer.setTotalScore(0);
                dummyPlayer.setDummy(true); // 标记为 dummy 玩家

                // 随机插入 dummy 玩家到 players 列表的某个位置
                int randomIndex = (int) (Math.random() * players.size());
                players.add(randomIndex, dummyPlayer);
            }
        }

        // 分组
        List<List<Player>> groups = new ArrayList<>();
        for (int i = 0; i < players.size(); i += groupSize) {
            int end = Math.min(i + groupSize, players.size());
            List<Player> group = players.subList(i, end);
            Collections.shuffle(group);
            groups.add(group);
        }
        return groups;
    }

    public List<CalculatePlayer> calculateGroupPoints(List<CalculatePlayer> players) {
        // 按素点降序排名
        players.sort(Comparator.comparingInt(CalculatePlayer::getRawScore).reversed());

        // 分配排名
        for (int i = 0; i < players.size(); i++) {
            players.get(i).setRank(i + 1);
        }

        // 计算马点
        Map<Integer, List<CalculatePlayer>> scoreGroups = new HashMap<>();

        // 按rawScore分组，记录分数相同的玩家
        for (CalculatePlayer player : players) {
            scoreGroups
                    .computeIfAbsent(player.getRawScore(), k -> new ArrayList<>())
                    .add(player);
        }

        for (List<CalculatePlayer> group : scoreGroups.values()) {
            if (group.size() > 1) {
                // 计算共享马点
                double totalSharedPoints = group.stream()
                        .mapToDouble(player -> calculateRankPoints(player.getRank()) +
                                (player.getRawScore() - 30000) / 1000.0)
                        .sum();

                double sharedPointsPerPlayer = totalSharedPoints / group.size();

                // 设置共享马点
                for (CalculatePlayer player : group) {
                    player.setCalculatedPoints(sharedPointsPerPlayer);
                }
            } else {
                // 如果没有共享，正常计算马点
                CalculatePlayer player = group.get(0);
                double rankPoints = calculateRankPoints(player.getRank());
                double placementPoints = (player.getRawScore() - 30000) / 1000.0;
                player.setCalculatedPoints(rankPoints + placementPoints);
            }
        }

        return players;
    }


    /**
     * 根据排名分配排名分
     *
     * @param rank 玩家排名
     * @return 对应的排名分
     */
    private double calculateRankPoints(int rank) {
        switch (rank) {
            case 1:
                return 45.0;
            case 2:
                return 5.0;
            case 3:
                return -15.0;
            case 4:
                return -35.0;
            default:
                return 0.0;
        }
    }
}
