package com.example.demo.service;

import com.example.demo.entity.HazardData;
import com.example.demo.repository.HazardDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HazardDataService {

    private final HazardDataRepository hazardDataRepository;

    public HazardDataService(HazardDataRepository hazardDataRepository) {
        this.hazardDataRepository = hazardDataRepository;
    }

    public List<HazardData> getAllHazardData() {
        return hazardDataRepository.findAll();
    }

    public HazardData saveHazardData(HazardData hazardData) {
        return hazardDataRepository.save(hazardData);
    }

    public void deleteHazardDataByHid(Long hid) {
        hazardDataRepository.deleteById(hid);
    }
    public HazardData findById(Long id) {
        // Find by ID returns an Optional
        Optional<HazardData> result = hazardDataRepository.findById(id);
        // Return the entity or handle the case when it's not found
        return result.orElse(null);
    }
}
