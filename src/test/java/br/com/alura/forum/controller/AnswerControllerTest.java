package br.com.alura.forum.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.forum.config.TokenManager;
import br.com.alura.forum.dto.input.NewAnswerInputDto;
import br.com.alura.forum.model.User;
import br.com.alura.forum.model.topic.domain.Topic;
import br.com.alura.forum.repository.TopicRepository;
import br.com.alura.forum.repository.UserRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.util.UriTemplate;


import java.net.URI;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("dev")
public class AnswerControllerTest {

	private static final String ENDPOINT = "/api/topics/{topicId}/answers";

    private Long topicId;
    private String jwt;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private AuthenticationManager authManager;

    
	@Before
    public void setup() throws RuntimeException {

        String rawPassword = "123456";
        User user = new User("Aluno da Alura", "aluno@gmail.com",
                new BCryptPasswordEncoder().encode(rawPassword));
        User persistedUser = this.userRepository.save(user);

        Topic topic = new Topic("Descrição do Tópico", "Conteúdo do Tópico",
                persistedUser, null);
        Topic persistedTopic = this.topicRepository.save(topic);
        this.topicId = persistedTopic.getId();

        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), rawPassword));
        this.jwt = this.tokenManager.generateToken(authentication);
    }

	@Test
	public void newAnswerBadRequest() throws Exception {
		URI uri = new UriTemplate(ENDPOINT).expand(this.topicId);

        NewAnswerInputDto inputDto = new NewAnswerInputDto();
        inputDto.setContent("bad");

        MockHttpServletRequestBuilder request = post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + this.jwt)
                .content(new ObjectMapper().writeValueAsString(inputDto));

        this.mockMvc.perform(request)
                .andExpect(status().isBadRequest());

	}
	
	@Test
	public void newAnswerNotAuthorized() throws Exception {
		URI uri = new UriTemplate(ENDPOINT).expand(this.topicId);

        NewAnswerInputDto inputDto = new NewAnswerInputDto();
        inputDto.setContent("bad");

        MockHttpServletRequestBuilder request = post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(inputDto));

        this.mockMvc.perform(request)
                .andExpect(status().isUnauthorized());

	}
	
	
}
