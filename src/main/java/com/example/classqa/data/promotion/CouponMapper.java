package com.example.classqa.data.promotion;

import com.example.classqa.po.Coupon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by liying on 2019/4/17.
 */
@Mapper
public interface CouponMapper {

    int insertCoupon(Coupon coupon);

    List<Coupon> selectCouponByUser(int userId);

    Coupon selectById(int id);

    List<Coupon>selectCouponInDate(Timestamp date);

    void insertCouponUser(@Param("couponId") int couponId,@Param("userId")int userId);

    void deleteCouponUser(@Param("couponId") int couponId,@Param("userId")int userId);

    List<Coupon> selectCouponByUserAndAmount(@Param("userId") int userId,@Param("amount") double amount);
}
