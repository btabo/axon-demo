package com.bta.axondemo.domain.plr.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Optional;

@Builder
@Getter
public class TransactionDescription {

    /**
     * Montant du prêt demandé
     **/
    private BigDecimal loanAmount;

    /**
     * Durée du pret demandé
     **/
    private Integer loanTerm;

    public static class TransactionDescriptionBuilder {

        public TransactionDescriptionBuilder fromTransactionDescription(TransactionDescription transactionDescription) {
            Optional.ofNullable(transactionDescription).ifPresent(td -> {
                this.loanAmount = td.loanAmount;
                this.loanTerm = td.loanTerm;
            });
            return this;
        }

    }

}
