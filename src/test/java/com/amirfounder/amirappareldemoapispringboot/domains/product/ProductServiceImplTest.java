package com.amirfounder.amirappareldemoapispringboot.domains.product;

import com.amirfounder.amirappareldemoapispringboot.data.ProductFactory;
import com.amirfounder.amirappareldemoapispringboot.exceptions.ResourceNotFound;
import com.amirfounder.amirappareldemoapispringboot.exceptions.ServiceUnavailable;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


public class ProductServiceImplTest {


    @InjectMocks
    ProductServiceImpl productService;

    @Mock
    ProductRepository productRepository;

    ProductFactory productFactory;
    Product product;
    PageRequest pageRequest;
    DataAccessException dataAccessException;
    List<Product> productList;
    String[] uniqueAttributes = {"Test1", "Test2", "Test3"};
    ArrayList<String> uniqueAttributesList;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        productFactory = new ProductFactory();
        product = new Product();
        pageRequest = PageRequest.of(0, 20);
        dataAccessException = new DataAccessException("Mocked Data Access Exception") {};
        productList = productFactory.generateProducts(10);
        uniqueAttributesList = new ArrayList<>(Arrays.asList(uniqueAttributes));
    }

    @Test
    public void getProducts_GivenDataAccessExceptionThrown_ThrowsServiceUnavailable() {
        when(productRepository.findAll(any(), any(Pageable.class))).thenThrow(dataAccessException);
        assertThrows(ServiceUnavailable.class, () -> productService.getProducts(product, pageRequest));
    }

    @Test
    public void getProducts_GivenNoExceptions_ReturnsArrayOfProducts() {
        when(productRepository.findAll(any(), any(Pageable.class))).thenReturn((new PageImpl<>(productList)));
        Page<Product> expected = new PageImpl<>(productList);
        Page<Product> actual = productService.getProducts(product, pageRequest);
        assertEquals(expected, actual);
    }

    @Test
    public void getProductsWithFilter_GivenDataAccessExceptionThrown_ThrowsServiceUnavailable() {
        when(productRepository.findAllWithFilter(any(), any())).thenThrow(dataAccessException);
        assertThrows(ServiceUnavailable.class, () -> productService.getProductsWithFilter(product, pageRequest));
    }

    @Test
    public void getProductsWithFilter_GivenNoExceptions_ReturnProducts() {
        when(productRepository.findAllWithFilter(any(), any())).thenReturn(new PageImpl<>(productList));
        Page<Product> expected = new PageImpl<>(productList);
        Page<Product> actual = productService.getProductsWithFilter(product, pageRequest);
        assertEquals(expected, actual);
    }

    @Test
    public void getProductById_GivenDataAccessExceptionThrown_ThrowServiceUnavailable() {
        when(productRepository.findById(any())).thenThrow(dataAccessException);
        assertThrows(ServiceUnavailable.class, () -> productService.getProductById(1L));
    }

    @Test
    public void getProductById_GivenNullReturned_ThrowResourceNotFound() {
        when(productRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFound.class, () -> productService.getProductById(1L));
    }

    @Test
    public void getProductById_GivenNoExceptions_ReturnProduct() {
        when(productRepository.findById(any())).thenReturn(Optional.ofNullable(product));
        Product expected = product;
        Product actual = productService.getProductById(1L);
        assertEquals(expected, actual);
    }

    @Test
    public void getDistinctAttributes_GivenDataAccessExceptionThrown_ThrowServiceUnavailable() {
        when(productRepository.findDistinctAttributes(any())).thenThrow(dataAccessException);
        assertThrows(ServiceUnavailable.class, () -> productService.getDistinctAttributes(""));
    }

    @Test
    public void getDistinctAttributes_GivenNoExceptions_ReturnDistinctAttributes() {
        when(productRepository.findDistinctAttributes(any())).thenReturn(uniqueAttributesList);
        ArrayList<String> expected = uniqueAttributesList;
        ArrayList<String> actual = productService.getDistinctAttributes("");
        assertEquals(expected, actual);
    }

}
