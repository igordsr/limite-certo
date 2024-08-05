package com.limite_certo.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpringTimezoneConfigTest {

    @BeforeEach
    public void setUp() {
        // Configura o fuso horário padrão para um valor conhecido antes de testar
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    @Test
    public void testTimezoneConfig() {
        // Instancia a configuração para garantir que o método @PostConstruct seja chamado
        new SpringTimezoneConfig().timezoneConfig();

        // Verifica se o fuso horário padrão foi alterado para "America/Sao_Paulo"
        TimeZone timeZone = TimeZone.getDefault();
        assertEquals("America/Sao_Paulo", timeZone.getID(), "O fuso horário padrão não está correto.");
    }
}
