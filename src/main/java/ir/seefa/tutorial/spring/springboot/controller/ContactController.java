package ir.seefa.tutorial.spring.springboot.controller;

import io.swagger.annotations.*;
import ir.seefa.tutorial.spring.springboot.entity.ContactEntity;
import ir.seefa.tutorial.spring.springboot.exception.ResourceNotFoundException;
import ir.seefa.tutorial.spring.springboot.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Saman Delfani
 * @version 1.0
 * @since 10 Sep 2020 T 13:24:21
 */
// 1. Controller class
@RestController
// 2. Controller mapping path
@RequestMapping("/api")
// 3. API Contract description with @Api annotation
@Api(value = "Contact API Management", protocols = "JSON")
public class ContactController {

    @Autowired
    ContactService contactService;

    // 4. API operation description and return class type
    @ApiOperation(value = "get list of user contacts", response = List.class)
    // 5. explain API response codes and description with @ApiResponses and @ApiResponse annotations
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "when there is no exception in return list of contacts"),
            @ApiResponse(code = 404, message = "when url or path is not true"),
            @ApiResponse(code = 401, message = "when there isn't enough permission to request this operation")
    })
    @GetMapping(value = "/getContacts")
    public List<ContactEntity> getContacts() {
        return contactService.getAll();
    }

    // 6. API operation with handling unknown contactId code by throwing ResourceNotFoundException class
    @GetMapping(value = "/getContact/{id}")
    public ResponseEntity<ContactEntity> getContactById(@PathVariable("id") int contactId) throws ResourceNotFoundException {
        ContactEntity contactEntity = contactService.get(contactId).orElseThrow(() -> new ResourceNotFoundException("This contact couldn't be found with requested id=" + contactId));
        return ResponseEntity.ok().body(contactEntity);
    }

    // 7. API operation description and return class type and operation parameters descriptions with @ApiParam annotation
    @ApiOperation(value = "using this method to get list of contacts as paging and sorting items", response = Page.class)
    @GetMapping(value = "/getContactsWithPagingAndSorting/{page}/{count}")
    public ResponseEntity<Page<ContactEntity>> getContacts(
            @ApiParam(value = "get page number", required = true, format = "int", example = "0, 1, 2, ...")
            @PathVariable("page") int page,
            @ApiParam(value = "number of items per page; more than 5 and less than 20", required = true, format = "int", example = "5, 10, 20, ...")
            @PathVariable("count") int count) {
        Page<ContactEntity> contacts = contactService.getAllWithPagingAndSorting(page, count);
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @GetMapping(value = "/getPageableContacts/{page}")
    public ResponseEntity<Page<ContactEntity>> getPageableContacts(@PathVariable("page") int page, @RequestParam("count") Integer count) {
        int numberOfItemPerPage = count != null ? count : 10;
        Page<ContactEntity> contacts = contactService.getAllWithPagingAndSorting(page, numberOfItemPerPage);
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    // 8. save new contact information with POST request and reu
    @PostMapping(value = "/createOrUpdateContact")
    public ResponseEntity<ContactEntity> saveContact(@RequestBody ContactEntity contact) {
        try {
            contactService.addOrUpdateContact(contact);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 9. Delete a contact record with Delete http request and send contact_id with URL query param and return Map return value or raise an exception for unknown contact Id
    @DeleteMapping(value = "/deleteContact/{id}")
    public Map<String, Boolean> deleteContact(@PathVariable("id") int contactId) throws ResourceNotFoundException {

        ContactEntity contact = contactService.get(contactId).orElseThrow(() -> new ResourceNotFoundException("Contact Not found this id :: " + contactId));

        contactService.delete(contact);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
