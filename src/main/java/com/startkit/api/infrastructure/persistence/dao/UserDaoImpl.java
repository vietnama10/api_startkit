package com.startkit.api.infrastructure.persistence.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.startkit.api.application.dto.UserSearchParams;
import com.startkit.api.domain.dao.UserDao;
import com.startkit.api.domain.model.User;
import com.startkit.api.infrastructure.entity.UserEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Service
public class UserDaoImpl implements UserDao {
    private final EntityManager entityManager;

    public UserDaoImpl(EntityManager em) {
        this.entityManager = em;
    }

    @Override
    public List<User> searchUsers(UserSearchParams params) {
        // Add default values if params is null
        if (params == null) {
            params = new UserSearchParams();
        }

        StringBuilder countSql = new StringBuilder("SELECT COUNT(*) FROM users u WHERE 1=1");
        StringBuilder sql = new StringBuilder();
        sql.append("""
            SELECT u.user_id as id, u.name, u.email
            FROM users u
            WHERE 1=1
        """);

        // Dynamically build WHERE clause based on provided parameters
        if (params.getName() != null && !params.getName().isEmpty()) {
            sql.append(" AND u.name LIKE CONCAT('%', :name, '%')");
            countSql.append(" AND u.name LIKE CONCAT('%', :name, '%')");
        }
        
        if (params.getEmail() != null && !params.getEmail().isEmpty()) {
            sql.append(" AND u.email LIKE CONCAT('%', :email, '%')");
            countSql.append(" AND u.email LIKE CONCAT('%', :email, '%')");
        }

        sql.append(" LIMIT :limit OFFSET :offset");

        try {
            // Get total count
            Query countQuery = entityManager.createNativeQuery(countSql.toString());
            if (params.getName() != null && !params.getName().isEmpty()) {
                countQuery.setParameter("name", params.getName());
            }
            if (params.getEmail() != null && !params.getEmail().isEmpty()) {
                countQuery.setParameter("email", params.getEmail());
            }
            Long totalElements = ((Number) countQuery.getSingleResult()).longValue();

            // Get paginated data
            Query query = entityManager.createNativeQuery(sql.toString(), UserEntity.class);
            if (params.getName() != null && !params.getName().isEmpty()) {
                query.setParameter("name", params.getName());
            }
            if (params.getEmail() != null && !params.getEmail().isEmpty()) {
                query.setParameter("email", params.getEmail());
            }
            query.setParameter("limit", params.getSize())
                 .setParameter("offset", (params.getPage() - 1) * params.getSize());

            @SuppressWarnings("unchecked")
            List<UserEntity> users = query.getResultList();
            params.setTotalElements(totalElements);
            params.setTotalPages((long) Math.ceil(totalElements.doubleValue() / params.getSize()));
            
            return users.stream()
                .map(e -> new User(e.getId(), e.getName(), e.getEmail()))
                .toList();
        } catch (Exception e) {
            // Log error and return empty list
            e.printStackTrace();
            return List.of();
        }
    }

}
