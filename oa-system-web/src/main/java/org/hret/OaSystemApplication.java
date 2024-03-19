package org.hret;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Author:HRET Milky Way
 * Date:2024/3/18
 * version:1.0
 * @author HRET
 * OA系统启动类
 */
@SpringBootApplication
@MapperScan("org.hret.mapper")
public class OaSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(OaSystemApplication.class, args);
    }

}
