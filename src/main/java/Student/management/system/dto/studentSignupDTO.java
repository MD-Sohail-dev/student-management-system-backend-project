package Student.management.system.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.mongodb.core.index.Indexed;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class studentSignupDTO {
    @NonNull
    private String userName;
    @NonNull
    private String password;
    @NonNull
    private String email;
    @NonNull
    private String admissionNumber;
}
