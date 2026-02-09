package com.teamyamatake.repository.specification;

import com.teamyamatake.repository.entity.Task;
import jakarta.annotation.Nonnull;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class TaskSpecification {

    public static Specification<Task> limitBetween(LocalDateTime since, LocalDateTime until) {
        return since == null && until == null ? null : new Specification<Task>() {
            @Override
            public Predicate toPredicate(@Nonnull Root<Task> root, CriteriaQuery<?> query, @Nonnull CriteriaBuilder cb) {
                return cb.between(root.get("limitDate"), since, until);
            }
        };
    }

    public static Specification<Task> statusIs(Integer status) {
        return status == null ? null : new Specification<Task>() {
            @Override
            public Predicate toPredicate(@Nonnull Root<Task> root, CriteriaQuery<?> query, @Nonnull CriteriaBuilder cb) {
                return cb.equal(root.get("status"), status);
            }
        };
    }

    public static Specification<Task> contentContains(String content) {
        return content.isEmpty() ? null : new Specification<Task>() {
            @Override
            public Predicate toPredicate(@Nonnull Root<Task> root, CriteriaQuery<?> query, @Nonnull CriteriaBuilder cb) {
                return cb.like(root.get("content"), "%" + content + "%");
            }
        };
    }

    public static Specification<Task> contentIs(String content) {
        return content.isEmpty() ? null : new Specification<Task>() {
            @Override
            public Predicate toPredicate(@Nonnull Root<Task> root, CriteriaQuery<?> query, @Nonnull CriteriaBuilder cb) {
                return cb.equal(root.get("content"), content);
            }
        };
    }

    public static Sort oderByLimitDate(boolean ascOrder) {
        return Sort.by(ascOrder ? Sort.Direction.ASC : Sort.Direction.DESC, "limitDate");
    }
}
