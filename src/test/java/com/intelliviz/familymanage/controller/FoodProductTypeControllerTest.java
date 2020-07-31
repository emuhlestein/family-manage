package com.intelliviz.familymanage.controller;

import com.intelliviz.familymanage.model.FoodProductType;
import com.intelliviz.familymanage.service.FoodProductTypeServiceImpl;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FoodProductTypeController.class)
public class FoodProductTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FoodProductTypeServiceImpl foodProductTypeService;

    @Test
    public void listAllFoodProductsTest() throws Exception {
        String expectedResponse = "[{id:1, name:Grain}, {id:2, name:Beans}, {id:3, name:Sugar}]";

        when(foodProductTypeService.listAll()).thenReturn(
                new ArrayList<FoodProductType>(
                        Arrays.asList(
                                new FoodProductType(1L, "Grain"),
                                new FoodProductType(2L,"Beans"),
                                new FoodProductType(3L,"Sugar")
                                )));
        RequestBuilder request = MockMvcRequestBuilders
                .get("/foodproducttype")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk()) // test for 200
                .andExpect(content().json(expectedResponse))
                .andReturn();

        // This code is not needed
        String actualResponse = result.getResponse().getContentAsString();
        JSONAssert.assertEquals(expectedResponse, actualResponse,false);
    }
}
