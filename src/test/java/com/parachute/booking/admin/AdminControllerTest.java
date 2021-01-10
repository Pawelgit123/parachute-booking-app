package com.parachute.booking.admin;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
class AdminControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void testValidation() throws Exception {
//    final MvcResult mvcResult = mockMvc.perform(post("./dsadasewq").contentType(MediaType.APPLICATION_JSON)
//        .content())
//        .andExpect(status().isBadRequest())
//        .andReturn();
//
//    mvcResult.getResponse().getContentAsString();
  }
}