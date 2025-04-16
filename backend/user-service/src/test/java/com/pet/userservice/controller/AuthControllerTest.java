package com.pet.userservice.controller;

import com.pet.userservice.dto.AuthResponse;
import com.pet.userservice.dto.RegisterRequest;
import com.pet.userservice.service.AuthService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AuthControllerTest {

    @Test
    public void testRegisterShouldReturnToken() {
        AuthService authService = Mockito.mock(AuthService.class);
        AuthController controller = new AuthController(authService);

        RegisterRequest request = new RegisterRequest("testuser", "testpass");

        AuthResponse mockResponse = new AuthResponse("mocked.jwt.token");
        Mockito.when(authService.register(Mockito.any())).thenReturn(mockResponse);

        AuthResponse response = controller.register(request);

        assertNotNull(response);
        assertEquals("mocked.jwt.token", response.getToken());
    }

}
