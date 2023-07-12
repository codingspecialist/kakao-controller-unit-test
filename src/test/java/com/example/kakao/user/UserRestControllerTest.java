package com.example.kakao.user;

import com.example.kakao._core.security.SecurityConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

// GlobalExceptionHandler 와 UserRestController를 SpringContext에 등록합니다.

@Import(SecurityConfig.class)
@WebMvcTest(controllers = {UserRestController.class})
public class UserRestControllerTest {

    // 객체의 모든 메서드는 추상메서드로 구현됩니다. (가짜로 만들면)
    // 해당 객체는 SpringContext에 등록됩니다.
    @MockBean
    private UserService userService;

    @Spy
    private AuthenticationManager authenticationManager;

    // @WebMvcTest를 하면 MockMvc가 SpringContext에 등록되기 때문에 DI할 수 있습니다.
    @Autowired
    private MockMvc mvc;

    // @WebMvcTest를 하면 ObjectMapper가 SpringContext에 등록되기 때문에 DI할 수 있습니다.
    @Autowired
    private ObjectMapper om;

    @Test
    public void t1(){}

    @Test
    public void join_test() throws Exception {
        // given
        UserRequest.JoinDTO requestDTO = new UserRequest.JoinDTO();
        requestDTO.setEmail("ssarmango@nate.com");
        requestDTO.setPassword("meta1234!");
        requestDTO.setUsername("ssarmango");
        String requestBody = om.writeValueAsString(requestDTO);

        // when
        ResultActions result = mvc.perform(
                MockMvcRequestBuilders
                        .post("/join")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );
        String responseBody = result.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : "+responseBody);


        // then
    }
}
