package com.magneto.mutantapi.controller;

import com.magneto.mutantapi.model.AdnRequest;
import com.magneto.mutantapi.service.MutantDetector;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mutant")
public class MutantController {

    private final MutantDetector detector = new MutantDetector();

    @PostMapping
    public ResponseEntity<String> checkMutant(@RequestBody AdnRequest request) {
        try {
            String[] adnArray = request.getDna().toArray(new String[0]);

            boolean esMutante = detector.isMutant(adnArray);
            if (esMutante) {
                return ResponseEntity.status(HttpStatus.OK).body("Es mutante");
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No es mutante");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ADN inválido: " + e.getMessage());
        }
    }
}
