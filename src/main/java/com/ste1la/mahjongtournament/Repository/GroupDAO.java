package com.ste1la.mahjongtournament.Repository;

import com.ste1la.mahjongtournament.Model.Group;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GroupDAO {

    // 插入新分组
    @Insert("INSERT INTO game_group (" +
            "round_number, group_id," +
            "player1_name, player1_raw_score, player1_converted_score, " +
            "player2_name, player2_raw_score, player2_converted_score, " +
            "player3_name, player3_raw_score, player3_converted_score, " +
            "player4_name, player4_raw_score, player4_converted_score" +
            ") VALUES (" +
            "#{roundNumber}, #{groupId}," +
            "#{player1Name}, #{player1RawScore}, #{player1ConvertedScore}, " +
            "#{player2Name}, #{player2RawScore}, #{player2ConvertedScore}, " +
            "#{player3Name}, #{player3RawScore}, #{player3ConvertedScore}, " +
            "#{player4Name}, #{player4RawScore}, #{player4ConvertedScore})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertGroup(Group group);

    // 查询分组信息通过组号
    @Select("SELECT * FROM game_group WHERE group_id = #{groupId} AND round_number = #{roundNumber}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "round_number", property = "roundNumber"),
            @Result(column = "group_id", property = "groupId"),

            @Result(column = "player1_name", property = "player1Name"),
            @Result(column = "player1_raw_score", property = "player1RawScore"),
            @Result(column = "player1_converted_score", property = "player1ConvertedScore"),

            @Result(column = "player2_name", property = "player2Name"),
            @Result(column = "player2_raw_score", property = "player2RawScore"),
            @Result(column = "player2_converted_score", property = "player2ConvertedScore"),

            @Result(column = "player3_name", property = "player3Name"),
            @Result(column = "player3_raw_score", property = "player3RawScore"),
            @Result(column = "player3_converted_score", property = "player3ConvertedScore"),

            @Result(column = "player4_name", property = "player4Name"),
            @Result(column = "player4_raw_score", property = "player4RawScore"),
            @Result(column = "player4_converted_score", property = "player4ConvertedScore")
    })
    Group selectGroupByNumber(int groupId, int roundNumber);

    // 查询所有分组信息
    @Select("SELECT * FROM game_group")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "round_number", property = "roundNumber"),
            @Result(column = "group_id", property = "groupId"),

            @Result(column = "player1_name", property = "player1Name"),
            @Result(column = "player1_raw_score", property = "player1RawScore"),
            @Result(column = "player1_converted_score", property = "player1ConvertedScore"),

            @Result(column = "player2_name", property = "player2Name"),
            @Result(column = "player2_raw_score", property = "player2RawScore"),
            @Result(column = "player2_converted_score", property = "player2ConvertedScore"),

            @Result(column = "player3_name", property = "player3Name"),
            @Result(column = "player3_raw_score", property = "player3RawScore"),
            @Result(column = "player3_converted_score", property = "player3ConvertedScore"),

            @Result(column = "player4_name", property = "player4Name"),
            @Result(column = "player4_raw_score", property = "player4RawScore"),
            @Result(column = "player4_converted_score", property = "player4ConvertedScore")
    })
    List<Group> selectAllGroups();

    @Update("UPDATE game_group SET " +
            "player1_name = #{player1Name}, player1_raw_score = #{player1RawScore}, player1_converted_score = #{player1ConvertedScore}, " +
            "player2_name = #{player2Name}, player2_raw_score = #{player2RawScore}, player2_converted_score = #{player2ConvertedScore}, " +
            "player3_name = #{player3Name}, player3_raw_score = #{player3RawScore}, player3_converted_score = #{player3ConvertedScore}, " +
            "player4_name = #{player4Name}, player4_raw_score = #{player4RawScore}, player4_converted_score = #{player4ConvertedScore} " +
            "WHERE round_number = #{roundNumber} AND group_id = #{groupId}")
    void updateGroup(Group group);
}
