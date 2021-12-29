package com.db.jogo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.db.jogo.model.Admin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("Admin Service Teste")
class AdminServiceImplTest {

    @Mock
    private AdminServiceImpl adminServiceImpl;

    Admin admin = Admin.builder().senha("123").build();

    @DisplayName("Teste do SAVE do Service de um Admin")
    @Test
    void saveAdmin() {
       when(adminServiceImpl.saveAdmin(admin)).thenReturn(admin);
       assertEquals(admin, adminServiceImpl.saveAdmin(admin));
    }
}