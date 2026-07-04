package com.synapse.usersservice.specifications;

import com.synapse.usersservice.dtos.rep.UserResponseDTO;
import com.synapse.usersservice.dtos.req.SearchUserRequestDTO;
import com.synapse.usersservice.entities.UserEntity;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {

    public static Specification<UserEntity> withFilters(SearchUserRequestDTO filters) {
        return Specification
                .where(likeIgnoreCase("firstName", filters.firstName()))
                .and(likeIgnoreCase("lastName", filters.lastName()))
                .and(likeIgnoreCase("email", filters.email()))
                .and(likeIgnoreCase("firstDocument", filters.firstDocument()))
                .and(likeIgnoreCase("secondDocument", filters.secondDocument()))
                .and(equalTo("status", filters.status()))
                .and(equalTo("birthDate", filters.birthDate()));
    }

    private static Specification<UserEntity> likeIgnoreCase(String field, String value) {
        return (root, query, cb) -> value == null ? null :
                cb.like(cb.lower(root.get(field)), "%" + value.toLowerCase() + "%");
    }

    private static <T> Specification<UserEntity> equalTo(String field, T value) {
        return (root, query, cb) -> value == null ? null :
                cb.equal(root.get(field), value);
    }
}

