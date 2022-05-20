package com.example.bugtracker.Service;

import com.example.bugtracker.Model.Credentials;
import com.example.bugtracker.Model.User;
import com.example.bugtracker.Repository.CredentialsRepository;
import lombok.Getter;
import lombok.NonNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
public class Service {
    private static final Logger LOGGER = LogManager.getLogger(Service.class);

    @Getter
    private User loggedUser;
    private final CredentialsRepository credentialsRepository;

    public boolean tryLogIn(@NonNull String username, @NonNull String password) {
        Credentials credentials = credentialsRepository.getCredentials(username);

        if (credentials != null && credentials.getUser() != null && Objects.equals(credentials.getPassword(), password)) {
            loggedUser = credentials.getUser();
            LOGGER.info("Logged user: " + username);
            return true;
        } else {
            LOGGER.info("Failed to log user: " + username);
            return false;
        }
    }
}
