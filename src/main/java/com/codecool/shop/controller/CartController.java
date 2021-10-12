package com.codecool.shop.controller;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.google.gson.Gson;
import org.json.JSONObject;
import utill.HandlingJSonObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet {
    ProductDao productDataStore = ProductDaoMem.getInstance();
    CartDaoMem cartDaoMem = CartDaoMem.getInstance();
//    ProductId productId = new ProductId();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        Gson gson = new Gson();
        HandlingJSonObject hJson = new HandlingJSonObject();


        JSONObject dataFromReq = hJson.getJsonFromRequest(req);
//        while (it.hasNext()) {
//            String key = it.next();
//            Object o = jObj.get(key);
//            System.out.println(key + " : " + o);
//        }



        System.out.println(dataFromReq.get("productId"));
        int productId = Integer.parseInt(String.valueOf(dataFromReq.get("productId")));
        if (productDataStore.getAll().contains(productDataStore.find(productId))) {
            cartDaoMem.add(productDataStore.find(productId));

            resp.setStatus(200);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            out.write(hJson.makeJsonResponse("Add to cart", "Add to basket"));
            resp.sendRedirect("/");

        } else {
            resp.sendError(404);

        }



        super.doPost(req, resp);
    }

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
