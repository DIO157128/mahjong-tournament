package com.ste1la.mahjongtournament.Service.DTO;

import lombok.Data;

/**
 * @program: mahjong-tournament
 * @description:
 * @author: ste1la
 * @create: 2025-01-09 00:51
 **/
@Data
public class CalculatePlayer {
    private String name;
    private int rawScore; // 素点
    private double calculatedPoints; // 马点
    private int rank; // 排名
    private boolean isDummy; // 是否是 Dummy 玩家

    public CalculatePlayer(String name, int rawScore, boolean isDummy) {
        this.name = name;
        this.rawScore = rawScore;
        this.isDummy = isDummy;
    }

}

