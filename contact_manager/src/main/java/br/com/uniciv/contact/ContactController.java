package br.com.uniciv.contact;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
public class ContactController {

    @Autowired
    private ContactRepository repository;

    @GetMapping("/contato")    
    public List<Contact> allContacts(){
        return repository.findAll();
    }

    @PostMapping("/contato")
    public Contact salve(@RequestBody Contact contact){
        return repository.save(contact);
    }

}
