package com.hcc.services;

import com.hcc.entities.Assignment;
import com.hcc.entities.User;
import com.hcc.repositories.AssignmentRepository;
import com.hcc.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Assignment> getAssignmentsForUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return assignmentRepository.findByUser(user);
    }

    public Optional<Assignment> getAssignmentById(Long id) {
        return assignmentRepository.findById(id);
    }

    public Assignment createAssignment(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }

    public Assignment updateAssignment(Long id, Assignment updatedAssignment) {
        return assignmentRepository.findById(id).map(existing -> {
            existing.setStatus(updatedAssignment.getStatus());
            existing.setNumber(updatedAssignment.getNumber());
            existing.setGithubUrl(updatedAssignment.getGithubUrl());
            existing.setBranch(updatedAssignment.getBranch());
            existing.setReviewVideoUrl(updatedAssignment.getReviewVideoUrl());
            existing.setUser(updatedAssignment.getUser());
            existing.setCodeReviewer(updatedAssignment.getCodeReviewer());
            return assignmentRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Assignment not found"));
    }
}
