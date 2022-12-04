package com.biricik.devs.event.listener;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.biricik.devs.dao.abstracts.elasticsearch.ProgrammingLanguageEsRepository;
import com.biricik.devs.entities.concretes.ProgrammingLanguage;
import com.biricik.devs.entities.elasticsearch.ProgrammingLanguageEs;
import com.biricik.devs.event.ProgrammingLanguageEsCreatedEvent;
import com.biricik.devs.event.ProgrammingLanguageEsDeletedEvent;

@Component
public class ProgrammingLanguageEsEventListener {

    private final ProgrammingLanguageEsRepository programmingLanguageEsRepository;

    public ProgrammingLanguageEsEventListener(ProgrammingLanguageEsRepository programmingLanguageEsRepository) {
        this.programmingLanguageEsRepository = programmingLanguageEsRepository;
    }

    @Async
    @EventListener
    public void ProgrammingLanguageEsCreatedEventHandler(
            ProgrammingLanguageEsCreatedEvent programmingLanguageEsCreatedEvent) {

        ProgrammingLanguage programmingLanguage = (ProgrammingLanguage) programmingLanguageEsCreatedEvent.getSource();

        programmingLanguageEsRepository.save(ProgrammingLanguageEs
                .programmingLanguageConvertToProgrammingLanguageEs(programmingLanguage));

    }

    @Async
    @EventListener
    public void ProgrammingLanguageEsDeletedEventHandler(
            ProgrammingLanguageEsDeletedEvent programmingLanguageEsDeletedEvent) {

        int programmingLanguageEsId = (int) programmingLanguageEsDeletedEvent.getSource();

        programmingLanguageEsRepository.deleteById(programmingLanguageEsId);

    }
}
