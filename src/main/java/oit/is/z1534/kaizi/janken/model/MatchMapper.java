package oit.is.z1534.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MatchMapper {

  //全試合結果を取得
  @Select("SELECT * from matches")
  ArrayList<Match> selectAllMatch();

  //最新の試合を取得
  @Select("SELECT * from matches where id = (SELECT count(*) from matches)")
  Match selectLastMatch();

  //指定したidの試合結果を取得
  @Select("SELECT * FROM matches WHERE id = #{id}")
  Match selectById(int id);

  /**
   * @param match
   */
  @Update("UPDATE matches SET is_active=#{is_active} WHERE id = #{id}")
  void updateById(Match match);

  /**
  * @param match
  */
  @Insert("INSERT INTO matches (user_1,user_2,user_1_hand,user_2_hand,is_active) VALUES (#{user_1},#{user_2},#{user_1_hand},#{user_2_hand},#{is_active});")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertMatch(Match match);

}
