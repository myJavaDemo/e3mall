package com.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtil {
	private static JedisPool pool=null;
	static{
		InputStream inputStream = JedisPool.class.getClassLoader().getResourceAsStream("redis.properties");
		Properties pro = new Properties();
		try {
			pro.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxIdle(Integer.parseInt(pro.getProperty("redis.maxTotal")));
		poolConfig.setMinIdle(Integer.parseInt(pro.get("redis.maxIdle").toString()));
		poolConfig.setMaxTotal(Integer.parseInt(pro.getProperty("redis.minIdle")));
		pool = new JedisPool(poolConfig, pro.getProperty("redis.url"), Integer.parseInt(pro.getProperty("redis.port")));
	}
	public static Jedis getResource() {
		return pool.getResource();
	}
	public static void main(String[] args) {
		Jedis jedis = getResource();
		System.out.println(jedis.get("username"));
	}
}
