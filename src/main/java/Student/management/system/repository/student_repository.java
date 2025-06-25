package Student.management.system.repository;

import Student.management.system.student_details.details;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface student_repository extends MongoRepository<details, ObjectId> {
    //  Custom query to find student by Roll Number
    Optional<details> findByRoll(String roll);


}

