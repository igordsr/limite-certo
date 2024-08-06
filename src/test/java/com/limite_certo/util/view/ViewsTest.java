package com.limite_certo.util.view;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ViewsTest {
    @Test
    public void testCompletoClassExists() {
        assertNotNull(Views.Completo.class);
    }

    @Test
    public void testParcialClassExists() {
        assertNotNull(Views.Parcial.class);
    }

    @Test
    public void testIdViewClassExists() {
        assertNotNull(Views.IdView.class);
    }

    @Test
    public void testNoneClassExists() {
        assertNotNull(Views.None.class);
    }
}