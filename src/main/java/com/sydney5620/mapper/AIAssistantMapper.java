package com.sydney5620.mapper;

import com.sydney5620.pojo.AIAssistant;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface AIAssistantMapper {

    @Select("select * from ai")
    List<AIAssistant> getAllAIAssistance();

    @Select("select * from ai where ai_name = #{aiName}")
    AIAssistant getAIAssistanceByAIName(String aiName);

    @Update("UPDATE ai Set ai_name = #{aiName}, command = #{command}, creativity = #{creativity}, contextCount = #{contextCount}, replyLength = #{replyLength} where ai_id = #{aiId}")
    Integer updateAIAssistance(AIAssistant aiAssistant);

    @Delete("delete from ai where ai_id = #{aiId}")
    Integer deleteAIAssistanceByAIId(Long aIId);

//    @Update("UPDATE ai Set content = #{content} where userId = #{userId}")
    void updateContent(AIAssistant aiAssistant);
}
