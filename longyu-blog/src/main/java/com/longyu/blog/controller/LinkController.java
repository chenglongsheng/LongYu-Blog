package com.longyu.blog.controller;

import com.longyu.common.domain.R;
import com.longyu.common.domain.vo.LinkListVo;
import com.longyu.common.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("link")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @GetMapping("/getAllLink")
    public R<List<LinkListVo>> getAllLink() {
        return R.ok(linkService.getAllLink());
    }

}
