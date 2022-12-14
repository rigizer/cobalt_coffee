package com.ssafy.cobaltcoffee.repository

import android.content.Context
import com.ssafy.cobaltcoffee.dto.LatestOrder
import com.ssafy.cobaltcoffee.dto.Product
import com.ssafy.cobaltcoffee.util.RetrofitUtil
import com.ssafy.cobaltcoffee.util.RetrofitCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "ProductRepository_코발트"
class ProductRepository(context: Context) {
    fun getProductList(callback: RetrofitCallback<List<Product>>)  {
        RetrofitUtil.productService.getProductList().enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                val res = response.body()
                if (response.code() == 200) {
                    if (res != null) {
                        callback.onSuccess(response.code(), res)
                    }
                } else {
                    callback.onFailure(response.code())
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                callback.onError(t)
            }
        })
    }

    fun getNewProductList(callback: RetrofitCallback<List<Product>>)  {
        RetrofitUtil.productService.getNewProductList().enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                val res = response.body()
                if (response.code() == 200) {
                    if (res != null) {
                        callback.onSuccess(response.code(), res)
                    }
                } else {
                    callback.onFailure(response.code())
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                callback.onError(t)
            }
        })
    }

    fun getBestProductList(callback: RetrofitCallback<List<Product>>)  {
        RetrofitUtil.productService.getBestProductList().enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                val res = response.body()
                if (response.code() == 200) {
                    if (res != null) {
                        callback.onSuccess(response.code(), res)
                    }
                } else {
                    callback.onFailure(response.code())
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                callback.onError(t)
            }
        })
    }

    fun getCoffeeProductList(callback: RetrofitCallback<List<Product>>)  {
        RetrofitUtil.productService.getCoffeeProductList().enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                val res = response.body()
                if (response.code() == 200) {
                    if (res != null) {
                        callback.onSuccess(response.code(), res)
                    }
                } else {
                    callback.onFailure(response.code())
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                callback.onError(t)
            }
        })
    }

    fun getTeaProductList(callback: RetrofitCallback<List<Product>>)  {
        RetrofitUtil.productService.getTeaProductList().enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                val res = response.body()
                if (response.code() == 200) {
                    if (res != null) {
                        callback.onSuccess(response.code(), res)
                    }
                } else {
                    callback.onFailure(response.code())
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                callback.onError(t)
            }
        })
    }

    fun getCookieProductList(callback: RetrofitCallback<List<Product>>)  {
        RetrofitUtil.productService.getCookieProductList().enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                val res = response.body()
                if (response.code() == 200) {
                    if (res != null) {
                        callback.onSuccess(response.code(), res)
                    }
                } else {
                    callback.onFailure(response.code())
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                callback.onError(t)
            }
        })
    }

    fun getCartProductList(cartList: List<LatestOrder>, callback: RetrofitCallback<List<LatestOrder>>)  {
        RetrofitUtil.productService.getCartProductList(cartList).enqueue(object : Callback<List<LatestOrder>> {
            override fun onResponse(call: Call<List<LatestOrder>>, response: Response<List<LatestOrder>>) {
                val res = response.body()
                if (response.code() == 200) {
                    if (res != null) {
                        callback.onSuccess(response.code(), res)
                    }
                } else {
                    callback.onFailure(response.code())
                }
            }

            override fun onFailure(call: Call<List<LatestOrder>>, t: Throwable) {
                callback.onError(t)
            }
        })
    }

    fun getProduct(productId: Int, callback: RetrofitCallback<Product>)  {
        RetrofitUtil.productService.getProduct(productId.toString()).enqueue(object: Callback<Product> {
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                val res = response.body()
                if (response.code() == 200) {
                    if (res != null) {
                        callback.onSuccess(response.code(), res)
                    }
                } else {
                    callback.onFailure(response.code())
                }
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                callback.onError(t)
            }
        })
    }

    companion object {
        private var INSTANCE : ProductRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = ProductRepository(context)
            }
        }

        fun get(): ProductRepository {
            return INSTANCE ?:
            throw IllegalStateException("ProductRepository must be initialized")
        }
    }
}