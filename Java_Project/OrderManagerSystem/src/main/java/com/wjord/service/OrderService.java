package com.wjord.service;

import com.wjord.info.Order;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    void insertOrder(String productName, String buyerPhone, int orderAmount, BigDecimal orderPrice);

    void updateOrder(String orderCode, String productName, String buyerPhone, int orderAmount, BigDecimal orderPrice);

    void deleteOrder(String orderCode);

    Order selectOrder(String orderCode);

    List<Order> selectAllOrders(int page, int pageSize);

    int selectTotalOrderCount();

    int selectTotalOrderCountByProductCode(String productCode);

    int selectTotalOrderCountByProductName(String productName);

    int selectTotalOrderCountByBuyerPhone(String buyerPhone);

    List<Order> selectOrdersByProductName(String productName, int page, int pageSize);

    List<Order> selectOrdersByProductCode(String productCode, int page, int pageSize);

    List<Order> selectOrdersByBuyerPhone(String buyerPhone, int page, int pageSize);

    List<Order> selectSortedOrderByPrice(int page, int pageSize);

    List<Order> selectSortedOrderByUpdateTime(int page, int pageSize);
}
