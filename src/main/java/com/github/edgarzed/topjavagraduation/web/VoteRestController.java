package com.github.edgarzed.topjavagraduation.web;

import com.github.edgarzed.topjavagraduation.model.User;
import com.github.edgarzed.topjavagraduation.model.Vote;
import com.github.edgarzed.topjavagraduation.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
    public List<Vote> getBetween(@RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                 @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        if (startDate == null && endDate == null) {
            return voteService.getAll();
        } else {
            return voteService.getFiltered(null, startDate, endDate);
        }
    }

    @PutMapping("/todays/{restaurantId}")
    public ResponseEntity createUpdateToday(@PathVariable("restaurantId") int restaurantId,
                                            @AuthenticationPrincipal User user,
                                            @RequestParam(value = "nowTime", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime nowTime) {
        if (user == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        LocalDateTime nowDateTime = LocalDateTime.now();
        if (nowTime == null) {
            nowTime = nowDateTime.toLocalTime();
        }
        if (nowTime.isAfter(LocalTime.of(11, 0))) {
            return ResponseEntity.status(HttpStatus.LOCKED).build();
        }
        voteService.save(user, restaurantId, nowDateTime.toLocalDate());
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
/*
    /votes?startDate&endDate --get(all/filtered)
    /votes/todays/{menu id} --put(vote)
*/