package com.jef.springcloud.service;

import com.jef.springcloud.entities.Payment;

public interface PaymentService {
 // 插入
   int insert(Payment payment);
 // 获取
  Payment getPaymentById(Long id);
}