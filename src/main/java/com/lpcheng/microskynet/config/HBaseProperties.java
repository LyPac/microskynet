package com.lpcheng.microskynet.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@Data
@ConfigurationProperties(prefix = "spring.hbase")
public class HBaseProperties {

    private Map<String, String> config;
}
