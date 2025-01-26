package com.belen.phishing.service;

import com.belen.phishing.config.ConstantesUtil;
import com.belen.phishing.dto.PhishingEntrada;
import com.belen.phishing.dto.PhishingRequest;
import com.belen.phishing.dto.UserDataRequest;
import com.belen.phishing.model.FakeAttemptEntity;
import com.belen.phishing.model.PlantillaEntity;
import com.belen.phishing.model.UserEntity;
import com.belen.phishing.repository.FakeAttemptRepository;
import com.belen.phishing.repository.PlantillaRepository;
import com.belen.phishing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PhishingService {

    private final PlantillaRepository plantillaRepository;
    private final EmailService emailService;
    private final FakeAttemptRepository fakeAttemptRepository;
    private final UserRepository userRepository;

    @Autowired
    public PhishingService(PlantillaRepository plantillaRepository, EmailService emailService, FakeAttemptRepository fakeAttemptRepository, UserRepository userRepository) {
        this.plantillaRepository = plantillaRepository;
        this.emailService = emailService;
        this.fakeAttemptRepository = fakeAttemptRepository;
        this.userRepository = userRepository;
    }

    public void sendPhishingEmail(PhishingRequest phishingRequest) throws MessagingException {
        // Fetch the HTML template
        PlantillaEntity plantilla = plantillaRepository.findPlantillaByCodPlantilla(phishingRequest.getPlatform());
        // Replace the placeholder with the specified URL
        String personalizedHtml = plantilla.getHtml().replace("{{ mi_variable }}", ConstantesUtil.BASE_URL + "/login/u/" + phishingRequest.getPlatform().replace("-pick", "") + "?username=" + phishingRequest.getUsername());
        String subject = plantilla.getSubject().replace("{{ username }}", phishingRequest.getUsername());
        // Send the email
        emailService.sendEmail2(phishingRequest.getMail(), subject, personalizedHtml);

        Optional<UserEntity> user = userRepository.findByUsername(phishingRequest.getUsername());

        // Create a new FakeAttemptEntity record
        FakeAttemptEntity fakeAttempt = new FakeAttemptEntity();
        fakeAttempt.setLEnviado(true);
        fakeAttempt.setLEntrado(false);
        fakeAttempt.setLDatosIntroducidos(false);
        fakeAttempt.setPlantilla(plantilla);
        if(user.isPresent()) {
            fakeAttempt.setUser(user.get());
        } else {
            fakeAttempt.setUser(null);
        }

        // Save the record
        fakeAttemptRepository.save(fakeAttempt);
    }

    public void phishingEntrada(PhishingEntrada phishingEntrada) {
        Optional<FakeAttemptEntity> fakeAttempt = fakeAttemptRepository.findByUserUsername(phishingEntrada.getUsername());
        if(fakeAttempt.isPresent()) {
            FakeAttemptEntity fakeAttemptEntity = fakeAttempt.get();
            fakeAttemptEntity.setLEntrado(true);
            fakeAttemptRepository.save(fakeAttemptEntity);
        }
        Optional<UserEntity> user = userRepository.findByUsername(phishingEntrada.getUsername());
        if(user.isPresent()) {
            UserEntity userEntity = user.get();
            userEntity.setPuntos(userEntity.getPuntos() - 2);
            userRepository.save(userEntity);
        }
    }

    @Transactional
    public void phishingPick(UserDataRequest userDataRequest) throws MessagingException {
        Integer puntuacion = 0;
        Optional<UserEntity> user = userRepository.findByUsername(userDataRequest.getUsername());
        if(user.isPresent()) {
            UserEntity userEntity = user.get();
            userEntity.setPuntos(userEntity.getPuntos() - 3);
            puntuacion = userEntity.getPuntos();
            userRepository.save(userEntity);
        }

        String htmlTemplate = plantillaRepository.findHtmlByCodPlantilla(userDataRequest.getPlatform().concat("-pickeado"));
        // Replace the placeholder with the specified URL
        String personalizedHtml = htmlTemplate
                .replace("{{nombre}}", userDataRequest.getUsername())
                .replace("{{username}}", userDataRequest.getEmailOrPhone())
                .replace("{{password}}", obfuscatePassword(userDataRequest.getPassword()))
                .replace("{{puntos}}", puntuacion.toString());
        String subject = userDataRequest.getUsername() + ", has sido victima de phishing!";        // Send the email
        emailService.sendEmail2(user.get().getMail(), subject, personalizedHtml);

        // Delete all FakeAttemptEntity records for the user
        fakeAttemptRepository.deleteByUserUsername(userDataRequest.getUsername());

    }

    private String obfuscatePassword(String password) {
        int length = password.length();
        int halfLength = length / 2;
        StringBuilder obfuscatedPassword = new StringBuilder();

        for (int i = 0; i < halfLength; i++) {
            obfuscatedPassword.append('*');
        }
        obfuscatedPassword.append(password.substring(halfLength));

        return obfuscatedPassword.toString();
    }
}