package com.works.restcontrollers;


import com.works.entities.Orders;
import com.works.entities.Product;
import com.works.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderRestController {
    final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/saveOrder")
    public ResponseEntity save(@RequestBody Orders orders) {
        return orderService.saveOrder(orders);
    } //Burada ürün kaydetme yazdım.

    @GetMapping("/listOrders")
    public ResponseEntity list() {
        return orderService.getOders();
    }
    //Burada tüm kayıtları listeleme servisi yazdım

}
