package org.naima.service.diagnostic.config;

import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;


@Profile("kubernetes")
@Configuration
public class KubernetesConfig {
    @Value(value="#{systemEnvironment['REDIS_DB_PORT_6379_TCP_ADDR']}")
    String redisHost;

    @Value("#{systemEnvironment['REDIS_DB_SERVICE_PORT']}")
    Integer redisPort;

    @Bean
    JedisConnectionFactory connectionFactory() {
        JedisConnectionFactory cf =  new JedisConnectionFactory();
        cf.setHostName(redisHost);
        cf.setPort(redisPort);
        cf.afterPropertiesSet();
        return cf;
    }
}
