package com.ste1la.mahjongtournament.Repository;

import com.ste1la.mahjongtournament.Model.Player;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PlayerDAO {

    // 插入新玩家
    @Insert("INSERT INTO player (name, school, total_score, is_sleeping) VALUES (#{name}, #{school}, #{totalScore}, #{isSleeping})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertPlayer(Player player);

    // 根据ID查询玩家
    @Select("SELECT * FROM player WHERE id = #{id}")
    @Results(id = "one", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "school", property = "school"),
            @Result(column = "total_score", property = "totalScore"),
            @Result(column = "is_sleeping", property = "isSleeping"),
    })
    Player selectPlayerById(Long id);

    // 查询所有玩家
    @Select("SELECT * FROM player")
    @Results(id = "all", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "school", property = "school"),
            @Result(column = "total_score", property = "totalScore"),
            @Result(column = "is_sleeping", property = "isSleeping"),
    })
    List<Player> selectAllPlayers();

    // 更新玩家的总计得分
    @Update("UPDATE player SET total_score = #{totalScore}, name = #{name}, school = #{school}, is_sleeping = #{isSleeping} WHERE id = #{id}")
    void updatePlayerScore(Player player);

    // 删除玩家
    @Delete("DELETE FROM player WHERE id = #{id}")
    void deletePlayerById(Long id);

    @Update("UPDATE player SET total_score = total_score + #{convertedScore} WHERE name = #{name}")
    int updateTotalScoreByName(@Param("name") String name, @Param("convertedScore") double convertedScore);

}
