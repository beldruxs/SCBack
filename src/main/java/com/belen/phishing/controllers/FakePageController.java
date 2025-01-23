package com.belen.phishing.controllers;

import com.belen.phishing.model.FakePageEntity;
import com.belen.phishing.repository.FakePageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fakepage")
public class FakePageController {

    @Autowired
    private FakePageRepository fakePageRepository;

    @PostMapping("/incrementVisits")
    public ResponseEntity<String> incrementFakePageVisits(@RequestParam String fakePageName) {
        FakePageEntity fakePage = fakePageRepository.findByNombre(fakePageName);
        if (fakePage == null) {
            return new ResponseEntity<>("Fake page not found", HttpStatus.NOT_FOUND);
        }

        // Increment the visitas field by 1
        if (fakePage.getVisitas() == null) {
            fakePage.setVisitas(1);
        } else {
            fakePage.setVisitas(fakePage.getVisitas() + 1);
        }
        fakePageRepository.save(fakePage);

        return new ResponseEntity<>("Visits incremented successfully", HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<FakePageEntity>> getAllFakePages() {
        List<FakePageEntity> fakePages = fakePageRepository.findAll();
        return new ResponseEntity<>(fakePages, HttpStatus.OK);
    }

    @GetMapping("/getByNombre")
    public ResponseEntity<FakePageEntity> getByNombre(@RequestParam String nombre) {
        FakePageEntity fakePage = fakePageRepository.findByNombre(nombre);
        if (fakePage == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(fakePage, HttpStatus.OK);
    }
}