package ir.seefa.tutorial.spring.springboot.service;

import ir.seefa.tutorial.spring.springboot.entity.ContactEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * @author Saman Delfani
 * @version 1.0
 * @since 10 Sep 2020 T 00:06:31
 */
public interface ContactService {

    Optional<ContactEntity> get(int contactId);

    List<ContactEntity> getAll();

    Page<ContactEntity> getAllWithPagingAndSorting(int page, int count);

    List<ContactEntity> findByNameAndFamily(String name, String family);

    List<ContactEntity> findByPhone(String phone);

    void addOrUpdateContact(ContactEntity contact);

    void delete(ContactEntity contactEntity);
}
