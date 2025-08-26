package Student.management.system.service;

import Student.management.system.entity.admissionNumber;
import Student.management.system.repository.admissionNumberRepository;
import Student.management.system.repository.studentRepository;
import Student.management.system.entity.student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Slf4j
public class studentService {
    @Autowired
    private studentRepository SR;

    @Autowired
    private admissionNumberRepository admNumRep;

private static final PasswordEncoder passEnc = new BCryptPasswordEncoder();

    // add new user
    public void addNewStudent(student s){
        try{
            s.setPassword(passEnc.encode(s.getPassword()));
            s.setRole("USER");
            SR.save(s);
        }catch(Exception e){
         log.error("user not save because "+e);
        }

    }

public void saveStudent(student s){
        SR.save(s);
}


public List<student> getAllStudentData(){
    return SR.findAll();
}


    public boolean deleteStudentByAdmissionNumber(String admissionNumber) {
        student st = SR.findByAdmissionNumber(admissionNumber); // fetch student
        admissionNumber AdmissionNumberOfStudent = admNumRep.findByAdmissionNumberOfStudent(admissionNumber); // fetch admission entity

        if (st != null && AdmissionNumberOfStudent != null) {
            SR.delete(st); // delete student
            admNumRep.delete(AdmissionNumberOfStudent); // delete admission number
            return true;
        }
        return false;
    }


    public student findByRoll(String rollNumber){
        student st = SR.findByRoll(rollNumber).orElse(null);
        return  st;
    }

}
