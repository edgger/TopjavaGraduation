package com.github.edgarzed.topjavagraduation.service;

import com.github.edgarzed.topjavagraduation.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteService {

    Vote create(Vote vote);

    Vote get(int id);

    List<Vote> getAll();

    List<Vote> getBetween(LocalDate startDate, LocalDate endDate);
}
