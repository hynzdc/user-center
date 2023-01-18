package com.hyn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hyn.entity.Tag;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TagMapper extends BaseMapper<Tag> {
}