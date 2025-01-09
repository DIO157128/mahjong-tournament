package com.ste1la.mahjongtournament.Service.DTO;

import com.ste1la.mahjongtournament.Model.Group;
import lombok.Data;

import java.util.List;

/**
 * @program: mahjong-tournament
 * @description:
 * @author: ste1la
 * @create: 2025-01-08 23:49
 **/
@Data
public class Round {
    private int roundNumber;
    private List<Group> groups;

    public Round(int roundNumber, List<Group> groups) {
        this.roundNumber = roundNumber;
        this.groups = groups;
    }
}


