package com.example.demo.config;

import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.Pool;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import com.example.demo.student.repo.Student;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;

@Configuration
public class RedisConfigs {
	
	@Value("${redis.host}")
	public String host;
	@Value("${redis.port}")
	public String port;
	
	
	@Value("${azure.redis.host}")
	public String hostAz;
	@Value("${azure.redis.port}")
	public int portAz;
	
	@Value("${azure.redis.pass}")
	public String passAz;
	
	
	
	
//	@Bean
//	public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter){
//		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//		container.setConnectionFactory(connectionFactory);
//		container.addMessageListener(listenerAdapter, new PatternTopic("chat"));
//		return container;
//	}
//	
//	@Bean
//	public MessageListenerAdapter listenerAdapter(Reciever reciever){
//		return new MessageListenerAdapter(reciever,"receiveMessage");
//	}
//	
//	@Bean
//	public Reciever reciever(CountDownLatch latch){
//		return new Reciever(latch);
//	}
//	
//	@Bean
//	public CountDownLatch latch(){
//		return new CountDownLatch(1);
//	}
//	
//	@Bean
//	public StringRedisTemplate template(RedisConnectionFactory conn){
//		return new StringRedisTemplate(conn);
//	}
//	
	@Bean
	public JedisConnectionFactory jedisConnFactory(){
		/*JedisConnectionFactory connFactory = new JedisConnectionFactory();
		connFactory.setHostName(this.host);
		connFactory.setPort(Integer.parseInt(this.port));
		return connFactory;*/
		JedisShardInfo jediShard = new JedisShardInfo(this.hostAz, this.portAz, true);
		jediShard.setPassword(this.passAz);
		
		return new JedisConnectionFactory(jediShard);
	}
	
	@Bean
	public RedisTemplate<String, Student> redisTemplate(){
		RedisTemplate<String, Student> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnFactory());
		return template;		
	}	
	
}
