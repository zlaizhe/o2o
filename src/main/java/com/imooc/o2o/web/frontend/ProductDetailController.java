package com.imooc.o2o.web.frontend;

import com.imooc.o2o.dto.ModelMap;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.service.ProductCategoryService;
import com.imooc.o2o.service.ProductService;
import com.imooc.o2o.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//商品详细页面的控制器
@RestController
@RequestMapping("/frontend")
public class ProductDetailController {

    @Autowired
    private ProductService productService;

    //根据Id获取商品详情，包括商品的所有详情图
    @GetMapping("/listproductdetailpageinfo")
    private ModelMap listProductDetailPageInfo(Long productId) {
        ModelMap modelMap = ModelMap.newInstance();
        if (productId == null) {
            return modelMap.putErrMsg(false, "emtpy productId");
        }
        Product product = productService.getProductById(productId);
        modelMap.put("product", product);
        return modelMap.putSuccess(true);
    }
}
