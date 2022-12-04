package com.biricik.devs.event;

import org.springframework.context.ApplicationEvent;

public class ProgrammingLanguageEsDeletedEvent extends ApplicationEvent {

    public ProgrammingLanguageEsDeletedEvent(int id) {
        super(id);
    }

}
