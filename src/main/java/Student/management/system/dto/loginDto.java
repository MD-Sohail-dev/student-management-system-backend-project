package Student.management.system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class loginDto {
    @NonNull
    private String userName;
    @NonNull
    private  String password;
}
