package com.example.shosekikun.common

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component

@Component
@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
class PropertyHolder {
    lateinit var username: String
    lateinit var password: String
    lateinit var url: String
    lateinit var driverClassName: String
}
