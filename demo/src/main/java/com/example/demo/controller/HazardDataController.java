package com.example.demo.controller;

import com.example.demo.entity.HazardData;
import com.example.demo.service.HazardDataService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/hazarddata")
public class HazardDataController {

    private final HazardDataService hazardDataService;

    public HazardDataController(HazardDataService hazardDataService) {
        this.hazardDataService = hazardDataService;
    }

    @GetMapping
    public List<HazardData> getAllHazardData() {
        return hazardDataService.getAllHazardData();
    }

    @PostMapping("/add")
    public String addHazard(
            @RequestPart("hazardType") String hazardType,
            @RequestPart("photo") MultipartFile photo,
            @RequestPart("gps") String gps,
            @RequestPart("state") String state,
            @RequestPart("dates") String dates) throws IOException {

        HazardData hazardData = new HazardData();
        hazardData.setHazardType(hazardType);
        hazardData.setPhoto(photo.getBytes());
        hazardData.setGps(gps);
        hazardData.setState(state);
        hazardData.setDates(LocalDateTime.parse(dates));

        hazardDataService.saveHazardData(hazardData);
        return "Hazard data added successfully!";
    }

    @GetMapping("/photo/{id}")
    public ResponseEntity<byte[]> getHazardPhoto(@PathVariable Long id) {
        HazardData hazardData = hazardDataService.findById(id);

        // Check if the HazardData exists
        if (hazardData == null || hazardData.getPhoto() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        byte[] imageBytes = hazardData.getPhoto();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);  // Adjust if your images are not JPEG

        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }

    @PutMapping("/update/{hid}")
    public String updateHazardData(@PathVariable Long hid, @RequestBody HazardData updatedHazardData) {
        HazardData hazardData = hazardDataService.findById(hid);
        if (hazardData != null) {
            hazardData.setHazardType(updatedHazardData.getHazardType());
            hazardData.setState(updatedHazardData.getState());
            hazardDataService.saveHazardData(hazardData);
            return "Hazard data updated successfully!";
        } else {
            return "Hazard data not found!";
        }
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/delete/{hid}")
    public String deleteHazard(@PathVariable Long hid) {
        hazardDataService.deleteHazardDataByHid(hid);
        return "Hazard data deleted successfully!";
    }
}
