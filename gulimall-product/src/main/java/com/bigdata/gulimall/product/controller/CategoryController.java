package com.bigdata.gulimall.product.controller;

import com.bigdata.common.utils.R;
import com.bigdata.gulimall.product.entity.CategoryEntity;
import com.bigdata.gulimall.product.service.CategoryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


/**
 * 商品三级分类
 *
 * @author cosmoswong
 * @email cosmoswong@sina.com
 * @date 2020-04-23 21:08:55
 */
@RestController
@Api(tags = "分类")
@RequestMapping("product/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 获取三级分类列表
     */
    @GetMapping("/list/tree")
    public R list() {
        long start = System.currentTimeMillis();
        List<CategoryEntity> page = categoryService.getListTree();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{catId}")
    public R info(@PathVariable("catId") Long catId) {
        CategoryEntity category = categoryService.getById(catId);

        return R.ok().put("category", category);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CategoryEntity category) {
        categoryService.save(category);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CategoryEntity category) {
        categoryService.updateById(category);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] catIds) {
        categoryService.removeByIds(Arrays.asList(catIds));

        return R.ok();
    }

}
