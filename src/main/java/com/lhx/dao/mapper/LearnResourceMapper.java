package com.lhx.dao.mapper;

import com.lhx.dao.entity.LearnResource;
import com.lhx.dao.entity.LearnResourceExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LearnResourceMapper {
    int countByExample(LearnResourceExample example);

    int deleteByExample(LearnResourceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LearnResource record);

    int insertSelective(LearnResource record);

    List<LearnResource> selectByExample(LearnResourceExample example);

    LearnResource selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LearnResource record, @Param("example") LearnResourceExample example);

    int updateByExample(@Param("record") LearnResource record, @Param("example") LearnResourceExample example);

    int updateByPrimaryKeySelective(LearnResource record);

    int updateByPrimaryKey(LearnResource record);
}