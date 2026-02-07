package org.thingsboard.server.controller;

import org.junit.Test;
import org.thingsboard.server.dao.service.DaoSqlTest;

import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

@DaoSqlTest
public class HelloControllerTest extends AbstractControllerTest {

    // Unit tests for greetings with boundary values and nominal values
    @Test
    public void testGetHelloMorningEarlyLimit() {
        String greeting = HelloController.getGreeting(LocalTime.of(0, 0));
        assertThat(greeting).isEqualTo("Bon matin");
    }
    @Test
    public void testGetHelloMorningLateLimit() {
        String greeting = HelloController.getGreeting(LocalTime.of(10, 59));
        assertThat(greeting).isEqualTo("Bon matin");
    }
    @Test
    public void testGetHelloMorningNominal() {
        String greeting = HelloController.getGreeting(LocalTime.of(6, 0));
        assertThat(greeting).isEqualTo("Bon matin");
    }

    @Test
    public void testGetHelloDayEarlyLimit() {
        String greeting = HelloController.getGreeting(LocalTime.of(11, 0));
        assertThat(greeting).isEqualTo("Bonjour");
    }
    @Test
    public void testGetHelloDayLateLimit() {
        String greeting = HelloController.getGreeting(LocalTime.of(17, 59));
        assertThat(greeting).isEqualTo("Bonjour");
    }
    @Test
    public void testGetHelloDayNominal() {
        String greeting = HelloController.getGreeting(LocalTime.of(13, 0));
        assertThat(greeting).isEqualTo("Bonjour");
    }

    @Test
    public void testGetHelloNightEarlyLimit() {
        String greeting = HelloController.getGreeting(LocalTime.of(18, 0));
        assertThat(greeting).isEqualTo("Bonsoir");
    }
    @Test
    public void testGetHelloNightLateLimit() {
        String greeting = HelloController.getGreeting(LocalTime.of(23, 59));
        assertThat(greeting).isEqualTo("Bonsoir");
    }
    @Test
    public void testGetHelloNightNominal() {
        String greeting = HelloController.getGreeting(LocalTime.of(22, 0));
        assertThat(greeting).isEqualTo("Bonsoir");
    }

    // Integration test to check the endpoint reacts to GET requests
    @Test
    public void testGetHello() throws Exception {
        String response = doGet("/api/bonjour").andReturn().getResponse().getContentAsString();
        assertThat(response).isIn("Bon matin", "Bonjour", "Bonsoir");
    }
    
}
