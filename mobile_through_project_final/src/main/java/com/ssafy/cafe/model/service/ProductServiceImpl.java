package com.ssafy.cafe.model.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ssafy.cafe.model.dao.ProductDao;
import com.ssafy.cafe.model.dto.LatestOrder;
import com.ssafy.cafe.model.dto.Product;

/**
 * @author itsmeyjc
 * @since 2021. 6. 23.
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao pDao;

    @Override
//    @Cacheable(value="getProductList")
    public List<Product> getProductList() {
        return pDao.selectAll();
    }
    
    @Override
//    @Cacheable(value="getNewProductList")
    public List<Product> getNewProductList() {
        return pDao.selectNewProducts();
    }

    @Override
//    @Cacheable(value="getBestProductList")
    public List<Product> getBestProductList() {
        return pDao.selectBestProducts();
    }

    @Override
//    @Cacheable(value="getCoffeeProductList")
    public List<Product> getCoffeeProductList() {
        return pDao.selectCoffeeProducts();
    }

    @Override
//    @Cacheable(value="getTeaProductList")
    public List<Product> getTeaProductList() {
        return pDao.selectTeaProducts();
    }

    @Override
//    @Cacheable(value="getCookieProductList")
    public List<Product> getCookieProductList() {
        return pDao.selectCookieProducts();
    }
    
    @Override
    public List<LatestOrder> getCartProductList(List<LatestOrder> cartList) {
        List<LatestOrder> returnList = new ArrayList<>();
        for (LatestOrder latestOrder: cartList) {
            Product product = pDao.selectProduct(latestOrder.getOrderId());
            returnList.add(new LatestOrder(
                product.getImg(), 
                latestOrder.getOrderCnt(), 
                "", 
                latestOrder.getOrderId(), 
                product.getName(), 
                new Date(), 
                'N', 
                product.getPrice(), 
                product.getType(), 
                product.getPrice() * latestOrder.getOrderCnt(),
                latestOrder.getProductId()
            ));
        }
        return returnList;
    }
    
    @Override
    public Product selectProduct(Integer productId) {
        return pDao.selectProduct(productId);
    }

    @Override
    public List<Map<String, Object>> selectWithComment(Integer productId) {
        return pDao.selectWithComment(productId);
    }
}
