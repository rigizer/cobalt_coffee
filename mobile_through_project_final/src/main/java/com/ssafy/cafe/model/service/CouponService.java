package com.ssafy.cafe.model.service;

import java.util.List;

import com.ssafy.cafe.model.dto.Coupon;
import com.ssafy.cafe.model.dto.CouponDetail;
import com.ssafy.cafe.model.dto.CouponType;

public interface CouponService {
    /**
     * 사용자의 쿠폰 목록을 반환한다.
     * @param userId
     */
    List<CouponDetail> getCouponList(String userId);
    
    /**
     * 사용자의 사용 가능한 쿠폰 목록을 반환한다.
     * @param userId
     */
    List<CouponDetail> getCouponListCanUse(String userId);
    
    /**
     * 사용자에게 쿠폰이 있는지 확인한다.
     * @param userId
     * @param couponTypeId
     * @return
     */
    Integer check(String userId, Integer couponTypeId);
    
    /**
     * 사용자에게 쿠폰을 발급한다.
     * @param coupon
     */
    CouponType addCoupon(Coupon coupon);
    
    /**
     * 사용자의 쿠폰을 사용처리한다.
     * @param couponId
     */
    void useCoupon(Integer couponId);
}
