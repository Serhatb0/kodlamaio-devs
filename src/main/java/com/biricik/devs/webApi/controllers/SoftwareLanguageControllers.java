package com.biricik.devs.webApi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biricik.devs.business.abstracts.SoftwareLanguageService;
import com.biricik.devs.entities.concretes.SoftwareLanguage;

@RestController
@RequestMapping("/api")
public class SoftwareLanguageControllers {

    private final SoftwareLanguageService softwareLanguageService;

    @Autowired
    public SoftwareLanguageControllers(SoftwareLanguageService softwareLanguageService) {
        this.softwareLanguageService = softwareLanguageService;
    }

    @GetMapping("/software-languages")
    public List<SoftwareLanguage> getSoftwareLanguage() {
        return softwareLanguageService.getSoftwareLanguage();
    }

    @GetMapping("/software-languages/{id}")
    public SoftwareLanguage getByIdSoftwareLanguage(@PathVariable int id) {
        return softwareLanguageService.getByIdSoftwareLanguage(id);
    }

    @DeleteMapping("/software-languages/{id}")
    public String deleteSoftwareLanguage(@PathVariable int id) {
        softwareLanguageService.deleteSoftwareLanguage(id);
        return "successful deleting";
    }

    @PutMapping("/software-languages/{id}")
    public SoftwareLanguage updateSoftwareLanguage(SoftwareLanguage softwareLanguage) {
        softwareLanguageService.updateSoftwareLanguage(softwareLanguage);
        return softwareLanguage;
    }

    @PostMapping("/software-languages")
    public SoftwareLanguage addSoftwareLanguage(SoftwareLanguage softwareLanguage) {
        return softwareLanguageService.addSoftwareLanguage(softwareLanguage);
    }

}
