package com.hyn.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hyn.dto.req.UserUpdateReqDto;
import com.hyn.dto.resp.UserRespDto;
import com.hyn.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    IPage<UserRespDto> searchUsers(Page<?> page,@Param("ew") QueryWrapper<?> userQueryWrapper);

    List<UserRespDto> searchUsersByTags(@Param("ew") QueryWrapper<?> queryWrapper);

    UserRespDto getCurrentUser(@Param("id") Long id);

    Boolean updateDetailsById(@Param("user") UserUpdateReqDto user);
}