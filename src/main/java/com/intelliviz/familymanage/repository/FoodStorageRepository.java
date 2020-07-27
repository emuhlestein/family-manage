package com.intelliviz.familymanage.repository;

import com.intelliviz.familymanage.model.FoodProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class FoodStorageRepository {

    @Autowired
    EntityManager em;

    public List<FoodProduct> getAllFoodProducts() {
        Query query = em.createNativeQuery("SELECT * from food_product");
        return (List<FoodProduct>) query.getResultList();
    }
}
