package com.imooc.o2o.web.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//此控制器仅用于转发到前端页面
@Controller
@RequestMapping("/frontend")
public class FrontendController {

    @GetMapping("/index")
    private String index() {
        return "frontend/index";
    }

    @GetMapping("/shoplist")
    private String shopList() {
        return "frontend/shoplist";
    }

    @GetMapping("/shopdetail")
    private String shopDetail() {
        return "frontend/shopdetail";
    }

    @GetMapping("/productdetail")
    private String productDetail() {
        return "frontend/productdetail";
    }
}
