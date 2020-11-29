package org.codegrinders.treasure_hunter.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.codegrinders.treasure_hunter.TreasureHunterApplication;
import org.codegrinders.treasure_hunter.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TreasureHunterApplication.class)
@WebAppConfiguration
public class UserControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;
    private MockMvc mvc;


    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    @Test
    public void getUserList() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        String uri = "/user/";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();


        Assert.assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        User[] userList = mapFromJson(content, User[].class);
        Assert.assertTrue(userList.length > 0);
    }
    @Test
    public void createUser() throws Exception {
        String uri = "/user/";
        User user = new User("7", "pakis@pakis.gr", "elena", "111", 0);
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        String inputJson = mapToJson(user);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);


    }
    @Test
    public void updateUser() throws Exception {
        String uri = "/user/";
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        User user = new User("4", "elena@elena.gr", "elena", "111", 0);
        user.setUsername("ELENA");

        String inputJson = mapToJson(user);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
        Assert.assertEquals("ELENA",user.getUsername());
    }

    @Test
    public void getUserById() throws Exception{
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        String uri = "/user/2";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        User user = mapFromJson(content, User.class);
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
        Assert.assertEquals("sakis",user.getUsername());

    }

}
