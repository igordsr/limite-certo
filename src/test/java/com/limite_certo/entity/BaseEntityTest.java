package com.limite_certo.entity;


import com.limite_certo.dto.BaseDTO;
import com.limite_certo.dto.ClienteBaseDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BaseEntityTest {

    private static class TestEntity extends BaseEntity<BaseDTO<?>> {
        @Override
        public BaseDTO<?> toDTO() {
            BaseDTO<?> dto = new ClienteBaseDTO() {
            };
            dto.setId(this.id);
            return dto;
        }
    }

    @Test
    public void testId_ShouldBeSetAndRetrieved() {
        TestEntity entity = new TestEntity();
        entity.setId(1L);

        assertEquals(1L, entity.getId());
    }

    @Test
    public void testConvertToDTO_ShouldReturnDTOInstance() {
        TestEntity entity = new TestEntity();
        entity.setId(1L);

        BaseDTO<?> dto = entity.toDTO();

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
    }
}
