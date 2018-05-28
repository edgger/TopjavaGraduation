package com.github.edgarzed.topjavagraduation.service;

import com.github.edgarzed.topjavagraduation.model.Vote;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VoteServiceImpl implements VoteService {
    @Override
    public Vote create(Vote vote) {
        return null;
    }

    @Override
    public Vote get(int id) {
        return null;
    }

    @Override
    public List<Vote> getAll() {
        return null;
    }

    @Override
    public List<Vote> getBetween(LocalDate startDate, LocalDate endDate) {
        return null;
    }
}
