package com.intelliviz.familymanage.repository;

import com.intelliviz.familymanage.model.FoodProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class FoodProductRepository {
    @Autowired
    EntityManager em;

    public List<FoodProduct> getAllFoodProducts() {
        Query query = em.createNativeQuery("SELECT * from food_product");
        return (List<FoodProduct>) query.getResultList();
    }

    public FoodProduct findById(Long id) {
        return em.find(FoodProduct.class, id);
    }

    public FoodProduct findByName(String name) {
        Query query = em.createNativeQuery("SELECT * from food_product where name = ?", FoodProduct.class);
        query.setParameter(1, name);
        List<FoodProduct> fpt = query.getResultList();
        return (fpt == null || fpt.size() == 0) ? null : fpt.get(0);
    }

    public List<FoodProduct> getAll() {
        Query query = em.createNativeQuery("SELECT * from food_product", FoodProduct.class);
        return (List<FoodProduct>) query.getResultList();
    }

    public FoodProduct save(FoodProduct foodProduct) {
        if(foodProduct.getId() == null) {
            em.persist(foodProduct);
        } else {
            // update
            em.merge(foodProduct);
        }
        return foodProduct;
    }
}
