package Student.management.system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class addDetailsDTO {

    @NonNull
    private String course;
    @NonNull
    private String dept;
    @NonNull
    private String yearOfStudy;
    @NonNull
    private String roll;
    private String overAllCgpa;

    @NonNull
    private String phone;
    @NonNull
    private String address;

    @NonNull
    private String dob;
    @NonNull
    private int age;
    @NonNull
    private String bloodGr;
    @NonNull
    private String gender;

    @NonNull
    private String guardianName;
    @NonNull
    private long guardianPhone;


}
