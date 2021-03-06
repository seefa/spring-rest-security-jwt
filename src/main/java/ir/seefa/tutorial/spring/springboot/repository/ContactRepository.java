package ir.seefa.tutorial.spring.springboot.repository;

import ir.seefa.tutorial.spring.springboot.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Saman Delfani
 * @version 1.0
 * @since 14 Sep 2020 T 03:45:31
 */
// 1. define repository class for JPA support and adding custom interface with Spring Data
@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Integer> {

    List<ContactEntity> findAllByNameAndFamily(String name, String family);

    List<ContactEntity> findAllByPhone(String phone);

}
