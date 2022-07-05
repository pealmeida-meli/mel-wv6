package com.dh.meli.spring02.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
public class NotFoundExceptionDetalhes {
    private String titulo;
    private int status;
    private String mensagem;
    private LocalDateTime timestamp;
}
