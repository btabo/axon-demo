package com.bta.axondemo.infra.sql.plr.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity(name = "plr")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlrEntity {

    @Id
    private String plrId;

    private BigDecimal loanAmount;

    private Integer loanTerm;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProfileEntity> profiles;

    private BigDecimal revenues;

    private BigDecimal charges;

}
