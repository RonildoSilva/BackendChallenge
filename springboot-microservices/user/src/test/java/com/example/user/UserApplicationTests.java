package com.example.user;

import com.example.domain.model.User;
import com.example.domain.repository.UserRepository;
import com.example.user.endpoint.controller.UserController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@AutoConfigureJsonTesters
@SpringBootTest
@AutoConfigureMockMvc
public class UserApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;

    @Autowired
    private JacksonTester<User> jsonItem;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void canCreateNewItem() throws Exception {
        // given
        Long id = Long.valueOf(0);
        String name = "Test user";
        String email = "item " + id + "@mail.com";
        String login = "login  " + id;
        Date createdDate = new Date();
        String password = "pass_"+0;

        User user = new User(id, name, email, login, createdDate, password, false);

        given(userRepository.save(new User(id, name, email, login, createdDate,
                passwordEncoder.encode(password),false)))
                .willReturn(user);

        // when
        MockHttpServletResponse response = mvc.perform(
                post("/api/v1/admin/user/")
                        .contentType(MediaType.APPLICATION_JSON).
                        content(jsonItem.
                                write(new User(id, name, email, login, createdDate,
                                        passwordEncoder.encode(password),false)).
                                getJson())).
                andReturn()
                .getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonItem.write(user).getJson()
        );
    }
    @Test
    public void canRetrieveAnItemByIdWhenExists() throws Exception {

        // given
        Long id = Long.valueOf(0);
        String name = "Test user";
        String email = "user" + id + "@mail.com";
        String login = "login" + id;
        Date createdDate = new Date();
        String password = "pass_"+0;

        User user = new User(id, name, email, login, createdDate, password, false);
        userRepository.save(new User(id, name, email, login, createdDate,
                passwordEncoder.encode(password), false));

        given(userRepository.findById(id))
                .willReturn(java.util.Optional.of(user));

        // when
        MockHttpServletResponse response = mvc.perform(
                get("/api/v1/admin/user/"+id)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonItem.write(user).getJson()
        );
    }

}
