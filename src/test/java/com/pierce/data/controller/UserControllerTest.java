package com.pierce.data.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;

/**
 * @Project : data
 * @Package Name : com.pierce.data.controller
 * @Description: TODO
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-06-07 13:41
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void userList() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/user/list"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andReturn();
    }
}