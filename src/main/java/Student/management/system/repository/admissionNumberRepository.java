package Student.management.system.repository;

import Student.management.system.entity.admissionNumber;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface admissionNumberRepository extends MongoRepository<admissionNumber, ObjectId> {
    boolean existsByAdmissionNumberOfStudent(String admissionNum);
    admissionNumber findByAdmissionNumberOfStudent(String admissionNum);
}
