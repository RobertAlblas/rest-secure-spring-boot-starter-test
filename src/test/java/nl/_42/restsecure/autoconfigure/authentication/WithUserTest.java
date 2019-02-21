package nl._42.restsecure.autoconfigure.authentication;

import nl._42.restsecure.autoconfigure.TestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class WithUserTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    public MockMvc initWebClient() {
        DefaultMockMvcBuilder webClientBuilder =
                MockMvcBuilders.webAppContextSetup(webApplicationContext)
                        .apply(springSecurity());

        return webClientBuilder
                .defaultRequest(get("/")
                        .contentType(APPLICATION_JSON)
                        .with(csrf()))
                .build();
    }

    @Test
    @WithUser("@userBuilder.user()")
    public void currentUser_shouldSucceed_whenLoggedIn() throws Exception {
        initWebClient()
                .perform(get("/authentication/current"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("authorities[0]").value("ROLE_ADMIN"))
                .andExpect(jsonPath("username").value("username"));
    }
}