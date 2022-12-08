package com.example.Shoppingapp.dao;

import com.example.Shoppingapp.model.Shop;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Productdao extends CrudRepository<Shop,Integer>
{
    @Query(value="SELECT `id`, `category`, `description`, `image`, `price`, `productname` FROM `shop` WHERE `productname` LIKE %:productname%",nativeQuery = true)
    List<Shop> SearchProduct(@Param("productname")String productname);
}
