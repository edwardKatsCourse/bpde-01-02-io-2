package com.company.example;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * \n -> new line
 * \t -> tab
 *
 * There's also an invisible "end-of-file" symbol, indication,
 * that the file is over and there's nothing left to read
 *
 * Example:
 * Peter Dale dale@site.com 1990-01-01\n
 * Peter Dale dale@site.com 1990-01-01\n
 *
 *
 * \tabcdefghij\nzyxwvutsrq\n
 *
 * 	abcdefghij
 * zyxwvutsrq
 *
 * {"firstName":"Peter", "lastName":"Dale"} -> JSON object
 */
public class UserRepository {
    private static Set<User> users = new HashSet<>();

    public void save(User user) throws IOException {
        users.add(user);
        UserWriter.save(users);
    }

    public void update(User user) {


        users.add(user);

    }

    public Set<User> getAll() {
        try {
            users = UserReader.getUsers();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User findByEmail(String email) throws IOException {
        users = UserReader.getUsers();
        return users
                .stream()
                .filter(x -> x.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }


}
