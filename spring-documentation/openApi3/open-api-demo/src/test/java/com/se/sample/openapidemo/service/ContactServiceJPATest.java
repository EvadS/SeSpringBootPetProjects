package com.se.sample.openapidemo.service;

import com.se.sample.openapidemo.domain.Contact;
import com.se.sample.openapidemo.exception.ResourceNotFoundException;
import org.junit.Rule;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest
 class ContactServiceJPATest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    @Autowired
    private DataSource dataSource;
    @Autowired
    private ContactService contactService;

    @Test
    public void testSaveUpdateDeleteContact() throws Exception {
        Contact c = new Contact();
        c.setName("Portgas D. Ace");
        c.setPhone("09012345678");
        c.setEmail("ace@whitebeard.com");

        contactService.save(c);
        assertNotNull(c.getId());

        Contact findContact = contactService.findById(c.getId());
        assertEquals("Portgas D. Ace", findContact.getName());
        assertEquals("ace@whitebeard.com", findContact.getEmail());

        // update record
        c.setEmail("ace@whitebeardpirat.es");
        contactService.update(c);

        // test after update
        findContact = contactService.findById(c.getId());
        assertEquals("ace@whitebeardpirat.es", findContact.getEmail());

        // test delete
        contactService.deleteById(c.getId());

        // query after delete
       // exceptionRule.expect(ResourceNotFoundException.class);
       // contactService.findById(c.getId());
    }
}