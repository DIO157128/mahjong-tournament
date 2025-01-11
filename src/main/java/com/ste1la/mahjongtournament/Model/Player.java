package com.ste1la.mahjongtournament.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private String school;   // 玩家所属学校
    private double totalScore = 0.0f; // 玩家总计得分（支持小数）
    private boolean isDummy = false; // 是否是 dummy 玩家
    @JsonProperty("isSleeping")
    private boolean isSleeping = false;
}
