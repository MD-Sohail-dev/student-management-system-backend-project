package Student.management.system.service;

import Student.management.system.repository.student_repository;
import Student.management.system.student_details.details;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.yaml.snakeyaml.events.Event;

import java.util.List;
import java.util.Optional;

@Component
public class student_service {
    @Autowired
    private student_repository SR;

    // adding data in data base
    public void addData(details D){
         SR.save(D);
    }

    // get data
    public List<details> getData(){
        return SR.findAll();
    }

    // find by roll
   public Optional<details> findByRollNumber(String roll){
        return  SR.findByRoll(roll);
   }

   // find by id
    public Optional<details> findById(ObjectId id){
        return SR.findById(id);
    }

    // delete students by id
    public boolean deleteById(ObjectId id){
        SR.deleteById(id);
        return  true;
    }

}
