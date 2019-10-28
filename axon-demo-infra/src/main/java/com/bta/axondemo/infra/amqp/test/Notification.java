package com.bta.axondemo.infra.amqp.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter @AllArgsConstructor @NoArgsConstructor
public class Notification implements Serializable {

    private String notificationType;
    private String msg;
}
