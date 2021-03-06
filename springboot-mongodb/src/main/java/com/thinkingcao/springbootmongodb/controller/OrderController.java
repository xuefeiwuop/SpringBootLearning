package com.thinkingcao.springbootmongodb.controller;

import com.alibaba.fastjson.JSON;
import com.thinkingcao.springbootmongodb.entity.Order;
import com.thinkingcao.springbootmongodb.result.ResponseCode;
import com.thinkingcao.springbootmongodb.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @desc: Api接口
 * @author: cao_wencao
 * @date: 2019-12-05 18:03
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;


    /**
     * @url: http://localhost:8080/api/add
     * @desc: 新增订单
     * @auth: cao_wencao
     * @date: 2020/1/2 0:02
     */
    @PostMapping("/add")
    public ResponseCode addOrder(@RequestBody Order order) {
        Order resultOrder = orderService.addOrder(order);
        log.info("resultOrder : {}", JSON.toJSONString(resultOrder));
        if (null == resultOrder) {
            return ResponseCode.error("新增订单缓存失败");
        }
        return ResponseCode.success("新增订单缓存成功");
    }


    /**
     * @url: http://localhost:8080/api/update
     * @desc: 更新订单
     * @auth: cao_wencao
     * @date: 2020/1/2 22:12
     */
    @PutMapping("/update")
    public ResponseCode updateOrder(@RequestBody Order order) {
        Order newOrder = orderService.updateOrder(order);
        log.info("newOrder : {}", JSON.toJSONString(newOrder));
        return ResponseCode.success("更新订单成功", newOrder);
    }


    /**
     * @url: http://localhost:8080/api/orderId
     * @desc: 通过订单号查询订单
     * @auth: cao_wencao
     * @date: 2020/1/2 22:12
     */
    @GetMapping("/{orderId}")
    public ResponseCode findOrderById(@PathVariable("orderId") String orderId) {
        Order newOrder = orderService.findOrderByOrderId(orderId);
        log.info("newOrder : {}", JSON.toJSONString(newOrder));
        return ResponseCode.success("查询订单成功！", newOrder);
    }

    /**
     * @url: http://localhost:8080/api/orderId
     * @desc: 通过订单号删除订单
     * @auth: cao_wencao
     * @date: 2020/1/2 22:13
     */
    @DeleteMapping("/{orderId}")
    public ResponseCode deleteOrderById(@PathVariable("orderId") String orderId) {
        orderService.deleteOrderByOrderId(orderId);
        log.info("删除订单成功，订单ID为: ｛｝",orderId);
        return ResponseCode.success("删除订单成功");
    }

    /**
     * @url: http://localhost:8080/api/list
     * @desc: 查找所有订单
     * @auth: cao_wencao
     * @date: 2020/1/2 22:14
     */
    @GetMapping("/list")
    public ResponseCode findAllOrder() {
        List<Order>  orderList = orderService.findAllOrder();
        log.info("orderList : {}", JSON.toJSONString(orderList));
        return ResponseCode.success("查询订单成功",orderList);
    }

}
