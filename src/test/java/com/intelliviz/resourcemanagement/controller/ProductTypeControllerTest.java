package com.intelliviz.resourcemanagement.controller;

import com.intelliviz.resourcemanagement.model.ProductType;
import com.intelliviz.resourcemanagement.service.ProductTypeServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
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
                new ArrayList<ProductType>(
                        Arrays.asList(
                                new ProductType(1L, "Grain", "test"),
                                new ProductType(2L,"Beans", "test"),
                                new ProductType(3L,"Sugar", "test")
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

        when(productTypeService.findById(1l)).thenReturn(
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
        when(productTypeService.findById(1l)).thenReturn(null);
        RequestBuilder request = MockMvcRequestBuilders
                .get("/badproducttype/{id}", 1)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andExpect(status().isNotFound()) // test for 404
                .andReturn();
    }

    @Test
    public void doBadId404Test() throws Exception {
        when(productTypeService.findById(1l)).thenReturn(null);
        RequestBuilder request = MockMvcRequestBuilders
                .get("/badproducttype/{id}", 10)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andExpect(status().isNotFound()) // test for 404
                .andReturn();
    }
}
