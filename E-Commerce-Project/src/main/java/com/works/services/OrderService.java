package com.works.services;

import com.works.entities.Orders;
import com.works.entities.Product;
import com.works.repositories.OrdersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class OrderService {
    final OrdersRepository ordersRepository;

    public OrderService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public ResponseEntity saveOrder(Orders orders) {         //Burada siparişleri kaydetme yazdım.
        Map<String, Object> hm = new LinkedHashMap<>();
        ordersRepository.save(orders);
        hm.put("status", true);
        hm.put("result", orders);
        return new ResponseEntity(hm, HttpStatus.OK);
    }
    public ResponseEntity getOders() {                     //Burada tüm kayıtları listeleme servisi yazdım
        Map<String, Object> hm = new HashMap<>();
        hm.put("Orders", ordersRepository.findAll());
        return new ResponseEntity(hm, HttpStatus.OK);
    }

}
