package com.github.edgarzed.topjavagraduation.service;

import com.github.edgarzed.topjavagraduation.dao.VoteDAO;
import com.github.edgarzed.topjavagraduation.model.Restaurant;
import com.github.edgarzed.topjavagraduation.model.User;
import com.github.edgarzed.topjavagraduation.model.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VoteServiceImpl implements VoteService {
    private final VoteDAO voteDAO;
    private final RestaurantService restaurantService;

    @Autowired
    public VoteServiceImpl(VoteDAO voteDAO, RestaurantService restaurantService) {
        this.voteDAO = voteDAO;
        this.restaurantService = restaurantService;
    }

    @Override
    public Vote save(User user, int restaurantId, LocalDate date) {
        Restaurant restaurant = restaurantService.get(restaurantId);
        Vote vote;
        List<Vote> todaysUserVoice = getFiltered(user, date, date);
        if (todaysUserVoice.size() > 0) {
            vote = todaysUserVoice.get(0);
            vote.setRestaurant(restaurant);
        } else {
            vote = new Vote(null, user, restaurant, date);
        }
        return voteDAO.save(vote);
    }

    @Override
    public List<Vote> getAll() {
        return voteDAO.getAll();
    }

    @Override
    public List<Vote> getFiltered(User user, LocalDate startDate, LocalDate endDate) {
        return voteDAO.getFiltered(user, startDate, endDate);
    }
}
