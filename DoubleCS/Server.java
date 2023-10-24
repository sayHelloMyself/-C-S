package DoubleCS;

import java.io.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private List<Contact> contacts;

    public Server() {
        contacts = new ArrayList<>();
        Contact contact1 = new Contact("王雄辉","西南石油大学","123456");
        Contact contact2 = new Contact("张三","西南石油大学","123456");
        contacts.add(contact1);
        contacts.add(contact2);
    }

    public void start(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is running on port " + port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                handleClient(clientSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClient(Socket clientSocket) {
        try (
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())
        ) {
            while (true) {
                String request = (String) in.readObject();

                if ("VIEW".equals(request)) {
                    out.writeObject(contacts);
                } else if ("ADD".equals(request)) {
                    Contact contact = (Contact) in.readObject();
                    contacts.add(contact);
                } else if ("UPDATE".equals(request)) {
                    int index = (int) in.readObject();
                    Contact updatedContact = (Contact) in.readObject();
                    if (index >= 0 && index < contacts.size()) {
                        contacts.get(index).updateContact(updatedContact.getName(), updatedContact.getAddress(), updatedContact.getPhone());
                    }
                } else if ("DELETE".equals(request)) {
                    int index = (int) in.readObject();
                    if (index >= 0 && index < contacts.size()) {
                        contacts.remove(index);
                    }
                } else if ("EXIT".equals(request)) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start(12345);
    }
}


