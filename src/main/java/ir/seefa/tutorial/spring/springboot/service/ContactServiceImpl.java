package ir.seefa.tutorial.spring.springboot.service;

import ir.seefa.tutorial.spring.springboot.entity.ContactEntity;
import ir.seefa.tutorial.spring.springboot.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

/**
 * @author Saman Delfani
 * @version 1.0
 * @since 10 Sep 2020 T 00:14:25
 */
// 1. service class for calling Repository method and using @Transactional annotation with different option(Isolation and Propagation levels and rollback condition)
// 2. using Pageable interface for getting pageable contents
@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public void addOrUpdateContact(ContactEntity contact) {
        contactRepository.save(contact);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, noRollbackFor = EntityNotFoundException.class)
    public void delete(ContactEntity contactEntity) {
        contactRepository.delete(contactEntity);
    }

    @Override
    public Optional<ContactEntity> get(int contactId) {
        return contactRepository.findById(contactId);
    }

    @Override
    public List<ContactEntity> getAll() {
        return contactRepository.findAll();
    }

    @Override
    public Page<ContactEntity> getAllWithPagingAndSorting(int page, int count) {
        int numberOfItemPerPage = count >= 5 && count <= 20 ? count : 10;
        Pageable pageable = PageRequest.of(page, numberOfItemPerPage, Sort.by("name", "family").ascending());
        return contactRepository.findAll(pageable);
    }

    @Override
    public List<ContactEntity> findByNameAndFamily(String name, String family) {
        return contactRepository.findAllByNameAndFamily(name, family);
    }

    @Override
    public List<ContactEntity> findByPhone(String phone) {
        return contactRepository.findAllByPhone(phone);
    }
}
