package com.parachute.booking.plane;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class PlaneServiceCreateIntegrationTest {

    @Autowired
    private PlaneRepository planeRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private final String requestMappingUrl = "/planes";

    @BeforeEach
    void setup() {
        planeRepository.deleteAll();
    }

    private PlaneDto createNewPlaneDtoForTest() {

        return PlaneDto.builder()
                .planeNumber(15L)
                .planeModel("F15")
                .id(1L)
                .build();
    }

    @Test
    void createPlane_andReturnStatusCode200() throws Exception {
        //given
        PlaneDto planeDto = createNewPlaneDtoForTest();
        String requestbody = objectMapper.writeValueAsString(planeDto);
        MockHttpServletRequestBuilder request = post(requestMappingUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestbody);

        //when
        MvcResult result = mockMvc.perform(request).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        List<Plane> planes = planeRepository.findAll();
        assertThat(planes.size()).isEqualTo(1);
        assertThat(planes.get(0)).satisfies(plane -> {
            assertThat(plane.getId()).isEqualTo(1L);
            assertThat(plane.getPlaneModel()).isEqualTo("F15");
            assertThat(plane.getPlaneNumber()).isEqualTo(15L);
        });
    }

}
