package Student.management.system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "student_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class student {
    //  This class represents the student document stored in MongoDB


    @Id
    private ObjectId id;
    @NonNull
    private String name;
    @NonNull
    private String password;
    @NonNull
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

    @Indexed(unique = true)
    @NonNull
    private String admissionNumber;
    @NonNull
    private String role;


}
