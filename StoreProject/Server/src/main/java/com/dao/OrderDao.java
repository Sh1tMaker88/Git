package com.dao;

import com.api.dao.IOrderDao;
import com.models.Order;


public class OrderDao extends AbstractDao<Order> implements IOrderDao {

    private static OrderDao instance;

    private OrderDao() {
    }

    public static OrderDao getInstance(){
        if (instance == null) {
            instance = new OrderDao();
        }
        return instance;
    }

    @Override
    public Order update(Order entity) {
        Order order = getById(entity.getId());
        order.setStatus(entity.getStatus());
        order.setBooks(entity.getBooks());
        order.setTotalPrice(entity.totalPrice(entity.getBooks()));
        return order;
    }
}