package com.intelliviz.resourcemanagement.repository;

import com.intelliviz.resourcemanagement.model.ProductType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
@Qualifier("ProductTypeJpaRepository")
public class ProductTypeJpaRepositoryImpl implements ProductTypeRepository {

    private static Logger LOGGER = LogManager.getLogger(ProductTypeJpaRepositoryImpl.class);

    @PersistenceContext
    private EntityManager em;

    public ProductTypeJpaRepositoryImpl() {
    }

    @Autowired
    public ProductTypeJpaRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<ProductType> getAll() {
        LOGGER.info("Getting all productypes using JPA!!!!!");
        return em.createQuery("SELECT pt FROM ProductType pt order by pt.name", ProductType.class).getResultList();
    }

    @Override
    public ProductType findByName(String name) {
        TypedQuery<ProductType> query = em.createQuery("SELECT pt FROM ProductType pt WHERE pt.name = :name", ProductType.class);
        query.setParameter("name", name);
        return query.getResultList().stream().findFirst().orElse(null);
    }

    @Override
    public ProductType findById(long id) {
        return em.find(ProductType.class, id);
    }

    @Override
    public ProductType insert(ProductType productType) {
        em.persist(productType);
        return productType;
    }

    @Override
    public Long deleteById(long id) {
        ProductType productType = findById(id);
        if(productType == null) {
            return null;
        }
        em.remove(productType);
        return id;
    }
}
