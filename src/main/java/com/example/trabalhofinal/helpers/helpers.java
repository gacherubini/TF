package com.example.trabalhofinal.helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.example.trabalhofinal.models.Assinatura;
import org.apache.tomcat.jni.Local;

import java.util.Optional;
import java.time.format.DateTimeParseException;

public class helpers {


    public static LocalDate isFirstAssinatura(Optional<Assinatura> existingAssinatura, Assinatura assinatura) {
        if (existingAssinatura.isEmpty()) {
            assinatura.setFimVigencia(LocalDate.now().plusDays(7));
        } else {
            assinatura.setFimVigencia(LocalDate.now());
        }
        return assinatura.getFimVigencia();
    }
}




