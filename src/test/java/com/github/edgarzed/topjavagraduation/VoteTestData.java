package com.github.edgarzed.topjavagraduation;

import com.github.edgarzed.topjavagraduation.model.Vote;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.github.edgarzed.topjavagraduation.RestaurantTestData.*;
import static com.github.edgarzed.topjavagraduation.UserTestData.*;

public class VoteTestData {
    private VoteTestData() {
    }

    public static final Vote VOTE1 = new Vote(1000, USER1, RESTAURANT2,LocalDate.of(2018,4,22));
    public static final Vote VOTE2 = new Vote(1001, USER2, RESTAURANT3,LocalDate.of(2018,4,22));
    public static final Vote VOTE3 = new Vote(1002, USER3, RESTAURANT3,LocalDate.of(2018,4,22));
    public static final Vote VOTE4 = new Vote(1003, USER1, RESTAURANT1,LocalDate.of(2018,4,23));
    public static final Vote VOTE5 = new Vote(1004, USER2, RESTAURANT1,LocalDate.of(2018,4,23));
    public static final Vote VOTE6 = new Vote(1005, USER3, RESTAURANT2,LocalDate.of(2018,4,23));

    public static final List<Vote> VOTES = Arrays.asList(VOTE6,VOTE4,VOTE5,VOTE2,VOTE3,VOTE1);
}
