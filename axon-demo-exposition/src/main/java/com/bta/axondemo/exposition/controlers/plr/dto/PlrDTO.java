package com.bta.axondemo.exposition.controlers.plr.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter @Builder @NoArgsConstructor @AllArgsConstructor
public class PlrDTO {

    private String plrId;

    private BigDecimal loanAmount;

    private Integer loanTerm;

    private List<ProfileDTO> profiles;

}
