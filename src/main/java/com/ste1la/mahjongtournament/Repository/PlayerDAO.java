package com.ste1la.mahjongtournament.Repository;

import com.ste1la.mahjongtournament.Model.Player;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PlayerDAO {

    // 插入新玩家
    @Insert("INSERT INTO player (name, total_score) VALUES (#{name}, #{totalScore})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertPlayer(Player player);

    // 根据ID查询玩家
    @Select("SELECT * FROM player WHERE id = #{id}")
    @Results(id = "one", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "total_score", property = "totalScore"),
    })
    Player selectPlayerById(Long id);

    // 查询所有玩家
    @Select("SELECT * FROM player")
    @Results(id = "all", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "total_score", property = "totalScore"),
    })
    List<Player> selectAllPlayers();

    // 更新玩家的总计得分
    @Update("UPDATE player SET total_score = #{totalScore}, name = #{name} WHERE id = #{id}")
    void updatePlayerScore(Player player);

    // 删除玩家
    @Delete("DELETE FROM player WHERE id = #{id}")
    void deletePlayerById(Long id);

    @Update("UPDATE player SET total_score = total_score + #{convertedScore} WHERE name = #{name}")
    int updateTotalScoreByName(@Param("name") String name, @Param("convertedScore") double convertedScore);

}
