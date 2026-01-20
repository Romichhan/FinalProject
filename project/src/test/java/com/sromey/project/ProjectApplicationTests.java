package com.sromey.project;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sromey.project.dto.ApiResponse;
import com.sromey.project.dto.CourseRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(TestcontainersConfiguration.class)
@AutoConfigureMockMvc
@SpringBootTest
class ProjectApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void testStudentEnrollment() throws Exception {
		CourseRequestDTO courseRequestDTO = new CourseRequestDTO();
		courseRequestDTO.setCourseCode("C01");
		courseRequestDTO.setCourseName("Computer applications");
		MvcResult mvcResult1 = mockMvc.perform(post("/course").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(courseRequestDTO))).andExpect(status().isCreated()).andReturn();
		String jsonResponse = mvcResult1.getResponse().getContentAsString();
		ApiResponse<String> apiResponse = objectMapper.readValue(jsonResponse,
				new TypeReference<ApiResponse<String>>() {
				});
		assertEquals("Course successfully added",apiResponse.getMessage());
		assertNull(apiResponse.getError(),"There should be no errors");

	}

}
