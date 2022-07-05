package com.dh.meli.spring02.handler;

import com.dh.meli.spring02.exception.NotFoundException;
import com.dh.meli.spring02.exception.NotFoundExceptionDetalhes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class NotFoundExHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<NotFoundExceptionDetalhes> handlerNotFoundEx(NotFoundException ex) {
        return new ResponseEntity<>(
                NotFoundExceptionDetalhes.builder()
                        .titulo("Objeto não encontrado")
                        .status(HttpStatus.NOT_FOUND.value())
                        .mensagem(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build(),
                HttpStatus.NOT_FOUND);
    }
}
