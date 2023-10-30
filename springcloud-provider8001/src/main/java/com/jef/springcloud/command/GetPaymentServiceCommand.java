package com.jef.springcloud.command;

import com.jef.springcloud.service.PaymentService;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Jef
 * @date 2023/10/30
 */
public class GetPaymentServiceCommand extends HystrixCommand<String> {

    @Autowired
    private PaymentService paymentService;

    protected GetPaymentServiceCommand() {
        super(setter());
    }

    private static Setter setter() {
        //服务分组
        HystrixCommandGroupKey groupKey = HystrixCommandGroupKey.Factory.asKey("payment");
        //服务标识
        HystrixCommandKey commandKey =
                HystrixCommandKey.Factory.asKey("getpayment");
        //线程池名称
        HystrixThreadPoolKey threadPoolKey = HystrixThreadPoolKey.Factory.asKey("payment-pool");
        //线程池配置
        HystrixThreadPoolProperties.Setter threadPoolProperties = HystrixThreadPoolProperties.Setter()
                .withCoreSize(10)
                .withKeepAliveTimeMinutes(5)
                .withMaxQueueSize(Integer.MAX_VALUE).withQueueSizeRejectionThreshold(10000);
        //命令属性配置
        HystrixCommandProperties.Setter commandProperties =
                HystrixCommandProperties.Setter()
                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD);
        return HystrixCommand.Setter
                .withGroupKey(groupKey)
                .andCommandKey(commandKey)
                .andThreadPoolKey(threadPoolKey)
                .andThreadPoolPropertiesDefaults(threadPoolProperties).andCommandPropertiesDefaults(commandProperties);
    }

    @Override
    protected String run() throws Exception {
        return paymentService.getPaymentById(1L).toString();
    }
}