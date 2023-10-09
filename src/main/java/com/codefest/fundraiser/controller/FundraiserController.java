package com.codefest.fundraiser.controller;

import com.codefest.fundraiser.model.FundRaiser;
import com.codefest.fundraiser.repository.FundRaiserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FundraiserController {

    private final FundRaiserRepository fundRaiserRepository;

    @GetMapping("/getFundraiserByID/{id}")
    public ResponseEntity<FundRaiser> getFundRaiserByID(@PathVariable Long id) {
        log.info("Get Fundraiser by ID called");
        return fundRaiserRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.badRequest().build());

    }

    @PostMapping("/createFundraiser")
    public ResponseEntity<FundRaiser> createFundRaiser(@RequestBody FundRaiser fundRaiser) {
        FundRaiser newfundraiser = fundRaiserRepository.save(fundRaiser);
        log.info("New Fundraiser Created:{}", newfundraiser);
        return new ResponseEntity<>(newfundraiser, HttpStatus.CREATED);
    }

    @PostMapping("/updateFundraiser")
    public ResponseEntity<FundRaiser> updateFundRaiser(@RequestBody FundRaiser fundRaiser) {
        Optional<FundRaiser> campaign = fundRaiserRepository.findById(fundRaiser.getFrID());
        if (campaign.isPresent()) {

            FundRaiser updatedFr = campaign.get();
            log.info("Old Fundraiser: {}", updatedFr);
            updatedFr.setNameOfFundraiser(fundRaiser.getNameOfFundraiser());
            updatedFr.setDescriptionOfFundraiser(fundRaiser.getDescriptionOfFundraiser());
            updatedFr.setTargetAmount(fundRaiser.getTargetAmount());
            fundRaiserRepository.save(updatedFr);
            return new ResponseEntity<>(updatedFr, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteFundraiserById/{id}")
    public ResponseEntity<HttpStatus> deleteFundraiserById(@PathVariable Long id) {
        fundRaiserRepository.deleteById(id);
        log.info("Fundraiser deleted : {}", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
