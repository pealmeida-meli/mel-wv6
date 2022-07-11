package dh.meli.calculadora;

import dh.meli.util.CalculadoraCreate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculadoraTest {

    private Calculadora calculadora;

    @BeforeEach
    public void setup() {
        calculadora = CalculadoraCreate.createValid();
    }

    @Test
    @DisplayName("Valida parâmetros diferentes de zero")
    void dividir_ReturnValueDouble_WhenParamDifferentZero() {
        double expected = CalculadoraCreate.createValid().getN1() / CalculadoraCreate.createValid().getN2();

        double resultado = calculadora.dividir();

        assertEquals(expected, resultado);
    }

    @Test
    @DisplayName("Valida parâmetros com denominador zero")
    void dividir_ReturnZero_WhenParamEqualZero() {
        double expected = 0;
        calculadora = CalculadoraCreate.createWithZero();

        double resultado = calculadora.dividir();

        assertEquals(expected, resultado);
    }

}