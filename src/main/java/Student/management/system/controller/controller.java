package Student.management.system.controller;

import Student.management.system.service.student_service;
import Student.management.system.student_details.details;
import com.sun.org.apache.xpath.internal.objects.XString;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
public class controller {
@Autowired
private student_service ss;

   @PostMapping
    public details saveData(@RequestBody details SD){
        ss.addData(SD);
        return SD;
   }

    // Fetch all student records
   @GetMapping
    public List<details> showData(){
       return  ss.getData();
   }

    //  Find student by Roll Number
   @GetMapping("findByRoll/{roll}")
    public details find_by_roll(@PathVariable String roll){
       return ss.findByRollNumber(roll).orElse(null);
   }

    //  Find student by ObjectId
    @GetMapping("findById/{id}")
    public details find_by_id(@PathVariable ObjectId id){
       return ss.findById(id).orElse(null);
    }

    // Delete student by ObjectId
    @DeleteMapping("deleteById/{id}")
    public boolean delete_by_id(@PathVariable ObjectId id){
       ss.deleteById(id);
       return  true;
    }



    /* Update student data using roll number.
 This method checks if a student exists by roll number.
 If found, it updates only the fields provided in the request (non-null and non-empty).
 Unchanged fields remain the same.*/
    @PutMapping("updateByRoll/{roll}")
    public details update(@PathVariable String roll,@RequestBody details newData){
        // Find the existing student data using roll number
       details old_Data = ss.findByRollNumber(roll).orElse(null);

       if(old_Data != null){
        //  Update name if new name is not null or empty
        old_Data.setName(newData.getName() != null && !newData.getName().equals("") ? newData.getName() : old_Data.getName());

           //  Update age if new age is different
        old_Data.setAge(newData.getAge() == old_Data.getAge() ? old_Data.getAge() : newData.getAge());

           // Update department if new department is not null or empty
        old_Data.setDept(newData.getDept() != null && !newData.getDept().equals("") ? newData.getDept() : old_Data.getDept());

           //  Update email if new email is not null or empty
        old_Data.setEmail(newData.getEmail() != null && !newData.getEmail().equals("") ? newData.getEmail() : old_Data.getEmail());

           //  Update phone if new phone is not null or empty
        old_Data.setPhone(newData.getPhone() != null && !newData.getPhone().equals("") ? newData.getPhone() : old_Data.getPhone());

           //  Update address if new address is not null or empty
        old_Data.setAddress(newData.getAddress() != null && !newData.getAddress().equals("") ? newData.getAddress() : old_Data.getAddress());

       }
        //  Save updated data back to the database
        ss.addData(old_Data);

        //  Return updated student data (or null if roll not found)
       return  old_Data;
    }
}
