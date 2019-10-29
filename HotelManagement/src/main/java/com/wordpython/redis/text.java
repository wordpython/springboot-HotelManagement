package com.wordpython.redis;

import java.util.List;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class text {
	public static void main(String[] args) {
		/**
		 * 特点 Redis支持数据的持久化，可以将内存中的数据保存在磁盘中，重启的时候可以再次加载进行使用。
		 * Redis不仅仅支持简单的key-value类型的数据，同时还提供list，set，zset，hash等数据结构的存储。
		 * Redis支持数据的备份，即master-slave模式的数据备份。
		 *
		 * 优点 性能极高 – Redis能读的速度是110000次/s,写的速度是81000次/s 。 丰富的数据类型 – Redis支持二进制案例的
		 * Strings, Lists, Hashes, Sets 及 Ordered Sets 数据类型操作。 原子 –
		 * Redis的所有操作都是原子性的，意思就是要么成功执行要么失败完全不执行。单个操作是原子性的。多个操作也支持事务，即原子性，通过MULTI和EXEC指令包起来。
		 * 丰富的特性 – Redis还支持 publish/subscribe, 通知, key 过期等等特性。
		 *
		 */
		// 连接本地的Redis服务
		// redis.clients.jedis.exceptions.JedisConnectionException这个是Redis数据库没有启动的异常，重新启动即可
		Jedis jedis = new Jedis("localhost");
		System.out.println("本地Redis连接服务成功");
		// 查看本地Redis服务状态
		System.out.println("本地Redis服务状态:" + jedis.ping());

		//选择要操作数据库
//		jedis.select(1);//操作第二个数据库
		
		/*
		 * 存数据（5种类型） String类型，Hash类型，List类型，Set类型，SortedSet类型zset（可排序的）
		 */
		// 存储字符串数据
		jedis.set("baidu", "www.baidu.com");
		System.out.println("存储的字符串数据为:" + jedis.get("baidu"));

		// 设置Hash数据类型
		jedis.hset("user:1001", "username", "wordpython");
		jedis.hset("user:1001", "age", "30");
		jedis.hset("user:1001", "gender", "男");

		// 存储list数据
		jedis.lpush("list", "haha1");
		jedis.lpush("list", "haha2");
		jedis.lpush("list", "haha3");
		jedis.lpush("list", "haha4");
		// 取出list数据
		List<String> list = jedis.lrange("list", 0, 4);
		System.out.print("存储的list集合内的数据为:");
		for (String zfc : list) {
			System.out.print(zfc + "  ");
		}
		System.out.println();

		// 设置set类型
		jedis.sadd("NBA", "勇士", "骑士");
		
		//设置sortedset,可排序
		jedis.zadd("english:scoreboard" ,90,"zhangsan");
		jedis.zadd("english:scoreboard" ,91,"lisi");
		jedis.zadd("english:scoreboard" ,93,"wangwu");

		// 获取
		// jedis.keys该方法返回的是一个set，必须要使用set。可以只用强转
		Set<String> keys = jedis.keys("*");
		System.out.print("Redis中的key有:");
		for (String key : keys) {
			System.out.print(key + "  ");
		}
		
		//关闭连接
		jedis.close();
	}
	
	@Test
	public void testJedisPool() {
		//创建一连接池对象
		JedisPool jedisPool=new JedisPool("127.0.0.1",6379);
		//从连接池中获得连接
		Jedis jedis=jedisPool.getResource();
//		jedis.select(1);
		
		//取出字符串
		String result=jedis.get("baidu");
		System.out.println(result);
		
		//关闭连接
		jedis.close();
		//关闭连接池
		jedisPool.close();
	}
}
