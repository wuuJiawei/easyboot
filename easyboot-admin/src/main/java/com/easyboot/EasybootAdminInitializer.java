package com.easyboot;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * EasybootAdminInitializer
 *
 * @name: EasybootAdminInitializer
 * @author: 12093
 * @date: 2019/2/3
 * @time: 22:46
 */
public class EasybootAdminInitializer {//extends SpringBootServletInitializer {

    //@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }

}