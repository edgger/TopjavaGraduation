package com.github.edgarzed.topjavagraduation.dao;

import com.github.edgarzed.topjavagraduation.model.User;
import com.github.edgarzed.topjavagraduation.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteDAO {

    Vote save(Vote vote);

    List<Vote> getAll();

    List<Vote> getFiltered(User user, LocalDate startDate, LocalDate endDate, boolean full);
}
