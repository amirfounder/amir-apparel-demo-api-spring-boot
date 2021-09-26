package com.amirfounder.amirappareldemoapispringboot.domains.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProductCriteriaRepositoryImpl implements ProductCriteriaRepository {

    private final EntityManager entityManager;
    private final ProductUtils productUtils;

    @Autowired
    public ProductCriteriaRepositoryImpl(EntityManager entityManager, ProductUtils productUtils) {
        this.entityManager = entityManager;
        this.productUtils = productUtils;
    }

    @Override
    public Page<Product> findAllWithFilter(Product product, Pageable pageable) {

        TypedQuery<Product> resultsQuery = buildProductQuery(product, pageable);
        TypedQuery<Long> countQuery = buildProductCountQuery();

        List<Product> products = resultsQuery.getResultList();
        Long count = countQuery.getSingleResult();

        return new PageImpl<Product>(products, pageable, count);
    }

    private TypedQuery<Product> buildProductQuery(Product product, Pageable pageable) {
        CriteriaQuery<Product> criteriaQuery = buildProductCriteriaQuery(product, pageable);
        TypedQuery<Product> query = entityManager.createQuery(criteriaQuery);

        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();

        query.setFirstResult(pageNumber * pageSize);
        query.setMaxResults(pageSize);

        return query;
    }

    private CriteriaQuery<Product> buildProductCriteriaQuery(Product product, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);

        Root<Product> root = criteriaQuery.from(Product.class);

        ArrayList<Predicate> predicates = buildWhereClausePredicates(product, root);
        ArrayList<Order> orders = buildOrderCriteria(pageable, root, criteriaQuery);

        criteriaQuery.select(root);
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));
        criteriaQuery.orderBy(orders.toArray(new Order[]{}));

        return criteriaQuery;
    }

    private ArrayList<Predicate> buildWhereClausePredicates(Product product, Root<Product> root) {
        ArrayList<Predicate> predicates = new ArrayList<>();

        predicates.add(buildWhereClausePredicate(root, "material", product, Product::getMaterial));
        predicates.add(buildWhereClausePredicate(root, "type", product, Product::getType));
        predicates.add(buildWhereClausePredicate(root, "demographic", product, Product::getDemographic));
        predicates.add(buildWhereClausePredicate(root, "color", product, Product::getColor));

        return (ArrayList<Predicate>) predicates.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    private Predicate buildWhereClausePredicate(
            Root<Product> root, String entityColumn, Product product, Function<Product, String> resolver
    ) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        String value = resolver.apply(product);
        return value == null ? null : criteriaBuilder
                .upper(root.get(entityColumn))
                .in(Arrays.asList(value.toUpperCase().split(",")));
    }

    private ArrayList<Order> buildOrderCriteria(
            Pageable pageable, Root<Product> root, CriteriaQuery<Product> criteriaQuery
    ) {
        ArrayList<Order> orders = new ArrayList<>();

        pageable.getSort().stream().forEach(order -> {
            Sort.Direction direction = order.getDirection();
            String property = order.getProperty();
            Order orderCriteria = buildSingleOrderCriteria(root, property, direction);
            if (orderCriteria != null) orders.add(orderCriteria);
        });

        return orders;
    }

    private Order buildSingleOrderCriteria(Root<Product> root, String property, Sort.Direction direction) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        ArrayList<String> validFields = productUtils.getProductFields();
        if (validFields.contains(property)) {
            if (direction.isDescending()) {
                return criteriaBuilder.desc(root.get(property));
            }
            return criteriaBuilder.asc(root.get(property));
        }
        return null;
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
