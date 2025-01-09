package com.ste1la.mahjongtournament.Model;

import lombok.Data;

/**
 * @program: mahjong-tournament
 * @description:
 * @author: ste1la
 * @create: 2025-01-08 16:39
 **/
@Data
public class Player {
    private Long id;         // 玩家ID
    private String name;     // 玩家姓名
    private double totalScore; // 玩家总计得分（支持小数）
    private boolean isDummy = false; // 是否是 dummy 玩家
}