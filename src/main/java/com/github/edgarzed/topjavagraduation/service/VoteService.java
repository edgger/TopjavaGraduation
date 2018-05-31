package com.github.edgarzed.topjavagraduation.service;

import com.github.edgarzed.topjavagraduation.model.User;
import com.github.edgarzed.topjavagraduation.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteService {

    Vote save(User user, int restaurantId, LocalDate date);

    List<Vote> getAll();

    List<Vote> getFiltered(User user, LocalDate startDate, LocalDate endDate);
}
