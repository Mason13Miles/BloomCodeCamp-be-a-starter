package com.hcc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcc.entities.Assignment;
import com.hcc.services.AssignmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AssignmentController.class)
public class AssignmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssignmentService assignmentService;

    @Test
    @WithMockUser(username = "testuser")
    public void testGetAssignments() throws Exception {
        Assignment assignment = new Assignment();
        when(assignmentService.getAssignmentsForUser("testuser"))
                .thenReturn(Arrays.asList(assignment));

        mockMvc.perform(get("/api/assignments"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "testuser")
    public void testCreateAssignment() throws Exception {
        Assignment assignment = new Assignment();
        // (Set fields as needed)
        when(assignmentService.createAssignment(assignment)).thenReturn(assignment);

        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(post("/api/assignments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(assignment)))
                .andExpect(status().isOk());
    }
}
