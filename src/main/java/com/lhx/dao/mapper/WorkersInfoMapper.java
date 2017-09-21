package com.lhx.dao.mapper;

import com.lhx.dao.entity.WorkersInfo;
import com.lhx.dao.entity.WorkersInfoExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WorkersInfoMapper {
    int countByExample(WorkersInfoExample example);

    int deleteByExample(WorkersInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WorkersInfo record);

    int insertSelective(WorkersInfo record);

    List<WorkersInfo> selectByExample(WorkersInfoExample example);

    WorkersInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WorkersInfo record, @Param("example") WorkersInfoExample example);

    int updateByExample(@Param("record") WorkersInfo record, @Param("example") WorkersInfoExample example);

    int updateByPrimaryKeySelective(WorkersInfo record);

    int updateByPrimaryKey(WorkersInfo record);
}