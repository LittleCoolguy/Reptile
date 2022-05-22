package com.example.reptile.controller;

import com.example.reptile.entity.Review;
import com.example.reptile.service.GetReview;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @description:
 * @author: XiaoGao
 * @time: 2022/5/22 14:50
 */
@Controller
public class FundController {

    @GetMapping("/get")
    public String getComment(Model model) {
        System.out.println("Asss");
        GetReview getReview = new GetReview();
        String title = getReview.getTitle();
        List<Review> reviews = getReview.getComments();
        model.addAttribute("title", title);
        model.addAttribute("reviews", reviews);
        return "comments";
    }

    @ResponseBody
    @GetMapping("/nihao")
    public String demo() {
        System.out.println("demo");
        return "demodd";
    }
}
