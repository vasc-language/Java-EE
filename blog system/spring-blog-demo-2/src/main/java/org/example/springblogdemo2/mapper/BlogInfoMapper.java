package org.example.springblogdemo2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.springblogdemo2.pojo.dataobject.BlogInfo;

@Mapper
public interface BlogInfoMapper extends BaseMapper<BlogInfo> {
}