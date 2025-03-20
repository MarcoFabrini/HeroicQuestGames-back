package com.hqc.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hqc.back.model.Carts;
import com.hqc.back.model.DetailsCart;
import com.hqc.back.model.Product;

@Repository
public interface IDetailsCartsRepository extends JpaRepository<DetailsCart, Integer> {

    List<DetailsCart> findByCart(Carts cart);

    // se il gioco è già stato scelto
    List<DetailsCart> findByProduct(Product product);

    boolean existsByCartAndProduct(Carts cart, Product product);

}
