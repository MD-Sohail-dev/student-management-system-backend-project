package Student.management.system.service;

import Student.management.system.entity.student;
import Student.management.system.repository.studentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class studentDetailsServiceImpl  implements UserDetailsService {

    @Autowired
    private studentRepository StudnRep;

    @Override
    public UserDetails loadUserByUsername(String studentName) throws UsernameNotFoundException {
        student s = StudnRep.findByname(studentName);
        if(s != null){
            UserDetails UD = org.springframework.security.core.userdetails.User.builder()
                    .username(s.getName())
                    .password(s.getPassword())
                    .roles(s.getRole())
                    .build();
            return  UD;
        }
        else{
            throw new UsernameNotFoundException("Student name not found");
        }
    }
}
