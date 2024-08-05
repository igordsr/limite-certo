//package com.limite_certo.service;
//
//import com.limite_certo.controller.exception.modal.CustomException;
//import com.limite_certo.dto.BaseDTO;
//import com.limite_certo.entity.BaseEntity;
//import com.limite_certo.repository.BaseRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//class BaseServiceTest {
//
//    @Mock
//    private BaseRepository<BaseEntity<?>, Long> repository;
//
//    @InjectMocks
//    private BaseService<BaseEntity<?>, BaseDTO<?>> baseService = new BaseService<BaseEntity<?>, BaseDTO<?>>(repository) {
//        @Override
//        protected BaseEntity convertToEntity(BaseDTO dto) {
//            return mock(BaseEntity.class);
//        }
//
//        @Override
//        protected BaseDTO convertToEntity(BaseEntity entity) {
//            return mock(BaseDTO.class);
//        }
//
//        @Override
//        protected void executarValidacoesAntesDeCadastrar(BaseDTO dto) {
//        }
//    };
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testCadastrar() {
//        BaseDTO dto = mock(BaseDTO.class);
//        BaseEntity entity = mock(BaseEntity.class);
//
//        doNothing().when(baseService).executarValidacoesAntesDeCadastrar(dto);
//        when(baseService.convertToEntity(dto)).thenReturn(entity);
//        when(repository.save(any(BaseEntity.class))).thenReturn(entity);
//        when(baseService.convertToEntity(entity)).thenReturn(dto);
//
//        BaseDTO result = baseService.cadastrar(dto);
//
//        assertEquals(dto, result);
//        verify(baseService).executarValidacoesAntesDeCadastrar(dto);
//        verify(baseService).convertToEntity(dto);
//        verify(repository).save(entity);
//        verify(baseService).convertToEntity(entity);
//    }
//
//    @Test
//    void testAtualizarEntidade() {
//        Long id = 1L;
//        BaseDTO dto = mock(BaseDTO.class);
//        BaseEntity entity = mock(BaseEntity.class);
//        BaseEntity savedEntity = mock(BaseEntity.class);
//
//        when(baseService.convertToEntity(dto)).thenReturn(entity);
//        when(repository.findById(id)).thenReturn(Optional.of(savedEntity));
//        when(repository.save(any(BaseEntity.class))).thenReturn(savedEntity);
//        when(baseService.convertToEntity(savedEntity)).thenReturn(dto);
//
//        BaseDTO result = baseService.atualizarEntidade(id, dto);
//
//        assertEquals(dto, result);
//        verify(repository).findById(id);
//        verify(repository).save(savedEntity);
//        verify(baseService).convertToEntity(savedEntity);
//    }
//
//    @Test
//    void testApagarPorId() {
//        Long id = 1L;
//
//        doNothing().when(repository).deleteById(id);
//
//        baseService.apagarPorId(id);
//
//        verify(repository).deleteById(id);
//    }
//
//    @Test
//    void testGetEntidade() {
//        Long id = 1L;
//        BaseEntity entity = mock(BaseEntity.class);
//
//        when(repository.findById(id)).thenReturn(Optional.of(entity));
//
//        BaseEntity result = baseService.getEntidade(id);
//
//        assertEquals(entity, result);
//        verify(repository).findById(id);
//    }
//
//    @Test
//    void testGetEntidadeNotFound() {
//        Long id = 1L;
//
//        when(repository.findById(id)).thenReturn(Optional.empty());
//
//        CustomException exception = assertThrows(CustomException.class, () -> baseService.getEntidade(id));
//
//        assertEquals("Registro não localizado.", exception.getMessage());
//        verify(repository).findById(id);
//    }
//}
