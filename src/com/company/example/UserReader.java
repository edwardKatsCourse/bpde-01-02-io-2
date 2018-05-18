package com.company.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserReader {

    private static final String FILENAME = "C:\\Users\\Edward\\Desktop\\BPDE-01-02 IO 2\\users.txt";


    public static Set<User> getUsers() throws IOException {
        Set<User> internalUsers = new HashSet<>();

        for (String rawUser : getRawUsers(readFile())) {
            String [] data = rawUser.split(" ");
            User user = new User(
                    data[0],
                    data[1],
                    data[2],
                    LocalDate.parse(data[3].trim())
            );

            //String s = " abc "
            //s.trim() -> "abc"

            //2018-01-01

            internalUsers.add(user);
        }
        return internalUsers;
    }

    private static List<String> getRawUsers(String fileContent) {
        return Arrays.asList(fileContent.split("\n"));
    }

    private static String readFile() throws IOException {
        File file = new File(FILENAME);
        if (!file.exists()) {
            file.createNewFile();
        }

        InputStream inputStream = new FileInputStream(file);

        byte [] buffer = new byte[100* 1000];
        while (inputStream.available() > 0) {
            inputStream.read(buffer);
        }
        inputStream.close();
        return new String(buffer);
    }
}
