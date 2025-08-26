package Student.management.system.repository;

import Student.management.system.entity.student;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Optional;
@EnableMongoRepositories
public interface studentRepository extends MongoRepository<student, ObjectId> {

    Optional<student> findByRoll(String roll);
    student findByname(String name);
    boolean existsByAdmissionNumber(String number);
    student findByAdmissionNumber(String admissionNumber);



}

