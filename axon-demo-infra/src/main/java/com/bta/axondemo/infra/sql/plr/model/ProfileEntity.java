package com.bta.axondemo.infra.sql.plr.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "profile")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileEntity {

    @Id
    private String id;

    private String customerId;
    private String insuranceType;
    private String insuranceFormula;

    @ManyToOne
    private PlrEntity plrEntity;
}
