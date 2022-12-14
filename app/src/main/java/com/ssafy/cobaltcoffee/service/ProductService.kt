package com.ssafy.cobaltcoffee.service

import com.ssafy.cobaltcoffee.dto.LatestOrder
import com.ssafy.cobaltcoffee.dto.Product
import retrofit2.Call
import retrofit2.http.*

interface ProductService {
    // 전체 상품의 목록을 반환한다.
    @GET("/rest/product/")
    fun getProductList(): Call<List<Product>>

    // 신규 상품의 목록을 반환한다.
    @GET("/rest/product/new")
    fun getNewProductList(): Call<List<Product>>

    // 인기 상품의 목록을 반환한다.
    @GET("/rest/product/best")
    fun getBestProductList(): Call<List<Product>>

    // 커피 상품의 목록을 반환한다.
    @GET("/rest/product/coffee")
    fun getCoffeeProductList() : Call<List<Product>>

    // 차 상품의 목록을 반환한다.
    @GET("/rest/product/tea")
    fun getTeaProductList(): Call<List<Product>>

    // 쿠키 상품의 목록을 반환한다.
    @GET("/rest/product/cookie")
    fun getCookieProductList(): Call<List<Product>>

    // 장바구니에 표시할 상품 정보를 반환한다.
    @POST("/rest/product/cart")
    fun getCartProductList(@Body cartList: List<LatestOrder>): Call<List<LatestOrder>>

    // 상품 정보 반환
    @GET("/rest/product/{productId}")
    fun getProduct(@Path("productId") productId: String): Call<Product>

    // 상품 정보 및 comment 반환
    @GET("/rest/product/comment/{productId}")
    fun getProductWithComments(@Path("productId") productId: String): Call<List<Map<String, Any>>>
}