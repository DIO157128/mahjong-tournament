CREATE SCHEMA mahjong_tournament;
USE mahjong_tournament;
CREATE TABLE player (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY, -- 玩家ID，主键
                        name VARCHAR(100) NOT NULL UNIQUE,   -- 玩家姓名，必须唯一
                        total_score INT DEFAULT 0            -- 玩家总计得分
);
CREATE TABLE game_group (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY, -- 分组ID，主键
                            round_number INT NOT NULL,            -- 比赛轮次的编号
                            group_id INT NOT NULL,                -- 轮次中的小组组号
                            player1_name VARCHAR(100) NOT NULL,   -- 玩家1姓名
                            player1_raw_score INT NOT NULL,       -- 玩家1素点
                            player1_converted_score INT NOT NULL, -- 玩家1转化的马点

                            player2_name VARCHAR(100) NOT NULL,   -- 玩家2姓名
                            player2_raw_score INT NOT NULL,       -- 玩家2素点
                            player2_converted_score INT NOT NULL, -- 玩家2转化的马点

                            player3_name VARCHAR(100) NOT NULL,   -- 玩家3姓名
                            player3_raw_score INT NOT NULL,       -- 玩家3素点
                            player3_converted_score INT NOT NULL, -- 玩家3转化的马点

                            player4_name VARCHAR(100) NOT NULL,   -- 玩家4姓名
                            player4_raw_score INT NOT NULL,       -- 玩家4素点
                            player4_converted_score INT NOT NULL  -- 玩家4转化的马点
);
