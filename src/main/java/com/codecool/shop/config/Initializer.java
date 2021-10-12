package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);
        Supplier samsung = new Supplier("Samsung", "Computers, home appliances and electronics");
        supplierDataStore.add(samsung);
        //
        Supplier dell = new Supplier("Dell", "Computers, home appliances and electronics");
        supplierDataStore.add(dell);
        Supplier hp = new Supplier("HP", "Computers, home appliances and electronics");
        supplierDataStore.add(hp);
        Supplier asus = new Supplier("Asus", "Computers, home appliances and electronics");
        supplierDataStore.add(asus);
        Supplier msi = new Supplier("MSI", "Computers, home appliances and electronics");
        supplierDataStore.add(msi);
        Supplier lg = new Supplier("LG", "Computers, home appliances and electronics");
        supplierDataStore.add(lg);
        Supplier apple = new Supplier("Apple", "Computers, home appliances and electronics");
        supplierDataStore.add(apple);
        Supplier huawei = new Supplier("Huawei", "Computers, home appliances and electronics");
        supplierDataStore.add(huawei);


        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);
        ProductCategory monitor = new ProductCategory("Monitor", "Hardware", "Output device for direct communication between user and computer. The purpose of the monitor is to immediately visualize the results of programs running on the computer.");
        productCategoryDataStore.add(monitor);
        ProductCategory laptop = new ProductCategory("Laptop", "Hardware", "A laptop, laptop computer, or notebook computer is a small, portable personal computer (PC) with a screen and alphanumeric keyboard.");
        productCategoryDataStore.add(laptop);
        ProductCategory smartphone = new ProductCategory("Smartphone", "Hardware", "Smartphone is a portable device that combines mobile telephone and computing functions into one unit.");
        productCategoryDataStore.add(smartphone);
        ProductCategory smartwatch = new ProductCategory("Smartwatch", "Hardware", "A smartwatch is a wearable computer in the form of a watch. Modern smartwatches provide a local touchscreen interface for daily use, while an associated smartphone app provides for management and telemetry");
        productCategoryDataStore.add(smartwatch);
        ProductCategory tv_set = new ProductCategory("Television set", "Hardware", "A television set or television receiver, more commonly called the television or TV, is a device that combines a tuner, display, and loudspeakers, for the purpose of viewing and hearing television broadcasting through satellites or cables, or using it as a computer monitor.");
        productCategoryDataStore.add(tv_set);


        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", new BigDecimal("49.9"), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", new BigDecimal("479"), "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", new BigDecimal("89"), "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        productDataStore.add(new Product("Samsung CF390 27", new BigDecimal("120.9"), "USD", "Ultra curved screen for wide viewing area and image depth. Eye comfort - thanks to flicker free technology and eye saver mode.", monitor, samsung));
        //
        productDataStore.add(new Product("Samsung CF390 27", new BigDecimal("120.9"), "USD", "Ultra curved screen for wide viewing area and image depth. Eye comfort - thanks to flicker free technology and eye saver mode.", monitor, samsung));

    }
}