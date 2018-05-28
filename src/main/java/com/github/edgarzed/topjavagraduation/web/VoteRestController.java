package com.github.edgarzed.topjavagraduation.web;

import com.github.edgarzed.topjavagraduation.model.Vote;
import com.github.edgarzed.topjavagraduation.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/votes")
public class VoteRestController {

    @Autowired
    VoteService voteService;

    @GetMapping
    public List<Vote> getBetween(@RequestParam(value = "startDate", required = false) LocalDate startDate,
                                 @RequestParam(value = "endDate", required = false) LocalDate endDate){
        return null;
    }

    @PutMapping("/todays/{menuId}")
    public ResponseEntity<Vote> createUpdateToday(@PathVariable("menuId") int menuId){
        return null;
    }
}
/*
    /votes?startDate&endDate --get(all/filtered)
    /votes/todays/{menu id} --put(vote)
*/