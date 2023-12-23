package com.wjord;

import com.wjord.config.Config;
import com.wjord.info.Product;
import com.wjord.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.math.BigDecimal;

@Component
@SpringJUnitConfig(classes = Config.class)
public class TestProductService {

    @Autowired
    private Product product;
    @Autowired
    private ProductService productService;

    @Test
    public void testInsertProduct() {
        System.out.println("====================================");
        System.out.println("测试插入商品信息");
        System.out.println("\"反甲\", 1, BigDecimal.valueOf(1840)");
        productService.insertProduct("反甲", 1, BigDecimal.valueOf(1840));
        System.out.println("\"饮血剑\", 6, BigDecimal.valueOf(2405)");
        productService.insertProduct("饮血剑", 6, BigDecimal.valueOf(2405));
        System.out.println("\"帽子\", 2, BigDecimal.valueOf(23455)");
        productService.insertProduct("帽子", 5, BigDecimal.valueOf(3600));
        System.out.println("\"狂徒铠甲\", 3, BigDecimal.valueOf(22445)");
        productService.insertProduct("狂徒铠甲", 1, BigDecimal.valueOf(2750));
    }

    @Test
    public void testDeleteProduct() {
        System.out.println("====================================");
        System.out.println("测试删除商品信息");
        productService.deleteProduct("268498896998");
    }

    @Test
    public void testUpdateProduct() {
        System.out.println("====================================");
        System.out.println("测试更新商品信息");
        productService.updateProduct("680918584993", "反甲", 2, BigDecimal.valueOf(1840));
    }

    @Test
    public void testSelectProduct() {
        System.out.println("====================================");
        System.out.println("测试查询商品信息");
        System.out.println(productService.selectProduct("680918584993"));
    }

    @Test
    public void testSelectAllProducts() {
        System.out.println("====================================");
        System.out.println("测试查询所有商品信息");
        System.out.println(productService.selectAllProducts(1, 10));
    }

    @Test
    public void testSelectTotalProductCount() {
        System.out.println("====================================");
        System.out.println("测试查询商品总数");
        System.out.println(productService.selectTotalProductCount());
    }

    @Test
    public void testSelectSortedProductByPrice() {
        System.out.println("====================================");
        System.out.println("测试查询商品总数");
        System.out.println(productService.selectSortedProductByPrice(1, 10));
    }

    @Test
    public void testSelectSortedProductByUpdateTime() {
        System.out.println("====================================");
        System.out.println("测试查询商品总数");
        System.out.println(productService.selectSortedProductByUpdateTime(1, 10));
    }
}
