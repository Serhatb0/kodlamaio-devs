package com.biricik.devs.event;

import org.springframework.context.ApplicationEvent;

import com.biricik.devs.entities.concretes.ProgrammingLanguage;


public class ProgrammingLanguageEsCreatedEvent extends ApplicationEvent {

    public ProgrammingLanguageEsCreatedEvent(ProgrammingLanguage source) {
        super(source);
    }
    
}
