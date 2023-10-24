package ThreeCS;


import DoubleCS.Contact;

import java.util.List;

public class ContactManagementController {
    private ContactManagementService service = new ContactManagementService();

    public List<Contact> viewContacts() {
        return service.retrieveContacts();
    }

    public void addContact(Contact contact) {
        service.addContact(contact);
    }

    public void updateContact(int index, Contact updatedContact) {
        service.updateContact(index, updatedContact);
    }

    public void deleteContact(int index) {
        service.deleteContact(index);
    }
}

