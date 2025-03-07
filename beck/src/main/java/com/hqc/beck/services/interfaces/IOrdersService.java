package com.hqc.beck.services.interfaces;

import java.util.List;

import com.hqc.beck.dto.OrdersDTO;
import com.hqc.beck.request.OrdersRequest;

public interface IOrdersService {

    List<OrdersDTO> findAllOrders() throws Exception;

    List<OrdersDTO> findByUser(Integer id) throws Exception;

    // List<OrdersDTO> searchByTyping(Integer id, Integer idPayCard, Integer
    // idUsers) throws Exception;

    void create(OrdersRequest req) throws Exception;

    void update(OrdersRequest req) throws Exception;
}
