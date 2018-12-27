package com.ola.qh.config.json;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@Configuration
@ConditionalOnClass({FastJsonHttpMessageConverter.class}) //1
@ConditionalOnProperty(//2
        name = {"spring.http.converters.preferred-json-mapper"},
        havingValue = "fastjson", 
        matchIfMissing = true
)
 class FastJson2HttpMessageConverterConfiguration{
    protected FastJson2HttpMessageConverterConfiguration() {
    }

    @Bean
    @ConditionalOnMissingBean({FastJsonHttpMessageConverter.class})//3
    public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();

        FastJsonConfig fastJsonConfig = new FastJsonConfig();//4

        converter.setFastJsonConfig(fastJsonConfig);

        return converter;
    }
}
