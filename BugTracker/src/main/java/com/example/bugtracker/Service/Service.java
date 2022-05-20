package com.example.bugtracker.Service;

import com.example.bugtracker.Model.*;
import com.example.bugtracker.Repository.Bugs.BugsRepository;
import com.example.bugtracker.Repository.Complains.ComplainsRepository;
import com.example.bugtracker.Repository.Credentials.CredentialsRepository;
import lombok.Getter;
import lombok.NonNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import lombok.RequiredArgsConstructor;

import java.nio.channels.SeekableByteChannel;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class Service {
    private static final Logger LOGGER = LogManager.getLogger(Service.class);

    @Getter
    private User loggedUser;
    private final CredentialsRepository credentialsRepository;
    private final ComplainsRepository complainsRepository;
    private final BugsRepository bugsRepository;

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

    public void saveComplain(String name, LocalDateTime localDateTime, Severity severity, String description) {
        complainsRepository.save(new Complain(name, localDateTime, severity, description));
    }

    public void linkComplain(int complainId, int bugId) {
        complainsRepository.linkComplain(complainId, bugId);
    }

    public List<Complain> getAllComplains() {
        return complainsRepository.getAllComplains();
    }

    public List<Complain> getUnlinkedComplains() {
        return getAllComplains()
                .stream()
                .filter((complain -> complain.getLinkedBug() == null))
                .collect(Collectors.toList());
    }

    public void saveBug(String name, LocalDateTime creationDate, Severity severity, String description) {
        bugsRepository.save(new Bug(name, creationDate, severity, description));
    }
}
