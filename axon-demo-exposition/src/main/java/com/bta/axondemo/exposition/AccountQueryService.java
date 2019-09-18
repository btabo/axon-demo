package com.bta.axondemo.exposition;

import java.util.List;

public interface AccountQueryService {
    public List<Object> listEventsForAccount(String accountNumber);
}
