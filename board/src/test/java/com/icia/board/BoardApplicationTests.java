package com.icia.board;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

//DB설정 없이 실행
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@SpringBootTest
@SpringBootApplication
public class BoardApplicationTests {

    public static void main(String[] args) {SpringApplication.run(BoardApplicationTests.class, args);}

}
