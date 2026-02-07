package org.thingsboard.server.controller;

import org.junit.Test;
import org.thingsboard.server.dao.service.DaoSqlTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DaoSqlTest
public class HelloControllerIntegrationTest extends AbstractControllerTest {

    @Test
    public void testGetHello() throws Exception {
        String response = doGet("/api/bonjour")
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertThat(response).isIn("Bon matin", "Bonjour", "Bonsoir");
    }

}
