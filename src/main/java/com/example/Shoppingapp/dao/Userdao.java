package com.example.Shoppingapp.dao;

import com.example.Shoppingapp.model.Shop;
import com.example.Shoppingapp.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Userdao extends CrudRepository<Shop,Integer>
{
    @Query(value="SELECT `id`, `address`, `cpassword`, `email`, `mobile`, `password` FROM `user` WHERE `email`= :email",nativeQuery = true)
    List<User> FindUserLogin(@Param("email")String email);
}
