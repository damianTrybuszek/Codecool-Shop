package com.codecool.shop.controller;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductQuantity;
import com.codecool.shop.utill.HandlingJSonObject;
import com.codecool.shop.utill.Message;
import com.google.gson.Gson;
import org.json.JSONObject;
import com.codecool.shop.utill.HandlingJSonObject;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@WebServlet(urlPatterns = {"/cart", "/cart-items"})
public class CartController extends HttpServlet {
    ProductDao productDataStore = ProductDaoMem.getInstance();
    CartDaoMem cartDaoMem = CartDaoMem.getInstance();
    Gson gson = new Gson();




    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        RequestDispatcher dispatcher = req.getRequestDispatcher("webapp/templates/product/index.html");
        PrintWriter out = resp.getWriter();
        String element = req.getRequestURI();

//        String cartString = gson.toJson(cartDaoMem.getCart());
//
//        System.out.println(cartString);

        if (element.contains("/cart-items")) {
            if (!cartDaoMem.getCart().isEmpty()) {
                List<ProductQuantity> productToJson = cartDaoMem.getQuantity();

//                System.out.println(productInCart);
//                System.out.println(quantityProd)
                System.out.println(productToJson);

                String cartString = gson.toJson(cartDaoMem.getQuantity());

                System.out.println(cartString);
//                JSONObject jsonProdInCart = new JSONObject(productInCart);
//                JSONObject jsonQuantityProd = new JSONObject(quantityProd);

                resp.setStatus(200);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                out.print(cartString);
//                dispatcher.forward(req, resp);
            }


        } else if (element.contains("/cart")) {
            int numberProd = cartDaoMem.countProduct();
            resp.setStatus(200);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            out.print(numberProd);

        }
//        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("webapp/templates/product/index.html");
        PrintWriter out = resp.getWriter();

        HandlingJSonObject hJson = new HandlingJSonObject();
        JSONObject dataFromReq = hJson.getJsonFromRequest(req);

        int productId = Integer.parseInt(String.valueOf(dataFromReq.get("productId")));
        if (productDataStore.getAll().contains(productDataStore.find(productId))) {
            cartDaoMem.add(productDataStore.find(productId));

//            String cartString = gson.toJson(cartDaoMem.getQuantity());
//
//            System.out.println(cartString);

            System.out.println(cartDaoMem.getCart());
            int numberProd = cartDaoMem.countProduct();
            System.out.println(numberProd);
            resp.setStatus(200);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            out.print(numberProd);

        }
//        resp.sendRedirect("/");
    }

    // Function can read request from Http
    static String extractPostRequestBody(HttpServletRequest request) {
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            Scanner s = null;
            try {
                s = new Scanner(request.getInputStream(), "UTF-8").useDelimiter("\\A");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return s.hasNext() ? s.next() : "";
        }
        return "";
    }
}
