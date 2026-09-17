package com.vetreach;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class TravelCostController {

    private final TravelCostService travelCostService;
    private final TravelCostRepository travelCostRepository;

    public TravelCostController(TravelCostService travelCostService, TravelCostRepository travelCostRepository) {
        this.travelCostService = travelCostService;
        this.travelCostRepository = travelCostRepository;
    }

    @GetMapping("/generate-travel-costs")
    public String generate() {
        travelCostService.generateAllTravelCosts();
        return "Travel costs generated!";
    }

    @GetMapping("/travel-costs")
    public List<TravelCost> getAll() {
        return travelCostRepository.findAll();
    }
}
