package com.easyboot.common;

import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.lang.Console;

import java.io.IOException;
import java.util.Properties;

/**
 * Constants
 *
 * @name: Constants
 * @author: 12093
 * @date: 2019/1/3
 * @time: 9:31
 */
public class Constants {

    public static Properties properties = null;

    public static Properties application = null;

    static {
        try {
            ClassPathResource resource = new ClassPathResource("config.properties");
            properties = new Properties();
            properties.load(resource.getStream());

            ClassPathResource applicationResource = new ClassPathResource("application.properties");
            application = new Properties();
            application.load(applicationResource.getStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Console.log("Properties: {}" + properties);
    }
}