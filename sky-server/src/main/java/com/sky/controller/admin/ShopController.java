package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController("adminShopController")
@RequestMapping("/admin/shop")
@Api(tags = "店铺相关接口")
@Slf4j
public class ShopController {

    public static final String Key = "SHOP_STATUS";

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 设置店铺的营业状态
     * @return
     */
    @ApiOperation("设置店铺的营业状态")
    @PutMapping("/{status}")
    public Result setStatus(@PathVariable Integer status){
        log.info("设置店铺的营业状态为:{}",status == 1 ? "营业中" : "打烊中");
        redisTemplate.opsForValue().set(Key,status);
        return Result.success();
    }

    /**
     * 获取店铺的营业状态
     * @return
     */
    @ApiOperation("获取店铺的营业状态")
    @GetMapping("/status")
    public Result<Integer> getStatus(){
            Integer status = (Integer)redisTemplate.opsForValue().get(Key);
        log.info("获取店铺的营业状态为:{}",status == 1 ? "营业中" : "打烊中");
        return Result.success(status);
    }
}
