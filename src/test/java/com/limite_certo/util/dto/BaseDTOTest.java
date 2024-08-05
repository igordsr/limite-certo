package com.limite_certo.util.dto;

import com.limite_certo.dto.BaseDTO;
import com.limite_certo.entity.BaseEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class BaseDTOTest {

    private static class TestDTO extends BaseDTO<TestEntity> {
        @Override
        public Long getId() {
            return id;
        }

        @Override
        public TestEntity toEntity() {
            TestEntity entity = new TestEntity();
            entity.setId(this.id);
            return entity;
        }
    }

    private static class TestEntity extends BaseEntity<TestDTO> {
        @Override
        public TestDTO toDTO() {
            TestDTO dto = new TestDTO();
            dto.setId(this.id);
            return dto;
        }
    }

    @Test
    public void testId_ShouldBeSetAndRetrieved() {
        TestDTO dto = new TestDTO();
        dto.setId(1L);

        assertEquals(1L, dto.getId());
    }

    @Test
    public void testId_ShouldBeNullByDefault() {
        TestDTO dto = new TestDTO();

        assertNull(dto.getId());
    }
}
