package com.example.Shoppingapp.dao;

import com.example.Shoppingapp.model.Shop;
import com.example.Shoppingapp.model.User;
import org.springframework.data.repository.CrudRepository;

public interface Userdao extends CrudRepository<Shop,Integer>
{
}
