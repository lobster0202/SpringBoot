package com.ohgiraffers.chap00autoconfiguration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Configuration
public class SpringConfiguration {

    /* application이라는 이름이 있는 properties는 우리가 경로를 설정하지 않아도 알아서 알아먹고 잘함*/
    @Value("${test.value}")
    public String value;

    @Value("${test.list}")
//    public List<String> list;
    private List<String> list;

    @Value("${test.greeting}")
    private String greeting;

    @Value("${test.array}")
    private Set<String> array;

    @Value("${username}")
    private String username;

    @Bean
    public Object propertyReadTest() {
        System.out.println("value : " + value);

        for (String listValue : list) {
            System.out.println(listValue);
        }
        System.out.println("greeting : " + greeting);

        Iterator<String> iter = array.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }

        System.out.println("username : " + username);


        /* (석현) yml이 가독성도 좋고 편해서 좀 더 많이 씀 */


        return new Object();
    }

}
