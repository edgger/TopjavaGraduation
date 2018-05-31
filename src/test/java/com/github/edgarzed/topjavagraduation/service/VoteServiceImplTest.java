package com.github.edgarzed.topjavagraduation.service;

import com.github.edgarzed.topjavagraduation.RestaurantTestData;
import com.github.edgarzed.topjavagraduation.UserTestData;
import com.github.edgarzed.topjavagraduation.VoteTestData;
import com.github.edgarzed.topjavagraduation.model.Vote;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class VoteServiceImplTest extends AbstractServiceTest{

    @Autowired
    VoteService voteService;

    @Test
    public void save() {
        LocalDate nowDate = LocalDate.now();
        Vote created = new Vote(1006, UserTestData.USER1, RestaurantTestData.RESTAURANT2, nowDate);
        Vote saved = voteService.save(UserTestData.USER1, 1001, nowDate);

        assertThat(created).isEqualToComparingFieldByField(saved);
    }

    @Test
    public void getAll() {
        assertThat(voteService.getAll()).usingDefaultElementComparator().isEqualTo(VoteTestData.VOTES);
    }

    @Test
    public void getFiltered() {
        List<Vote> filtered = voteService.getFiltered(UserTestData.USER3, LocalDate.of(2018, 4, 22), LocalDate.of(2018, 4, 22));
        assertThat(filtered).hasSize(1).contains(VoteTestData.VOTE3);
    }
}