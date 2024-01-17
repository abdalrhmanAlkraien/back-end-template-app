package com.marinabits.energietechs.repository.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Abstract specification.
 *
 * @author Azzam Abu Msameh
 * @since 6/25/2023
 */
public abstract class AbstractSpecification<T> implements Specification<T> {

    protected Map<String, String> filters;
    private static final String SORT_FIELD = "sortField";
    private static final String SORT_ORDER = "sortOrder";

    /**
     * to Predicate.
     *
     * @param root            query root
     * @param query           query
     * @param criteriaBuilder criteria builder
     * @return predicate
     */
    @Override
    public Predicate toPredicate(final Root<T> root, final CriteriaQuery<?> query, final CriteriaBuilder criteriaBuilder) {

        String sortField = "";
        if (filters.get(SORT_FIELD) != null && !filters.get(SORT_FIELD).isEmpty()) {
            sortField = filters.remove(SORT_FIELD);
        }

        final String sortDirection;
        if (filters.get(SORT_ORDER) == null || filters.get(SORT_ORDER).isEmpty()) {
            sortDirection = "desc";
        } else {
            sortDirection = filters.remove(SORT_ORDER);
        }

        String finalSortField = sortField;
        Predicate predicate = criteriaBuilder
                .and(getConditionsPredicates(root, criteriaBuilder, filters).toArray(new Predicate[0]));

        if (!sortField.isEmpty()) {
            Expression<String> sortExpression = getSortExpression(root, finalSortField);
            if (sortDirection.equals("asc")) {
                query.orderBy(criteriaBuilder.asc(sortExpression));
            } else {
                query.orderBy(criteriaBuilder.desc(sortExpression));
            }
        }

        return predicate;
    }

    /**
     * Get sort expression.
     *
     * @param root      query root.
     * @param sortField sort field
     * @return expression
     */
    protected Expression<String> getSortExpression(final Root<T> root, final String sortField) {
        return root.get(sortField);
    }

    /**
     * Gets condition Predicates.
     *
     * @param root            query root
     * @param criteriaBuilder {@link CriteriaBuilder} object
     * @param filters         query filters map
     * @return list of predicate
     */
    @SuppressWarnings("PMD.UselessParentheses")
    protected List<Predicate> getConditionsPredicates(final Root<T> root, final CriteriaBuilder criteriaBuilder,
                                                      final Map<String, String> filters) {

        return filters.entrySet()
                .stream()
                .filter(filterEntry -> (filterEntry.getValue() == null || filterEntry.getValue().isEmpty()))
                .map(filterEntry -> criteriaBuilder.like(criteriaBuilder.lower(root.get(filterEntry.getKey())),
                        "%" + filterEntry.getValue().toLowerCase() + "%"))
                .collect(Collectors.toList());
    }
}
