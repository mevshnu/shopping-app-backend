package com.example.Shoppingapp.controller;

import com.example.Shoppingapp.dao.Productdao;
import com.example.Shoppingapp.dao.Userdao;
import com.example.Shoppingapp.model.Shop;
import com.example.Shoppingapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class Shopcontroller {
    @Autowired
    private Productdao dao;
    private Userdao daos;

    @CrossOrigin(origins = "*")
    @GetMapping("/view")
    public List<Shop> view() {
        return (List<Shop>) dao.findAll();
    }

    @PostMapping(path = "/addproduct", consumes = "application/json", produces = "application/json")
    public Map<String, String> Addproduct(@RequestBody Shop s) {
        System.out.println(s.getProductname().toString());
        System.out.println(s.getImage().toString());
        System.out.println(s.getCategory().toString());
        System.out.println(s.getDescription().toString());
        System.out.println(s.getPrice());
        dao.save(s);
        HashMap<String, String> map = new HashMap<>();
        map.put("status", "success");
        return map;

    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/search", consumes = "application/json", produces = "application/json")
    public List<Shop> Search(@RequestBody Shop s) {
        String productname = String.valueOf(s.getProductname());
        System.out.println(productname);
        return (List<Shop>) dao.SearchProduct(s.getProductname());
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/userRegistration", consumes = "application/json", produces = "application/json")
    public HashMap<String, String> UserRegistration(@RequestBody User um) {
        System.out.println(um);
        List<User> result = (List<User>) daos.FindUserLogin(um.getEmail());
        System.out.println(result);
        HashMap<String, String> st = new HashMap<>();
        if (result.size() != 0) {
            st.put("status", "success");
            st.put("message", "user already exists");
        } else {
            daos.save(um);
            st.put("status", "success");
            st.put("message", "user added successfully");
        }
        return st;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/addProducts", produces = "application/json", consumes = "application/json")
    public HashMap<String, String> AddProduct(@RequestBody Shop pm) {
        dao.save(pm);
        HashMap<String, String> st = new HashMap<>();
        st.put("status", "success");
        st.put("message", "product added successfully");
        return st;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/fetchProducts")
    public List<Shop> FetchProducts() {
        return (List<Shop>) dao.findAll();
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/userLogin", produces = "application/json", consumes = "application/json")
    public HashMap<String, String> UserLogin(@RequestBody User um) {
        System.out.println(um.getEmail());
        List<User> result = (List<User>) daos.FindUserLogin(um.getEmail());
        HashMap<String, String> st = new HashMap<>();
        if (result.size() == 0) {
            st.put("status", "failed");
            st.put("message", "user doesn't exist");
        } else {
            if (Objects.equals(result.get(0).getPassword(), um.getPassword())) {
                st.put("status", "success");
                st.put("message", "user login success");
            } else {
                st.put("status", "failed");
                st.put("message", "wrong password");
            }
        }
        return st;
    }
}
