package com.parachute.booking.plane;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class PlaneServiceSearchIntegrationTest {

    @Autowired
    PlaneRepository planeRepository;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    private final String requestMappingUrl = "/planes";

    private Plane createNewPlaneForTestA() {
        return Plane.builder()
                .planeNumber(1111L)
                .planeModel("F14")
                .build();
    }

    private Plane createNewPlaneForTestB() {
        return Plane.builder()
                .planeNumber(2222L)
                .planeModel("F15")
                .build();
    }

    private Plane createNewPlaneForTestC() {
        return Plane.builder()
                .planeNumber(3333L)
                .planeModel("F16")
                .build();
    }

    @BeforeEach
    void setup() {
        planeRepository.deleteAll();
    }

    @Test
    void getAllPlanes_andReturnsStatusCode200() throws Exception {
        //given
        planeRepository.save(createNewPlaneForTestA());
        planeRepository.save(createNewPlaneForTestB());
        planeRepository.save(createNewPlaneForTestC());

        MockHttpServletRequestBuilder request = get(requestMappingUrl);

        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        List<PlaneDto> responseBody = objectMapper.readValue(response.getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {
        });
        assertThat(responseBody).hasSize(3);
    }

    @Test
    void getAllPlanes_andReturnsStatusCode200_noPlanes() throws Exception {
        //given
        MockHttpServletRequestBuilder request = get(requestMappingUrl);

        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        List<PlaneDto> responseBody = objectMapper.readValue(response.getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {
        });
        assertThat(responseBody).hasSize(0);
    }

    @Test
    void findPlaneById_andReturnsStatusCode200() throws Exception {
        //given
        Plane save = planeRepository.save(createNewPlaneForTestA());
        planeRepository.save(createNewPlaneForTestB());
        planeRepository.save(createNewPlaneForTestC());

        Long id = save.getId();
        MockHttpServletRequestBuilder request = get(requestMappingUrl + "/{id}", id);

        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        PlaneDto respondeBody = objectMapper.readValue(response.getContentAsString(StandardCharsets.UTF_8), PlaneDto.class);
        assertThat(respondeBody.getId()).isEqualTo(id);
        assertThat(respondeBody.getPlaneModel()).isEqualTo("F14");
        assertThat(respondeBody.getPlaneNumber()).isEqualTo(1111L);
    }

    @Test
    void findPlaneById_andReturnsStatusCode400_planeDoesntExists() throws Exception {
        // given
        MockHttpServletRequestBuilder request = get(requestMappingUrl + "/{id}", 100);

        // when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void findPlaneById_andReturnsStatusCode400_idIsNegative() throws Exception {
        // given
        MockHttpServletRequestBuilder request = get(requestMappingUrl + "/{id}", -1);

        // when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void findPlaneByPlaneNumber_andReturnsStatusCode200() throws Exception {
        //given
        Plane save = planeRepository.save(createNewPlaneForTestA());
        planeRepository.save(createNewPlaneForTestB());
        planeRepository.save(createNewPlaneForTestC());

        Long number = save.getPlaneNumber();
        Long id = save.getId();
        MockHttpServletRequestBuilder request = get(requestMappingUrl + "/number/{number}", number);

        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        PlaneDto respondeBody = objectMapper.readValue(response.getContentAsString(StandardCharsets.UTF_8), PlaneDto.class);
        assertThat(respondeBody.getId()).isEqualTo(id);
        assertThat(respondeBody.getPlaneModel()).isEqualTo("F14");
        assertThat(respondeBody.getPlaneNumber()).isEqualTo(number);
    }

    @Test
    void findPlaneByPlaneNumber_andReturnsStatusCode400() throws Exception {
        // given
        planeRepository.save(createNewPlaneForTestA());
        planeRepository.save(createNewPlaneForTestB());
        planeRepository.save(createNewPlaneForTestC());
        MockHttpServletRequestBuilder request = get(requestMappingUrl + "/number/{number}", 100L);

        // when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void findPlaneByPlaneModel_andReturnsStatusCode200() throws Exception {
        //given
        Plane save = planeRepository.save(createNewPlaneForTestA());
        planeRepository.save(createNewPlaneForTestB());
        planeRepository.save(createNewPlaneForTestC());

        String model = save.getPlaneModel();
        Long id = save.getId();
        MockHttpServletRequestBuilder request = get(requestMappingUrl + "/model/{model}", model);

        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        PlaneDto respondeBody = objectMapper.readValue(response.getContentAsString(StandardCharsets.UTF_8), PlaneDto.class);
        assertThat(respondeBody.getId()).isEqualTo(id);
        //TODO this is very interesting - try change id to 1L
        assertThat(respondeBody.getPlaneModel()).isEqualTo(model);
        assertThat(respondeBody.getPlaneNumber()).isEqualTo(1111L);
    }

    @Test
    void findPlaneByPlaneModel_andReturnsStatusCode400() throws Exception {
        // given
        planeRepository.save(createNewPlaneForTestA());
        planeRepository.save(createNewPlaneForTestB());
        planeRepository.save(createNewPlaneForTestC());
        MockHttpServletRequestBuilder request = get(requestMappingUrl + "/model/{model}", "F22");

        // when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

}
