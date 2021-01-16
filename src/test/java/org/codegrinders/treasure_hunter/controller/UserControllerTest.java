package org.codegrinders.treasure_hunter.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.codegrinders.treasure_hunter.TreasureHunterApplication;
import org.codegrinders.treasure_hunter.model.User;
import org.codegrinders.treasure_hunter.repository.UserRepository;
import org.codegrinders.treasure_hunter.service.PlayerService;
import org.codegrinders.treasure_hunter.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TreasureHunterApplication.class)
@WebAppConfiguration
public class UserControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    @Mock
    UserRepository userRepository;

    @Mock
    UserService userService;

    @Mock
    PlayerService playerService;

    private MockMvc mvc;

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    protected <T> T mapFromJson(String json, Class<T> clazz) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    @Test
    public void whenGetUserListThenReturnTrueIfNotNull() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        String uri = "/user/";
        MvcResult mvcResult = mvc.perform(get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        User[] userList = mapFromJson(content, User[].class);
        Assert.assertTrue(userList.length > 0);

    }
    @Test
    public void whenCreateUser() throws Exception {
        User user = new User( "maria@maria.gr", "maria", "111");
        String uri = "/user/";
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        String inputJson = mapToJson(user);
        mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andExpect(status().isCreated());


    }
    @Test
    public void whenUpdateUsersUsername() throws Exception {
        String uri = "/user/";
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        User user = new User("elena@elena.gr", "elena", "111");
        user.setUsername("ELENA");
        String inputJson = mapToJson(user);
        mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        Assert.assertEquals("ELENA",user.getUsername());

    }

    @Test
    public void givenUsersIdWhenFoundThenReturnUsername() throws Exception{
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        String uri = "/user/3";
        MvcResult mvcResult = mvc.perform(get(uri)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        User user = mapFromJson(content, User.class);
        Assert.assertEquals("takis",user.getUsername());
    }


    @Test
    public void deleteUserById() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        String uri = "/user/2";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
    }

    @Test
    public void whenAddedUserThatExistsThenExpectedBadRequest() throws Exception{
        String uri = "/user/";
        User user = new User("pakis@pakis.gr", "mits", "111");
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        String inputJson = mapToJson(user);
        mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andExpect(status().isBadRequest());

    }

    @Test
    public void tryToLogin() throws Exception{
        String uri = "/user/login";
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        MvcResult mvcResult = mvc.perform(get(uri).param("username","pakis").param("password","111")).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        User user = mapFromJson(content, User.class);
        Assert.assertEquals("pakis",user.getUsername());
    }

    @Test
    public void tryToLoginNoExistingUser() throws Exception{
        String uri = "/user/login";
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        MvcResult mvcResult = mvc.perform(get(uri).param("username","test").param("password","111")).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        User user = mapFromJson(content, User.class);
        Assert.assertNull(user.getUsername());
    }

    @Test
    public void whenLoginPostUser() throws Exception {
        User user = new User( "1","pakis",55);
        String uri = "/user/logged";
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        String inputJson = mapToJson(user);
        mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andExpect(status().isOk());
    }

    @Test
    public void tryToGetOnlinePlayersWithNoneLoggedPlayer() throws Exception{
        String uri = "/user/online";
        User user = new User("1","pakis",55);


        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        MvcResult mvcResult = mvc.perform(get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        User[] userList = mapFromJson(content, User[].class);
        Assert.assertTrue(userList.length > 0);
    }




}