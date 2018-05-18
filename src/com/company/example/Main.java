package com.company.example;

import java.io.IOException;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) throws IOException {
        UserRepository userRepository = new UserRepository();
//        userRepository.getAll().forEach(System.out::println);

        User user = new User(
                "David",
                "Fridman",
                "fridman@site.com",
                LocalDate.of(1986, 6, 20));

        userRepository.save(user);
//        userRepository.getAll().forEach(System.out::println);


        user = new User(
                "Sarah",
                "Daniels",
                "daniels@site.com",
                LocalDate.of(1991, 12, 31));

        userRepository.save(user);

        user = new User(
                "Jacob",
                "Samuels",
                "samuels@site.com",
                LocalDate.of(1991, 12, 31));

        userRepository.save(user);
        userRepository.getAll().forEach(System.out::println);
        System.out.println("*********************");

        User byEmail = userRepository.findByEmail("samuels@site.com");

        System.out.printf("USER: %s", byEmail.toString());

    }
}
