package com.codefest.fundraiser.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "FundRaiser")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FundRaiser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long frID;
    private String nameOfFundraiser;
//    private Volunteer volunteer;
    private Double targetAmount;
    private String descriptionOfFundraiser;

    //private string coverPhotoForFundraiser;
}
