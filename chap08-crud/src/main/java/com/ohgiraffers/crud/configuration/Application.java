package com.ohgiraffers.crud.configuration;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
/*  실행 클래스의 위치가 기본 패키지에서 벗어나게 되면
    ComponentScan의 영역이 달라지므로 명시해줘야 한다. */
@ComponentScan(basePackages = "com.ohgiraffers.crud")
/* annotationClass : Mapper라는 어노테이션을 쓰는 클래스 설정 하는 것 */
@MapperScan(basePackages = "com.ohgiraffers.crud", annotationClass = Mapper.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
