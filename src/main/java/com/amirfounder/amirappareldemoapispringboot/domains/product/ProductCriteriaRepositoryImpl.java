package com.amirfounder.amirappareldemoapispringboot.domains.product;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProductCriteriaRepositoryImpl implements ProductCriteriaRepository {

    private final EntityManager entityManager;

    @Autowired
    public ProductCriteriaRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Product> findAllWithFilter(Product product) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);

        ArrayList<Predicate> predicates = buildPredicates(product, criteriaBuilder, root);

        criteriaQuery.select(root);
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        TypedQuery<Product> typedQuery = entityManager.createQuery(criteriaQuery);

        return typedQuery.getResultList();
    }

    private ArrayList<Predicate> buildPredicates(
            Product product,
            CriteriaBuilder criteriaBuilder,
            Root<Product> root
    ) {
        ArrayList<Predicate> predicates = new ArrayList<>();

        predicates.add(buildPredicate(root, criteriaBuilder, "material", product, Product::getMaterial));
        predicates.add(buildPredicate(root, criteriaBuilder, "type", product, Product::getType));
        predicates.add(buildPredicate(root, criteriaBuilder, "demographic", product, Product::getDemographic));
        predicates.add(buildPredicate(root, criteriaBuilder, "color", product, Product::getColor));

        return (ArrayList<Predicate>) predicates.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    private Predicate buildPredicate(
            Root<Product> root,
            CriteriaBuilder criteriaBuilder,
            String entityColumn,
            Product entityProbe,
            Function<Product, String> resolver
    ) {
        String value = resolver.apply(entityProbe);
        return value == null ? null : criteriaBuilder
                .upper(root.get(entityColumn))
                .in(Arrays.asList(value.toUpperCase().split(",")));
    }

}
