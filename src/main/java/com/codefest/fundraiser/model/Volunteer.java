package com.codefest.fundraiser.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Volunteer")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Volunteer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(hidden = true)
    private Long id;
    private String firstName;
    private String lastName;
    private String expertise;
    private String campaignAssociation;

}
