package com.ste1la.mahjongtournament.Model;

/**
 * @program: mahjong-tournament
 * @description:
 * @author: ste1la
 * @create: 2025-01-08 16:39
 **/
import lombok.Data;

import java.util.List;
@Data
public class Group {
    private Long id;                  // 分组ID
    private int roundNumber;          // Round this group belongs to
    private int groupId;              // Group ID within the round

    private String player1Name;       // 玩家1姓名
    private int player1RawScore;      // 玩家1素点
    private double player1ConvertedScore; // 玩家1马点

    private String player2Name;       // 玩家2姓名
    private int player2RawScore;      // 玩家2素点
    private double player2ConvertedScore; // 玩家2马点

    private String player3Name;       // 玩家3姓名
    private int player3RawScore;      // 玩家3素点
    private double player3ConvertedScore; // 玩家3马点

    private String player4Name;       // 玩家4姓名
    private int player4RawScore;      // 玩家4素点
    private double player4ConvertedScore; // 玩家4马点
}

