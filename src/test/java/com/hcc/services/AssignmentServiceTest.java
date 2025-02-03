package com.hcc.services;

import com.hcc.entities.Assignment;
import com.hcc.entities.User;
import com.hcc.repositories.AssignmentRepository;
import com.hcc.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class AssignmentServiceTest {

    @Mock
    private AssignmentRepository assignmentRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AssignmentService assignmentService;

    @Test
    public void testGetAssignmentsForUser() {
        User user = new User();
        user.setUsername("testuser");

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
        Assignment assignment1 = new Assignment();
        Assignment assignment2 = new Assignment();
        when(assignmentRepository.findByUser(user)).thenReturn(Arrays.asList(assignment1, assignment2));

        List<Assignment> assignments = assignmentService.getAssignmentsForUser("testuser");
        assertEquals(2, assignments.size());
        verify(userRepository, times(1)).findByUsername("testuser");
        verify(assignmentRepository, times(1)).findByUser(user);
    }

    @Test
    public void testGetAssignmentById() {
        Assignment assignment = new Assignment();
        // (Assuming you have an ID setter)
        // assignment.setId(1L);
        when(assignmentRepository.findById(1L)).thenReturn(Optional.of(assignment));
        Optional<Assignment> found = assignmentService.getAssignmentById(1L);
        assertTrue(found.isPresent());
        verify(assignmentRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateAssignment() {
        Assignment assignment = new Assignment();
        when(assignmentRepository.save(assignment)).thenReturn(assignment);
        Assignment created = assignmentService.createAssignment(assignment);
        assertNotNull(created);
        verify(assignmentRepository, times(1)).save(assignment);
    }
}
