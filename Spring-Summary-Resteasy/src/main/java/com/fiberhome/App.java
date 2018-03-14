package com.fiberhome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Description:
 * @author sjZhang
 * @date 2018年3月13日下午3:47:12
 */
@SpringBootApplication
@ComponentScan(basePackages={"com.fiberhome"})
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
