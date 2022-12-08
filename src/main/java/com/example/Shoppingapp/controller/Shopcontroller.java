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

@RestController
public class Shopcontroller {
    @Autowired
    private Productdao dao;
    private Userdao daos;

    @CrossOrigin(origins="*")
    @GetMapping("/view")
    public List<Shop> view()
    {
        return (List<Shop>) dao.findAll();
    }
    @PostMapping(path = "/addproduct",consumes = "application/json",produces = "application/json")
    public Map<String,String> Addproduct(@RequestBody Shop s)
    {
        System.out.println(s.getProductname().toString());
        System.out.println(s.getImage().toString());
        System.out.println(s.getCategory().toString());
        System.out.println(s.getDescription().toString());
        System.out.println(s.getPrice());
        dao.save(s);
        HashMap<String,String> map =new HashMap<>();
        map.put("status","success");
        return map;

    }
    @CrossOrigin(origins="*")
    @PostMapping(path="/search",consumes = "application/json",produces = "application/json")
    public List<Shop> Search(@RequestBody Shop s)
    {
        String productname = String.valueOf(s.getProductname());
        System.out.println(productname);
        return (List<Shop>) dao.SearchProduct(s.getProductname());
    }
    @CrossOrigin(origins ="*")
    @PostMapping(path="/adduser",consumes = "application/json",produces = "application/json")
    public Map<String,String> adduser(@RequestBody User u)
    {
        System.out.println(u.getName().toString());
        System.out.println(u.getAddress().toString());
        System.out.println(u.getMobile().toString());
        System.out.println(u.getEmail().toString());
        System.out.println(u.getPassword().toString());
        System.out.println(u.getCpassword().toString());
        daos.save(u);
        HashMap<String,String> map= new HashMap<>();
        map.put("status","success");
        return map;


    }


}
