package Student.management.system.controller;

import Student.management.system.entity.admissionNumber;
import Student.management.system.entity.student;
import Student.management.system.repository.admissionNumberRepository;
import Student.management.system.service.studentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
@Slf4j
@Tag(name = "Admin API", description = "Operations for Admin like generating admission number and viewing students")
public class AdminController {

    @Autowired
    private admissionNumberRepository AdmRep;

    @Autowired
    private studentService studSer;

    @Operation(summary = "Generate Admission Number", description = "Generates a unique admission number for a student")
    @GetMapping("/generate-admission-number")
    public ResponseEntity<?> generateNumber() {
        try {
            int year = Year.now().getValue();
            String uniqueID = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            String number = "ADM" + year + uniqueID;

            admissionNumber admNum = new admissionNumber();
            admNum.setAdmissionNumberOfStudent(number);
            AdmRep.save(admNum);

            return new ResponseEntity<>("Generated new admission number: " + number, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Admission Number not generated because {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to generate admission number: " + e.getMessage());
        }
    }

    @Operation(summary = "Get All Students", description = "Fetches details of all registered students")
    @GetMapping("/get-AllStudent")
    public ResponseEntity<?> getAllStudent() {
        try {
            List<student> allStudentData = studSer.getAllStudentData();
            return new ResponseEntity<>(allStudentData, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Not returning all student data because {}", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(
            summary = "Delete Student by Admission Number",
            description = "Deletes a student record from the system using the admission number"
    )
    @Transactional
    @DeleteMapping("/delete/{admissionNumber}")
    public ResponseEntity<?> deleteStudent(@PathVariable String admissionNumber) {
        boolean deleted = studSer.deleteStudentByAdmissionNumber(admissionNumber);
        if (deleted) {
            return ResponseEntity.ok("Student with Admission Number " + admissionNumber + " deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Student with Admission Number " + admissionNumber + " not found!");
        }
    }



    @Operation(
            summary = "Find Student by Roll Number",
            description = "Fetches the student details using the provided roll number"
    )
    @GetMapping("/find-by-roll/{rollNumber}")
    public ResponseEntity<?> getStudentByRoll(@PathVariable String rollNumber) {
        student st = studSer.findByRoll(rollNumber);

        if (st != null) {
            return new ResponseEntity<>(st,HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Student with Roll Number " + rollNumber + " not found!");
        }
    }



}
