package com.zhizheng.mapper;

import com.zhizheng.model.Goods;
import com.zhizheng.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper {
    int deleteByPrimaryKey(Long goodsId);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Long goodsId);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    void deleteAll();

    List<Goods> findAllByGoodsIdBetween(@Param("param1") long l, @Param("param2")long l1);

    Object findAllByGoodsIdIn(List<Long> goodsIds);

    List<Goods> selectAll();
}