package cn.keepting.family.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.StringRedisTemplate;

@Primary
@Configuration
@ConfigurationProperties("redis.primary")
public class PrimaryRedisConfig extends RedisPropertiesExpand {

    @Bean
    @Primary
    public StringRedisTemplate stringRedisTemplate() {
        return buildStringTemplate();
    }

}
