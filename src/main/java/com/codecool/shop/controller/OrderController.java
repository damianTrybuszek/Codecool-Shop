package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.service.ProductService;
import com.google.gson.Gson;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class OrderController extends HttpServlet {

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    ProductDao productDataStore = ProductDaoMem.getInstance();
    ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
    SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
    ProductService productService = new ProductService(productDataStore, productCategoryDataStore, supplierDataStore);
    OrderDao orderDataStore = OrderDaoMem.getInstance();

    TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
    WebContext webContext = new WebContext(request, response, request.getServletContext());

    String ProductIdJson = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
    Gson gson = new Gson();
    int productId = gson.fromJson(ProductIdJson, int.class);
  }
}
