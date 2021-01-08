package cn.keepting.family.server.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@ConfigurationProperties("yg.rest-config.primary")
public class PrimaryRestTemplateConfig {

    /**
     * Connect timeout, unit is ms
     */
    private int connectTimeout = 1000;

    /**
     * Read timeout, unit is ms
     */
    private int soTimeout = 1000;

    public static final String BEAN_NAME = "primaryTemplate";

    private static final String FACTORY_BEAN_NAME = "primaryTemplateFactroy";

    @Bean(name = BEAN_NAME)
    @Primary
    public RestTemplate restTemplate(@Qualifier(FACTORY_BEAN_NAME) ClientHttpRequestFactory factory) {
        return new RestTemplate(factory);
    }

    @Bean(name = FACTORY_BEAN_NAME)
    @Primary
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(this.connectTimeout);//单位为ms
        factory.setReadTimeout(this.soTimeout);//单位为ms
        return factory;
    }


    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getSoTimeout() {
        return soTimeout;
    }

    public void setSoTimeout(int soTimeout) {
        this.soTimeout = soTimeout;
    }
}
