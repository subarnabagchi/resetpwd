package com.app.resetpwd;

import com.app.resetpwd.model.User;
import com.app.resetpwd.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ResetPasswordControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Test
    void testShowPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().is(200))
                .andExpect(view().name("resetpasswordform"));
    }

    @Test
    void testResetPassword() throws Exception {
        when(userService.findByEmailId(anyString())).thenReturn(java.util.Optional.of(new User()));
        mockMvc.perform(MockMvcRequestBuilders.post("/resetpwd")
                .param("email", "subarna.bagchi@gmail.com")
                .param("password", "test5555$1")).andExpect(status().is(200))
                .andExpect(view().name("resetPasswordSuccess"));

    }

    @Test
    void testResetPasswordWithInvalidEmail() throws Exception {
        when(userService.findByEmailId(anyString())).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.post("/resetpwd")
                .param("email", "test@gmail.com")
                .param("password", "test5555$1")).andExpect(status().is(200))
                .andExpect(view().name("resetpasswordform"))
                .andExpect(model().attributeExists("error"));

    }

}
