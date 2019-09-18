package com.bta.axondemo.exposition.controlers.accounts.services;

import java.util.List;

public interface AccountQueryService {
    public List<Object> listEventsForAccount(String accountNumber);
}
