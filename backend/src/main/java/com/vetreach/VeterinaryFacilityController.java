package com.vetreach;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class VeterinaryFacilityController {

    private final VeterinaryFacilityRepository facilityRepository;

    public VeterinaryFacilityController(VeterinaryFacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    @GetMapping("/facilities")
    public List<VeterinaryFacility> getAllFacilities() {
        return facilityRepository.findAll();
    }
}