package com.redis;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisTest {
	@Test
	public void Test01(){
		Jedis jedis = new Jedis("192.168.80.131", 6379);		
		String username = jedis.get("username");
		System.out.println(username);
	}
	@Test
	public void Test02(){
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxIdle(20);
		poolConfig.setMinIdle(5);
		poolConfig.setMaxTotal(10);
		JedisPool pool = new JedisPool(poolConfig, "192.168.80.131", 6379);
		Jedis jedis = pool.getResource();
		jedis.set("aa", "bb");
		System.out.println(jedis.get("aa"));
		jedis.close();
		pool.close();
	}
}
