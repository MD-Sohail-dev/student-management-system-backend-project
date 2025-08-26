package Student.management.system.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "AdmissionNumberOfAllStudents")
@Data
public class admissionNumber {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    private String admissionNumberOfStudent;
    private String name;
}
