package ThreeCS;

import DoubleCS.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactManagementUI {
    public static void main(String[] args) {
        ContactManagementController controller = new ContactManagementController();
        Scanner scanner = new Scanner(System.in);
        List<Contact> contacts = new ArrayList<>();

        while (true) {
            System.out.println("Contact Management System");
            System.out.println("1. View Contacts");
            System.out.println("2. Add Contact");
            System.out.println("3. Update Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    contacts = controller.viewContacts();
                    System.out.println("Contacts:");
                    for (int i = 0; i < contacts.size(); i++) {
                        System.out.println(i + ". " + contacts.get(i));
                    }
                    break;

                case 2:
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Address: ");
                    String address = scanner.nextLine();
                    System.out.print("Enter Phone: ");
                    String phone = scanner.nextLine();
                    controller.addContact(new Contact(name, address, phone));
                    System.out.println("Contact added.");
                    break;

                case 3:
                    System.out.print("Enter the index of the contact to update: ");
                    int indexToUpdate = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    if (indexToUpdate >= 0 && indexToUpdate < contacts.size()) {
                        System.out.print("Enter new Name: ");
                        String newName = scanner.nextLine();
                        System.out.print("Enter new Address: ");
                        String newAddress = scanner.nextLine();
                        System.out.print("Enter new Phone: ");
                        String newPhone = scanner.nextLine();
                        controller.updateContact(indexToUpdate, new Contact(newName, newAddress, newPhone));
                        System.out.println("Contact updated.");
                    } else {
                        System.out.println("Invalid index.");
                    }
                    break;

                case 4:
                    System.out.print("Enter the index of the contact to delete: ");
                    int indexToDelete = scanner.nextInt();
                    if (indexToDelete >= 0 && indexToDelete < contacts.size()) {
                        controller.deleteContact(indexToDelete);
                        System.out.println("Contact deleted.");
                    } else {
                        System.out.println("Invalid index.");
                    }
                    break;

                case 5:
                    System.out.println("Exiting the Contact Management System.");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
