package com.codefest.fundraiser.controller;

import com.codefest.fundraiser.model.Volunteer;
import com.codefest.fundraiser.repository.VolunteerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class VolunteerController {

    private final VolunteerRepository volunteerRepository;

    @GetMapping("/getAllVolunteers")
    public ResponseEntity<List<Volunteer>> getAllVolunteers(){
        List<Volunteer> volunteerList = new ArrayList<>(volunteerRepository.findAll());
        return ResponseEntity.status(volunteerList.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK).body(volunteerList);
    }

    @GetMapping("/getVolunteerById/{id}")
    public ResponseEntity<Volunteer> getVolunteerById(@PathVariable Long id){
        return volunteerRepository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @PostMapping("/addVolunteer")
    public ResponseEntity<Volunteer> addVolunteer(@RequestBody Volunteer volunteer){
        return new ResponseEntity<>(volunteerRepository.save(volunteer), HttpStatus.OK);
    }

    @PostMapping("/updateVolunteerById/{id}")
    public ResponseEntity<Volunteer> updateVolunteerById(@PathVariable Long id, @RequestBody Volunteer newVolunteer){
        Optional<Volunteer> optionalOldVolunteer = volunteerRepository.findById(id);

        if(optionalOldVolunteer.isPresent()){
            Volunteer updatedVolunteer = optionalOldVolunteer.get();
            updatedVolunteer.setFirstName(newVolunteer.getFirstName());
            updatedVolunteer.setLastName(newVolunteer.getLastName());
            updatedVolunteer.setExpertise(newVolunteer.getExpertise());
            updatedVolunteer.setCampaignAssociation(newVolunteer.getCampaignAssociation());
            return ResponseEntity.ok(volunteerRepository.save(updatedVolunteer));

        }else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/deleteVolunteerById/{id}")
    public ResponseEntity<HttpStatus> deleteVolunteerById(@PathVariable Long id){
        volunteerRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
