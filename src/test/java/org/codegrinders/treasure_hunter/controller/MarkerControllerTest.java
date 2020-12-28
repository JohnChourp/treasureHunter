package org.codegrinders.treasure_hunter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.codegrinders.treasure_hunter.TreasureHunterApplication;
import org.codegrinders.treasure_hunter.model.Marker;
import org.codegrinders.treasure_hunter.repository.MarkerRepository;
import org.codegrinders.treasure_hunter.repository.PuzzleRepository;
import org.codegrinders.treasure_hunter.service.MarkerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.test.context.SpringBootTest;
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
public class MarkerControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    MarkerRepository markerRepository;

    @Autowired
    PuzzleRepository puzzleRepository;

    @Autowired
    MarkerService markerService;

    private MockMvc mvc;

    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    @Test
    public void whenGetAllMarkersThenCheckMarkersLengthIsPositive() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        String uri = "/marker/";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Marker[] markerList = mapFromJson(content, Marker[].class);
        Assert.assertTrue(markerList.length > 0);
    }

    @Test
    public void WhenGetByIdThenCheckIfMarkerTitleIsCorrect() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        String uri = "/marker/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Marker marker = mapFromJson(content, Marker.class);
        Assert.assertEquals("library", marker.getTitle());
    }

    @Test
    public void whenGetAllDescriptionsThenCheckIfSizeOfListIsPositive() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        String uri = "/marker/allDescriptions";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        Marker[] markerList = mapFromJson(content, Marker[].class);
        Assert.assertTrue(markerList.length > 0);

    }


}