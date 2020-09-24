package ir.seefa.tutorial.spring.springboot.repository;

import ir.seefa.tutorial.spring.springboot.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Saman Delfani
 * @version 1.0
 * @since 24 Sep 2020 T 05:45:57
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByUsername(String username);
}
