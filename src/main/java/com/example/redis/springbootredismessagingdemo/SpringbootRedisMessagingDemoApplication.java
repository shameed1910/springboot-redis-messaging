package com.example.redis.springbootredismessagingdemo;

import com.example.redis.springbootredismessagingdemo.receiver.Receiver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@SpringBootApplication
public class SpringbootRedisMessagingDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRedisMessagingDemoApplication.class, args);

	}

	@Bean
	RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
											MessageListenerAdapter listenerAdapter){

		RedisMessageListenerContainer container=new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.addMessageListener(listenerAdapter,new PatternTopic("chat"));
		return container;
	}


	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver){
		return new MessageListenerAdapter(receiver,"receiveMessage");
	}

@Bean
	Receiver receiver(){
		return new Receiver();
}

	@Bean
	StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory){
		return new StringRedisTemplate(factory);
	}



}
