package com.wjord.dao;

import com.wjord.info.Product;

import java.util.List;

public interface ProductDao {
    void insertProduct(Product product);

    void deleteProductByCode(String productCode);

    void deleteProductByName(String productName);

    void updateProductByCode(String productCode, Product product);

    void updateProductByName(String productName, Product product);

    Product selectProductByCode(String productCode);

    Product selectProductByName(String productName);

    List<Product> selectAllProducts(int page, int pageSize);

    int selectTotalProductCount();

    List<Product> sortProductByPrice(int page, int pageSize);

    List<Product> sortProductByUpdatedTime(int page, int pageSize);
}
