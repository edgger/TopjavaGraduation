package com.github.edgarzed.topjavagraduation.web;

import com.github.edgarzed.topjavagraduation.model.User;
import com.github.edgarzed.topjavagraduation.model.Vote;
import com.github.edgarzed.topjavagraduation.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(VoteRestController.REST_URL)
public class VoteRestController {
    static final String REST_URL = "/rest/votes";

    @Autowired
    VoteService voteService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vote> getBetween(@RequestParam(value = "startDate", required = false) LocalDate startDate,
                                 @RequestParam(value = "endDate", required = false) LocalDate endDate,
                                 @AuthenticationPrincipal User user) {
        if (startDate == null && endDate == null) {
            return voteService.getAll();
        } else {
            return voteService.getFiltered(user, startDate, endDate);
        }
    }

    @PutMapping("/todays/{restaurantId}")
    public ResponseEntity createUpdateToday(@PathVariable("restaurantId") int restaurantId,
                                            @AuthenticationPrincipal User user) {
        LocalDateTime nowDateTime = LocalDateTime.now();
        if (nowDateTime.toLocalTime().isAfter(LocalTime.of(11, 0))) {
            return ResponseEntity.status(HttpStatus.LOCKED).build();
        }
        List<Vote> between = voteService.getFiltered(user, nowDateTime.toLocalDate(), nowDateTime.toLocalDate());
        if (voteService.save(user, restaurantId, nowDateTime.toLocalDate())) {
            if (between.size() == 0) {
                return ResponseEntity.status(HttpStatus.CREATED).build();
            } else {
                return ResponseEntity.status(HttpStatus.ACCEPTED).build();
            }
        } else {
            //TODO: invalid restaurantId?
            return ResponseEntity.status(HttpStatus.LOCKED).build();
        }
    }
}
/*
    /votes?startDate&endDate --get(all/filtered)
    /votes/todays/{menu id} --put(vote)
*/