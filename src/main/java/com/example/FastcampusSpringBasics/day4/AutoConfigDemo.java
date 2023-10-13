package com.example.FastcampusSpringBasics.day4;

import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

public class AutoConfigDemo {

    @Autowired
    DataSource dataSource;
}
