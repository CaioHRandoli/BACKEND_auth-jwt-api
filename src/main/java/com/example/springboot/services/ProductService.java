package com.example.springboot.services;

import com.example.springboot.dtos.ProductRecordDto;
import com.example.springboot.models.ProductModel;
import com.example.springboot.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductModel saveProduct(ProductRecordDto productRecordDto) {
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel);
        return productRepository.save(productModel);
    }

    public List<ProductModel> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<ProductModel> getProductById(UUID id) {
        return productRepository.findById(id);
    }

    public ProductModel updateProduct(UUID id, ProductRecordDto productRecordDto){
        var productO = productRepository.findById(id);
        if(productO.isPresent()){
            var productModel = productO.get();
            BeanUtils.copyProperties(productRecordDto, productModel);
            return productRepository.save(productModel);
        } else {
            return null;
        }
    }

    public boolean deleteProduct(UUID id){
        var productO = productRepository.findById(id);
        if(productO.isPresent()){
            productRepository.delete(productO.get());
            return true;
        } else {
            return false;
        }
    }
}
