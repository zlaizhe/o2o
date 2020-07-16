package com.imooc.o2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//此Controller仅用于路由
@Controller
@RequestMapping(path = "/shopadmin", method = RequestMethod.GET)
public class ShopAdminController {

    @RequestMapping("/shopoperation")
    public String shopOperation() {
        return "shop/shopoperation";//转发到/WEB-INF/html/shop/shopoperation.html页面
    }

    @RequestMapping("/shoplist")
    public String shopList() {
        return "shop/shoplist";
    }

    @RequestMapping("/shopmanagement")
    public String shopManagement() {
        return "shop/shopmanagement";
    }

    @RequestMapping("/productcategorymanagement")
    public String productCategoryManagement() {
        return "shop/productcategorymanagement";
    }

    @RequestMapping("/productoperation")
    public String productOperation() {
        return "shop/productoperation";
    }

    @RequestMapping("/productmanagement")
    public String productManagement() {
        return "shop/productmanagement";
    }

}
