package com.intelliviz.resourcemanagement.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intelliviz.resourcemanagement.model.ProductType;
import com.intelliviz.resourcemanagement.service.ProductTypeServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(ProductTypeController.class)
public class ProductTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductTypeServiceImpl productTypeService;

    @Test
    public void listAllProductTypesTest() throws Exception {
        String expectedResponse = "[{id:1, name:Grain, description:test}, {id:2, name:Beans, description:test}, {id:3, name:Sugar, description:test}]";

        when(productTypeService.listAll()).thenReturn(
                new ArrayList<>(
                        Arrays.asList(
                                new ProductType(1L, "Grain", "test"),
                                new ProductType(2L, "Beans", "test"),
                                new ProductType(3L, "Sugar", "test")
                        )));
        RequestBuilder request = MockMvcRequestBuilders
                .get("/producttype")
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

        when(productTypeService.findById(1L)).thenReturn(
               new ProductType(1L, "Grain", "test"));
        RequestBuilder request = MockMvcRequestBuilders
                .get("/producttype/{id}", 1)
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
        when(productTypeService.findById(1L)).thenReturn(null);
        RequestBuilder request = MockMvcRequestBuilders
                .get("/badproducttype/{id}", 1)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andExpect(status().isNotFound()) // test for 404
                .andReturn();
    }

    @Test
    public void doBadId404Test() throws Exception {
        when(productTypeService.findById(1L)).thenReturn(null);
        RequestBuilder request = MockMvcRequestBuilders
                .get("/badproducttype/{id}", 10)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andExpect(status().isNotFound()) // test for 404
                .andReturn();
    }

    @Test
    void postProductTypeTest() throws Exception {
        ProductType mockProductType = new ProductType(1, "Test", "test");
        when(productTypeService.save(Mockito.any(ProductType.class))).thenReturn(mockProductType);

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(mockProductType);

        RequestBuilder request = MockMvcRequestBuilders
                .post("/producttype")
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

        ProductType productType = mapper.readValue(response.getContentAsString(), ProductType.class);
        assertEquals(mockProductType.getId(), productType.getId());
        assertEquals(mockProductType.getName(), productType.getName());
        assertEquals(mockProductType.getDescription(), productType.getDescription());
    }

    @Test
    void deleteProductTypeTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .delete("/producttype/{id}", 1);
        mockMvc.perform(request)
                .andExpect(status().isOk());
    }
}
