package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/", "/category/", "/supplier/", "/search/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        CartDaoMem cartDaoMem = CartDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDao = SupplierDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore, productCategoryDataStore, supplierDao);
        String element = req.getRequestURI();
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("categories", productService.getAllProductCategory());
        context.setVariable("suppliers", productService.getAllSuppliers());
        context.setVariable("cartQuantity", cartDaoMem.countProduct());

        int categoryId = 0;
        int supplierId = 0;

        if (element.contains("/category/")) {
            categoryId = Integer.parseInt(element.replaceAll("/category/", ""));
            context.setVariable("products", productService.getProductsForCategory(categoryId));
        } else if (element.contains("/supplier/")) {
            supplierId = Integer.parseInt(element.replaceAll("/supplier/", ""));
            context.setVariable("products", productService.getProductsForSupplier(supplierId));
        } else {
            context.setVariable("products", productService.getAllProducts());
        }
        context.setVariable("highlightedCategory", categoryId);
        context.setVariable("highlightedSupplier", supplierId);


        // // Alternative setting of the template context
        // Map<String, Object> params = new HashMap<>();
        // params.put("category", productCategoryDataStore.find(1));
        // params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        // context.setVariables(params);
        engine.process("product/index.html", context, resp.getWriter());
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDao = SupplierDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore, productCategoryDataStore, supplierDao);
        String element = req.getRequestURI();
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        if (element.contains("/search/")) {
            String phrase = req.getParameter("phrase");
            List<Product> searchingProducts = new ArrayList<>();
            if (!phrase.equals("")) {
                searchingProducts = productDataStore.search(phrase);
            }
            if (searchingProducts.size() > 0) {
                context.setVariable("products", (searchingProducts));
                context.setVariable("searchedPhrase", phrase);
            } else {
                resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/"));
            }

        }
        engine.process("product/index.html", context, resp.getWriter());

    }
}
