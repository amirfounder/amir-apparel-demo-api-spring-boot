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
    public ProductCriteriaRepositoryImpl(
            EntityManager entityManager,
            ProductUtils productUtils
    ) {
        this.entityManager = entityManager;
        this.productUtils = productUtils;
    }

    @Override
    public Page<Product> findAllWithFilter(
            Product product,
            Pageable pageable
    ) {
        TypedQuery<Product> productTypedQuery = buildProductsQuery(product, pageable);
        TypedQuery<Long> countTypedQuery = buildCountQuery(product);

        List<Product> products = productTypedQuery.getResultList();
        Long count = countTypedQuery.getSingleResult();

        return new PageImpl<>(products, pageable, count);
    }

    private TypedQuery<Product> buildProductsQuery(
            Product product,
            Pageable pageable
    ) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);

        Root<Product> root = criteriaQuery.from(Product.class);

        ArrayList<Predicate> filterCriteria = buildFilterCriteria(product, root);
        ArrayList<Order> orderCriteria = buildOrderCriteria(pageable, root);

        modifyProductCriteriaQuery(criteriaQuery, filterCriteria, orderCriteria, root);

        TypedQuery<Product> query = entityManager.createQuery(criteriaQuery);

        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();

        query.setFirstResult(pageNumber * pageSize);
        query.setMaxResults(pageSize);

        return query;
    }

    private TypedQuery<Long> buildCountQuery(
            Product product
    ) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

        Root<Product> root = criteriaQuery.from(Product.class);

        ArrayList<Predicate> filterCriteria = buildFilterCriteria(product, root);

        modifyCountCriteriaQuery(criteriaQuery, filterCriteria, root);

        return entityManager.createQuery(criteriaQuery);
    }

    private void modifyProductCriteriaQuery(
            CriteriaQuery<Product> criteriaQuery,
            ArrayList<Predicate> filterCriteria,
            ArrayList<Order> orders,
            Root<Product> root
    ) {
        criteriaQuery.select(root);
        criteriaQuery.where(filterCriteria.toArray(new Predicate[]{}));
        criteriaQuery.orderBy(orders.toArray(new Order[]{}));
    }

    private void modifyCountCriteriaQuery(
            CriteriaQuery<Long> criteriaQuery,
            ArrayList<Predicate> predicates,
            Root<Product> root
    ) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        criteriaQuery.select(criteriaBuilder.count(root));
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));
    }

    private ArrayList<Predicate> buildFilterCriteria(
            Product product,
            Root<Product> root
    ) {
        ArrayList<Predicate> predicates = new ArrayList<>();

        predicates.add(buildSingleFilterCriteria(root, "material", product, Product::getMaterial));
        predicates.add(buildSingleFilterCriteria(root, "type", product, Product::getType));
        predicates.add(buildSingleFilterCriteria(root, "demographic", product, Product::getDemographic));
        predicates.add(buildSingleFilterCriteria(root, "color", product, Product::getColor));

        return (ArrayList<Predicate>) predicates
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private Predicate buildSingleFilterCriteria(
            Root<Product> root,
            String property,
            Product product,
            Function<Product, String> resolver
    ) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        String value = resolver.apply(product);
        return value == null ? null : criteriaBuilder
                .upper(root.get(property))
                .in(Arrays.asList(value.toUpperCase().split(",")));
    }

    private ArrayList<Order> buildOrderCriteria(
            Pageable pageable,
            Root<Product> root
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

    private Order buildSingleOrderCriteria(
            Root<Product> root,
            String property,
            Sort.Direction direction
    ) {
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

}
