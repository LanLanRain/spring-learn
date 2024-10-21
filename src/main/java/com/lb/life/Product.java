package com.lb.life;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Product /* implements InitializingBean */ implements DisposableBean {

    public Product() {
        System.out.println("Product.Product");
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("Product.setName");
        this.name = name;
    }

    /**
     * 初始化方法
     *
     * @throws Exception
     */
    /* @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Product.afterPropertiesSet");
    } */

    public void myInit() {
        System.out.println("Product.myInit");
    }


    @Override
    public void destroy() throws Exception {
        System.out.println("Product.destroy");
    }

    public void myDestroy() {
        System.out.println("Product.myDestroy");
    }
}
