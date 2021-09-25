package com.amirfounder.amirappareldemoapispringboot.domains.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProductCriteriaRepositoryImpl implements ProductCriteriaRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Page<Product> findAllWithFilter(Product product, Pageable pageable) {

        TypedQuery<Product> resultsQuery = buildProductQuery(product, pageable);
        TypedQuery<Long> countQuery = buildProductCountQuery();

        List<Product> products = resultsQuery.getResultList();
        Long count = countQuery.getSingleResult();

        return new PageImpl<Product>(products, pageable, count);
    }

    private TypedQuery<Product> buildProductQuery(Product product, Pageable pageable) {
        CriteriaQuery<Product> criteriaQuery = buildProductCriteriaQuery(product);
        TypedQuery<Product> query = entityManager.createQuery(criteriaQuery);

        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();

        query.setFirstResult(pageNumber * pageSize);
        query.setMaxResults(pageSize);

        return query;
    }

    private CriteriaQuery<Product> buildProductCriteriaQuery(Product product) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);

        ArrayList<Predicate> predicates = buildPredicates(product, root);

        criteriaQuery.select(root);
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        return criteriaQuery;
    }

    private ArrayList<Predicate> buildPredicates(Product product, Root<Product> root) {
        ArrayList<Predicate> predicates = new ArrayList<>();

        predicates.add(buildPredicate(root, "material", product, Product::getMaterial));
        predicates.add(buildPredicate(root, "type", product, Product::getType));
        predicates.add(buildPredicate(root, "demographic", product, Product::getDemographic));
        predicates.add(buildPredicate(root, "color", product, Product::getColor));

        return (ArrayList<Predicate>) predicates.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    private Predicate buildPredicate(Root<Product> root, String entityColumn, Product entityProbe,
            Function<Product, String> resolver
    ) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        String value = resolver.apply(entityProbe);
        return value == null ? null : criteriaBuilder
                .upper(root.get(entityColumn))
                .in(Arrays.asList(value.toUpperCase().split(",")));
    }

    private TypedQuery<Long> buildProductCountQuery() {
        CriteriaQuery<Long> criteriaQuery = buildProductCountCriteriaQuery();
        return entityManager.createQuery(criteriaQuery);
    }

    private CriteriaQuery<Long> buildProductCountCriteriaQuery() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Product> root = criteriaQuery.from(Product.class);
        return criteriaQuery.select(criteriaBuilder.count(root));
    }
}
