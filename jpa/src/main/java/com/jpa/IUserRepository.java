package com.jpa;

import com.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface IUserRepository extends JpaRepository<User, Long> {
//    List<User> findByEmailEquals(String email);
//    List<User> findByStartTimeGreaterThan(LocalDateTime date);
//
//    List<User> findByEmailEndingWith(String email);
//    List<User> findByEmailContaining(String email);
//    List<User> findByEmailOrderByUsernameDesc(String email);
//
//    @Query("select a from User a where a.email = ?1")
//    List<User> selectUser(String te, Sort sort);

    @Query(value = " select * from t_user a join t_dept b on a.dept_id = b.id", nativeQuery = true)
    List<User> findS();
}
