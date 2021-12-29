package com.db.jogo.service;

import com.db.jogo.model.Admin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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