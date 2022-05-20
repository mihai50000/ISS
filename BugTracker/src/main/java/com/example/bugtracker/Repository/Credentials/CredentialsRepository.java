package com.example.bugtracker.Repository.Credentials;

import com.example.bugtracker.Model.Credentials;

public interface CredentialsRepository {
    Credentials getCredentials(String username);
}
