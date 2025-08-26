package Student.management.system.controller;

import Student.management.system.dto.addDetailsDTO;
import Student.management.system.dto.updateStudentDTO;
import Student.management.system.entity.student;
import Student.management.system.repository.studentRepository;
import Student.management.system.service.studentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
@Slf4j
@Tag(name = "Student API", description = "APIs for students to add and update their personal details")
public class studentController {

    @Autowired
    private studentService studSer;

    @Autowired
    private studentRepository studRep;


    //  Add student details

    @Operation(summary = "Add Student Details", description = "Add academic and personal details of the logged-in student")
    @PostMapping("/add-details")
    public ResponseEntity<?> addDetails(@RequestBody addDetailsDTO details) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            student st = studRep.findByname(userName);

            if (st == null) {
                return new ResponseEntity<>(userName + " not found", HttpStatus.NOT_FOUND);
            }

            // Academic details
            st.setCourse(details.getCourse());
            st.setDept(details.getDept());
            st.setYearOfStudy(details.getYearOfStudy());
            st.setRoll(details.getRoll());
            st.setOverallCgpa(details.getOverAllCgpa() != null && !details.getOverAllCgpa().isEmpty()
                    ? details.getOverAllCgpa() : st.getOverallCgpa());

            // Personal details
            st.setPhone(details.getPhone());
            st.setAddress(details.getAddress());
            st.setDob(details.getDob());
            st.setAge(details.getAge() != 0 ? details.getAge() : st.getAge());
            st.setBloodGr(details.getBloodGr());
            st.setGender(details.getGender());
            st.setGuardianName(details.getGuardianName());
            st.setGuardianPhone(details.getGuardianPhone() != 0 ? details.getGuardianPhone() : st.getGuardianPhone());

            studSer.saveStudent(st);
            return new ResponseEntity<>("Details added successfully", HttpStatus.OK);

        } catch (Exception e) {
            log.error("Error while adding student details: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to add details", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





    // Get Student Profile

    @Operation(summary = "Get My Profile", description = "Fetch the profile details of the logged-in student")
    @GetMapping("/profile")
    public ResponseEntity<?> getMyProfile() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();

            student s = studRep.findByname(userName);

            if (s == null) {
                return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(s, HttpStatus.OK);

        } catch (Exception e) {
            log.error("Error fetching student profile because {}", e.getMessage(), e);
            return new ResponseEntity<>("Error fetching profile", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    // Update student details

    @Operation(summary = "Update Student Details", description = "Update existing academic and personal details of the logged-in student")
    @PutMapping("/update")
    public ResponseEntity<?> updateDetails(@RequestBody updateStudentDTO updStud) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            student st = studRep.findByname(userName);

            if (st == null) {
                return new ResponseEntity<>(userName + " not found", HttpStatus.NOT_FOUND);
            }

            // Only update non-null and non-empty values
            st.setName(updStud.getName() != null && !updStud.getName().isEmpty() ? updStud.getName() : st.getName());
            st.setPassword(updStud.getPassword() != null && !updStud.getPassword().isEmpty() ? updStud.getPassword() : st.getPassword());
            st.setEmail(updStud.getEmail() != null && !updStud.getEmail().isEmpty() ? updStud.getEmail() : st.getEmail());
            st.setCourse(updStud.getCourse() != null && !updStud.getCourse().isEmpty() ? updStud.getCourse() : st.getCourse());
            st.setDept(updStud.getDept() != null && !updStud.getDept().isEmpty() ? updStud.getDept() : st.getDept());
            st.setYearOfStudy(updStud.getYearOfStudy() != null && !updStud.getYearOfStudy().isEmpty() ? updStud.getYearOfStudy() : st.getYearOfStudy());
            st.setRoll(updStud.getRoll() != null && !updStud.getRoll().isEmpty() ? updStud.getRoll() : st.getRoll());
            st.setOverallCgpa(updStud.getOverallCgpa() != null && !updStud.getOverallCgpa().isEmpty() ? updStud.getOverallCgpa() : st.getOverallCgpa());
            st.setPhone(updStud.getPhone() != null && !updStud.getPhone().isEmpty() ? updStud.getPhone() : st.getPhone());
            st.setAddress(updStud.getAddress() != null && !updStud.getAddress().isEmpty() ? updStud.getAddress() : st.getAddress());
            st.setDob(updStud.getDob() != null && !updStud.getDob().isEmpty() ? updStud.getDob() : st.getDob());
            st.setAge(updStud.getAge() != 0 ? updStud.getAge() : st.getAge());
            st.setBloodGr(updStud.getBloodGr() != null && !updStud.getBloodGr().isEmpty() ? updStud.getBloodGr() : st.getBloodGr());
            st.setGender(updStud.getGender() != null && !updStud.getGender().isEmpty() ? updStud.getGender() : st.getGender());
            st.setGuardianName(updStud.getGuardianName() != null && !updStud.getGuardianName().isEmpty() ? updStud.getGuardianName() : st.getGuardianName());
            st.setGuardianPhone(updStud.getGuardianPhone() != 0 ? updStud.getGuardianPhone() : st.getGuardianPhone());

            studSer.saveStudent(st);
            return new ResponseEntity<>("Details updated successfully", HttpStatus.OK);

        } catch (Exception e) {
            log.error("Error while updating student details: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to update details", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
