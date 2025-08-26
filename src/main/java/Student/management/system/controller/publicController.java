package Student.management.system.controller;

import Student.management.system.dto.loginDto;
import Student.management.system.dto.studentSignupDTO;
import Student.management.system.entity.admissionNumber;
import Student.management.system.entity.student;
import Student.management.system.repository.admissionNumberRepository;
import Student.management.system.repository.studentRepository;
import Student.management.system.service.emailService;
import Student.management.system.service.studentDetailsServiceImpl;
import Student.management.system.service.studentService;
import Student.management.system.utils.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@Slf4j
@Tag(name = "Public API", description = "Operations for student signup and login")
public class publicController {

    @Autowired
    private admissionNumberRepository admNumRep;

    @Autowired
    private studentRepository studRep;

    @Autowired
    private emailService emlSer;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    studentDetailsServiceImpl studentDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private studentService studServ;

    @Operation(summary = "Student Signup", description = "Registers a new student if admission number exists")
    @PostMapping("/signup")
    public ResponseEntity<?> signupNewAccount(@RequestBody studentSignupDTO newStudent) {
        try {
            if (admNumRep.existsByAdmissionNumberOfStudent(newStudent.getAdmissionNumber())
                    && !studRep.existsByAdmissionNumber(newStudent.getAdmissionNumber())) {

                student s = new student();
                s.setName(newStudent.getUserName());
                s.setPassword(newStudent.getPassword());
                s.setEmail(newStudent.getEmail());
                s.setAdmissionNumber(newStudent.getAdmissionNumber());
                studServ.addNewStudent(s);

                admissionNumber st = admNumRep.findByAdmissionNumberOfStudent(newStudent.getAdmissionNumber());
                st.setName(newStudent.getUserName());
                admNumRep.save(st);

                String to = newStudent.getEmail();
                String subject = "Account Created Successfully!";
                String body = "Dear Student " + newStudent.getUserName() + " \n\n"
                        + "Congratulations! Your account has been successfully registered.\n\n"
                        + "You can now login and fill in your important details.\n\n"
                        + "Best Regards,\n"
                        + "College Administration ";

                emlSer.sendMail(to, subject, body);
                return new ResponseEntity<>("Successfully registered", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(newStudent.getAdmissionNumber() + " you are not a student of our college", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Not registered because {}", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Student Login", description = "Authenticates student and generates JWT token")
    @PostMapping("/login")
    public ResponseEntity<?> loginAccount(@RequestBody loginDto loginUser) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginUser.getUserName(), loginUser.getPassword())
            );
            UserDetails studentDetails = studentDetailsService.loadUserByUsername(loginUser.getUserName());
            String token = jwtUtils.generateToken(studentDetails.getUsername());
            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Token not generated because {}", e);
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.NOT_FOUND);
        }
    }
}
