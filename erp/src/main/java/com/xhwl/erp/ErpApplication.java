package com.xhwl.erp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

import com.xhwl.erp.util.file.StorageProperties;
import com.xhwl.erp.util.file.StorageService;

//import com.xhwl.erp.tool.Receiver;

@SpringBootApplication
@ServletComponentScan
@EnableConfigurationProperties(StorageProperties.class)
public class ErpApplication {
	
	/* RabbitMQ */
//    public final static String queueName = "spring-boot";
//
//    @Bean
//    Queue queue() {
//        return new Queue(queueName, false);
//    }
//
//    @Bean
//    TopicExchange exchange() {
//        return new TopicExchange("spring-boot-exchange");
//    }
//
//    @Bean
//    Binding binding(Queue queue, TopicExchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).with(queueName);
//    }
//
//    @Bean
//    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
//            MessageListenerAdapter listenerAdapter) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setQueueNames(queueName);
//        container.setMessageListener(listenerAdapter);
//        return container;
//    }
//
//    @Bean
//    MessageListenerAdapter listenerAdapter(Receiver receiver) {
//        return new MessageListenerAdapter(receiver, "receiveMessage");
//    }

    /* RabbitMQ end */
    
	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(ErpApplication.class, args);
	}

	@Bean
    CommandLineRunner init(StorageService storageService){
        return (args) ->{
            storageService.deleteAll();
            storageService.init();
        };
    }
}
