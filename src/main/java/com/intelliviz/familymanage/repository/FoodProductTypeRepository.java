package com.intelliviz.familymanage.repository;

import com.intelliviz.familymanage.model.FoodProduct;
import com.intelliviz.familymanage.model.FoodProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class FoodProductTypeRepository {
    @Autowired
    EntityManager em;

    public FoodProductType findById(Long id) {
        return em.find(FoodProductType.class, id);
    }

    public FoodProductType findByName(String name) {
        Query query = em.createNativeQuery("SELECT * from food_product_type where name = ?", FoodProductType.class);
        query.setParameter(1, name);
        List<FoodProductType> fpt = query.getResultList();
        return (fpt == null || fpt.size() == 0) ? null : fpt.get(0);
    }

    public List<FoodProductType> getAll() {
        Query query = em.createNativeQuery("SELECT * from food_product_type", FoodProductType.class);
        return (List<FoodProductType>) query.getResultList();
    }

    public FoodProductType save(FoodProductType foodProductType) {
        if(foodProductType.getId() == null) {
            em.persist(foodProductType);
        } else {
            // update
            em.merge(foodProductType);
        }
        return foodProductType;
    }

    public void deleteById(Long id) {
        FoodProductType fpt = findById(id);
        em.remove(fpt);
    }
}
