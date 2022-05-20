package com.example.bugtracker.Repository.Complains;

import com.example.bugtracker.Model.Complain;

import java.util.List;

public interface ComplainsRepository {

    void save(Complain complain);

    void linkComplain(int complainId, int bugId);

    List<Complain> getAllComplains();
}
