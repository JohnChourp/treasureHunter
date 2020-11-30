package org.codegrinders.treasure_hunter.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.codegrinders.treasure_hunter.TreasureHunterApplication;
import org.codegrinders.treasure_hunter.model.Puzzle;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.io.IOException;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TreasureHunterApplication.class)
@WebAppConfiguration
public class PuzzleControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;
    private MockMvc mvc;

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    @Test
    public void getPuzzleList() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        String uri = "/puzzle/";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Puzzle[] puzzleList = mapFromJson(content, Puzzle[].class);
        Assert.assertTrue(puzzleList.length > 0);
        Assert.assertEquals(puzzleList.length,6);
    }

    @Test
    public void getPuzzleById() throws Exception{
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        String uri = "/puzzle/5";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Puzzle puzzle = mapFromJson(content, Puzzle.class);
        Assert.assertEquals("miss mary",puzzle.getAnswer());

    }
}
