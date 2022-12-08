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
    public HashMap<String, String> UserRegistration(@RequestBody User us) {
        System.out.println(us.getName().toString());
        System.out.println(us.getAddress().toString());
        System.out.println(us.getMobile().toString());
        System.out.println(us.getEmail().toString());
        System.out.println(us.getPassword().toString());
        System.out.println(us.getCpassword().toString());
        daos.save(us);
        HashMap<String, String> map = new HashMap<>();
        map.put("status", "success");
        return map;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/userlogin", produces = "application/json", consumes = "application/json")
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
