package com.example.bugtracker.Repository;

import com.example.bugtracker.Model.Credentials;

public interface CredentialsRepository {
    Credentials getCredentials(String username);
}
