package Student.management.system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class updateStudentDTO {
    private String name;
    private String password;
    private String email;

    private String course;
    private String dept;
    private String yearOfStudy;
    private String roll;

    private String overallCgpa;
    private String phone;
    private String address;
    private String dob;
    private int age;
    private String bloodGr;
    private String gender;

    private String guardianName;
    private long guardianPhone;
}
