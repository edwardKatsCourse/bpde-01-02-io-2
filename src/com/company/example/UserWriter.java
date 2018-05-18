package com.company.example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;

public class UserWriter {
    private static final String FILENAME = "C:\\Users\\Edward\\Desktop\\BPDE-01-02 IO 2\\users.txt";

    public static void save(Set<User> users) throws IOException {
        File file = new File(FILENAME);
        if (!file.exists()) {
            file.createNewFile();
        }

        StringBuilder builder = new StringBuilder();

        int counter = 0;
        for (User user : users) {
            counter++;
            builder.append(user.getFirstName())
                    .append(" ")
                    .append(user.getLastName())
                    .append(" ")
                    .append(user.getEmail())
                    .append(" ")
                    .append(user.getDateOfBirth().toString());

            if (counter != users.size()) {
                builder.append("\n");
            }
        }

        OutputStream outputStream = new FileOutputStream(file);
        outputStream.write(builder.toString().getBytes());
        outputStream.close();
    }
}
