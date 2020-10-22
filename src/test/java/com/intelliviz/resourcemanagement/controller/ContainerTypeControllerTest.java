package com.intelliviz.resourcemanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intelliviz.resourcemanagement.model.ContainerType;
import com.intelliviz.resourcemanagement.service.ContainerTypeServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ContainerTypeController.class)
public class ContainerTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContainerTypeServiceImpl containerTypeService;

    @Test
    public void listAllProductTypesTest() throws Exception {
        String expectedResponse = "[{id:1, name:Grain, description:test}, {id:2, name:Beans, description:test}, {id:3, name:Sugar, description:test}]";

        when(containerTypeService.listAll()).thenReturn(
                new ArrayList<>(
                        Arrays.asList(
                                new ContainerType(1L, "Grain", "test"),
                                new ContainerType(2L, "Beans", "test"),
                                new ContainerType(3L, "Sugar", "test")
                        )));
        RequestBuilder request = MockMvcRequestBuilders
                .get("/containertype")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk()) // test for 200
                .andExpect(content().json(expectedResponse))
                .andReturn();

        // This code is not needed
        String actualResponse = result.getResponse().getContentAsString();
        JSONAssert.assertEquals(expectedResponse, actualResponse, true);
    }


    @Test
    public void getProductTypeByIdTest() throws Exception {
        String expectedResponse = "{id:1, name:Grain, description:test}";

        when(containerTypeService.findById(1L)).thenReturn(
               new ContainerType(1L, "Grain", "test"));
        RequestBuilder request = MockMvcRequestBuilders
                .get("/containertype/{id}", 1)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk()) // test for 200
                .andExpect(content().json(expectedResponse))
                .andReturn();

        // This code is not needed
        String actualResponse = result.getResponse().getContentAsString();
        JSONAssert.assertEquals(expectedResponse, actualResponse, true);
    }

    @Test
    public void doBadUrl404Test() throws Exception {
        when(containerTypeService.findById(1L)).thenReturn(null);
        RequestBuilder request = MockMvcRequestBuilders
                .get("/badcontainertype/{id}", 1)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andExpect(status().isNotFound()) // test for 404
                .andReturn();
    }

    @Test
    public void doBadId404Test() throws Exception {
        when(containerTypeService.findById(1L)).thenReturn(null);
        RequestBuilder request = MockMvcRequestBuilders
                .get("/badcontainertype/{id}", 10)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andExpect(status().isNotFound()) // test for 404
                .andReturn();
    }

    @Test
    void postProductTypeTest() throws Exception {
        ContainerType mockContainerType = new ContainerType(1, "Test", "test");
        when(containerTypeService.save(Mockito.any(ContainerType.class))).thenReturn(mockContainerType);

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(mockContainerType);

        RequestBuilder request = MockMvcRequestBuilders
                .post("/containertype")
                .accept(MediaType.APPLICATION_JSON).content(jsonInString)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(jsonPath("$.id", Matchers.equalTo(1)))
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        String contentString = response.getContentAsString();
        assertNotNull(contentString);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(contentString, jsonInString);

        ContainerType containerType = mapper.readValue(response.getContentAsString(), ContainerType.class);
        assertEquals(mockContainerType.getId(), containerType.getId());
        assertEquals(mockContainerType.getName(), containerType.getName());
        assertEquals(mockContainerType.getDescription(), containerType.getDescription());
    }

    @Test
    void deleteProductTypeTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .delete("/containertype/{id}", 1);
        mockMvc.perform(request)
                .andExpect(status().isOk());
    }
}
