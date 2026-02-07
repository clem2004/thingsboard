/**
 * Copyright © 2016-2026 The Thingsboard Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
