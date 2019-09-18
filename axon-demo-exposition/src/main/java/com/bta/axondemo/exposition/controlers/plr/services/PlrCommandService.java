package com.bta.axondemo.exposition.controlers.plr.services;

import com.bta.axondemo.exposition.controlers.accounts.dto.AccountCreateDTO;
import com.bta.axondemo.exposition.controlers.accounts.dto.MoneyCreditDTO;
import com.bta.axondemo.exposition.controlers.accounts.dto.MoneyDebitDTO;
import com.bta.axondemo.exposition.controlers.plr.dto.PlrDTO;

import java.util.concurrent.CompletableFuture;

public interface PlrCommandService {

    public CompletableFuture<String> createPlr(PlrDTO plrDTO);

}
