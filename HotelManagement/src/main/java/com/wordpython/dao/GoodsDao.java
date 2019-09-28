package com.wordpython.dao;

import java.util.List;

import com.wordpython.entity.Goods;

public interface GoodsDao {
	//保存失物
	public int saveGoods(Goods goods);
	//精准查询
	public Goods selectGoods(Goods goods);
	//删除
	public int deleteGoods(String uuid);
	//根据表中的日期进行排序，找到最新的10条数据
	public List<Goods> selectGoodsOfTime(Integer n);
}
