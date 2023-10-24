package DoubleCS;

import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connected to the server.");

            while (true) {
                System.out.println("1. View Contacts");
                System.out.println("2. Add Contact");
                System.out.println("3. Update Contact");
                System.out.println("4. Delete Contact");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (choice == 1) {
                    out.writeObject("VIEW");
                    List<Contact> contacts = (List<Contact>) in.readObject();
                    for (int i = 0; i < contacts.size(); i++) {
                        System.out.println(i + ". " + contacts.get(i));
                    }
                } else if (choice == 2) {
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Address: ");
                    String address = scanner.nextLine();
                    System.out.print("Enter Phone: ");
                    String phone = scanner.nextLine();
                    Contact contact = new Contact(name, address, phone);

                    out.writeObject("ADD");
                    out.writeObject(contact);
                    System.out.println("Contact added.");
                } else if (choice == 3) {
                    System.out.print("Enter the index of the contact to update: ");
                    int index = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter new Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter new Address: ");
                    String address = scanner.nextLine();
                    System.out.print("Enter new Phone: ");
                    String phone = scanner.nextLine();
                    Contact updatedContact = new Contact(name, address, phone);

                    out.writeObject("UPDATE");
                    out.writeObject(index);
                    out.writeObject(updatedContact);
                    System.out.println("Contact updated.");
                } else if (choice == 4) {
                    System.out.print("Enter the index of the contact to delete: ");
                    int index = scanner.nextInt();
                    out.writeObject("DELETE");
                    out.writeObject(index);
                    System.out.println("Contact deleted.");
                } else if (choice == 5) {
                    out.writeObject("EXIT");
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}


