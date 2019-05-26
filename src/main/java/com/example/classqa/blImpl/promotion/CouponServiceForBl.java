package com.example.classqa.blImpl.promotion;
import com.example.classqa.po.Coupon;
import com.example.classqa.vo.ResponseVO;

import java.util.List;

public interface CouponServiceForBl {
    public ResponseVO checkCoupon(int couponId, double fare);
    public ResponseVO giveCoupon(int userid);
    public List<Coupon> getCouponsInDate(int user_id);
}
