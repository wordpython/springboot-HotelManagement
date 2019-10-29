package com.wordpython.admin.service.impl;//package com.wordpython.service.impl;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import com.wordpython.service.GoodsService;
//
///**
// * 获取数据策略：先从缓存中获取数据，没有则取数据表中 数据，再将数据写入缓存
// */
//@Service
//@Transactional
//public class GoodsServiceImpl implements GoodsService {
//
//	@Autowired
//	private GoodsDao goodsDao;
//	@Autowired
//	private RedisUtil redisUtil;
//
//
//	 /**
//     * 更新（添加）数据策略：先更新数据表，成功之后，删除原来的缓存，再更新缓存
//     */
//	@Override
//	public int saveGoods(Goods goods) {
//		int i=goodsDao.saveGoods(goods);
//		if(i!=0) {//插入成功
//			if(redisUtil.hasKey("newGoods10")) {
//				redisUtil.del("newGoods10");//删除原来缓存的key
//			}
//			redisUtil.set("newGoods10", goodsDao.selectGoodsOfTime(10));//读取最新10条数据加入缓存
//			System.out.println("有新数据插入数据库，已添加进缓存");
//		}
//		return i;
//	}
//
//	@Override
//	public Goods selectGoods(Goods goods) {
//		return goodsDao.selectGoods(goods);
//	}
//
//	@Override
//	public int deleteGoods(String uuid) {
//		int i=goodsDao.deleteGoods(uuid);
//		if(i!=0) {//删除成功
//			if(redisUtil.hasKey("newGoods10")) {
//				redisUtil.del("newGoods10");//删除原来缓存的key
//			}
//			redisUtil.set("newGoods10", goodsDao.selectGoodsOfTime(10));//读取最新10条数据加入缓存
//			System.out.println("删除数据库的数据，已更新缓存");
//		}
//		return i;
//	}
//
//	@Override
//	public List<Goods> selectGoodsOfTimeFirst(Integer n) {
//		return goodsDao.selectGoodsOfTime(n);
//	}
//	/*
//	 * 获取数据策略：最新10条数据，因为新数据插入数据库后加入缓存，所以缓存中一定有最新数据
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Goods> selectGoodsOfTime(Integer n) {
//		String key="newGoods"+n;
//		System.out.println("key:----:"+key);
//		boolean hasKey=redisUtil.hasKey(key);
//		if(!hasKey) {
//			System.out.println("该key不存在缓存中，正在加入缓存中");
//			redisUtil.set("newGoods10", goodsDao.selectGoodsOfTime(n));//读取最新10条数据加入缓存
//		}
//		List<Goods> goods = (List<Goods>) redisUtil.get(key);
//		System.out.println("goods:----:"+goods);
//		return goods;
//	}
//}
