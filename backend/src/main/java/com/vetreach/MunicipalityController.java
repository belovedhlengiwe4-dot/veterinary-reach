package com.vetreach;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class MunicipalityController {

    private final MunicipalityRepository municipalityRepository;

    public MunicipalityController(MunicipalityRepository municipalityRepository) {
        this.municipalityRepository = municipalityRepository;
    }

    @GetMapping("/municipalities")
    public List<Municipality> getAllMunicipalities() {
        return municipalityRepository.findAll();
    }
}
