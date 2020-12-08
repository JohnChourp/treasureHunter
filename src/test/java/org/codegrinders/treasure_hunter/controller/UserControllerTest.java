package org.codegrinders.treasure_hunter.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.codegrinders.treasure_hunter.TreasureHunterApplication;
import org.codegrinders.treasure_hunter.model.User;
import org.codegrinders.treasure_hunter.repository.UserRepository;
import org.codegrinders.treasure_hunter.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TreasureHunterApplication.class)
@WebAppConfiguration
public class UserControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

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
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
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
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
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


}