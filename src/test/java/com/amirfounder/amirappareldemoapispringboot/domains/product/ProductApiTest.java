package com.amirfounder.amirappareldemoapispringboot.domains.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;

import static com.amirfounder.amirappareldemoapispringboot.utils.Constants.PRODUCTS_PATH;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductApiTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private ObjectMapper objectMapper;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void getProducts_Returns200() throws Exception {
        mockMvc.perform(get(PRODUCTS_PATH))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content[*].id").exists());
    }

    @Test
    public void getProductById_Returns200() throws Exception {
        mockMvc.perform(get(PRODUCTS_PATH + "/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id", is(1)));
    }

    @Test
    public void getProductById_GivenInvalidId_Returns404() throws Exception {
        mockMvc.perform(get(PRODUCTS_PATH + "/99999999999999"))
                .andExpect(status().is(404))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getProductsWithFilter_GivenDemographicEqualsMen_Returns200WithMenProducts() throws Exception {
        mockMvc.perform(get(PRODUCTS_PATH + "/filter?demographic=men"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content[*].demographic", everyItem(is("Men"))));
    }

    @Test
    public void getProductsWithFilter_GivenDemographicEqualsMenWomen_Returns200WithMenOrWomenProducts() throws Exception {
        mockMvc.perform(get(PRODUCTS_PATH + "/filter?demographic=men,women"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content[*].demographic", everyItem(anyOf(is("Men"), is("Women")))));
    }

    @Test
    public void getProductsWithFilter_GivenDemographicEqualsMenMaterialEqualsSilk_Returns200WithMenAndSilkProducts() throws Exception {
        mockMvc.perform(get(PRODUCTS_PATH + "/filter?demographic=men&material=silk"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content[*].demographic", everyItem(is("Men"))))
                .andExpect(jsonPath("$.content[*].material", everyItem(is("Silk"))));
    }

    @Test
    public void getProductsWithFilter_GivenValidAttribute_InvalidField_Returns200EmptyArray() throws Exception {
        mockMvc.perform(get(PRODUCTS_PATH + "/filter?demographic=foo"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(0)))
                .andExpect(jsonPath("$.content", not(hasSize(greaterThan(0)))));
    }

    @Test
    public void getProductsWithFilter_GivenInvalidAttribute_Returns200() throws Exception {
        mockMvc.perform(get(PRODUCTS_PATH + "/filter?foo=bar"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content[*]", everyItem(is(anything()))));
    }

    @Test
    public void getProductsWithSort_GivenSortEqualsPriceAscending_Returns200WithSortedPrice() throws Exception {
        String json = mockMvc.perform(get(PRODUCTS_PATH + "/filter?sort=price,asc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        LinkedHashMap<?, ?> pageRepresentation = objectMapper.readValue(json, LinkedHashMap.class);
        Product[] products = objectMapper.convertValue(pageRepresentation.get("content"), Product[].class);

        ArrayList<Product> expected = new ArrayList<>(Arrays.asList(products));
        ArrayList<Product> actual = new ArrayList<>(Arrays.asList(products));

        Comparator<Product> compareByPriceAsc = Comparator.comparing(Product::getPrice);
        expected.sort(compareByPriceAsc);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getProductsWithSort_GivenSortEqualsPriceDescending_Returns200WithSortedPrice() throws Exception {
        String json = mockMvc.perform(get(PRODUCTS_PATH + "/filter?sort=name,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        LinkedHashMap<?, ?> pageRepresentation = objectMapper.readValue(json, LinkedHashMap.class);
        Product[] products = objectMapper.convertValue(pageRepresentation.get("content"), Product[].class);

        ArrayList<Product> expected = new ArrayList<>(Arrays.asList(products));
        ArrayList<Product> actual = new ArrayList<>(Arrays.asList(products));

        Comparator<Product> compareByNameDesc = (o1, o2) -> o2.getName().compareTo(o1.getName());
        expected.sort(compareByNameDesc);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getDistinctAttributes_GivenAttributeEqualsColor_Returns200WithStringArray() throws Exception {
        mockMvc.perform(get(PRODUCTS_PATH + "/attribute/color"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[*]", everyItem(is(any(String.class)))));
    }

    @Test
    public void getDistinctAttributes_GivenInvalidAttribute_Returns400() throws Exception {
        mockMvc.perform(get(PRODUCTS_PATH + "/attribute/foo"))
                .andExpect(status().is(400))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

}
