package rs.ac.bg.fon.njt.server.Services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.njt.server.Enums.ResponseStatus;
import rs.ac.bg.fon.njt.server.Models.Password;
import rs.ac.bg.fon.njt.server.Repositories.PasswordRepository;
import rs.ac.bg.fon.njt.server.Utils.Response;

@Service
public class PasswordServiceImpl implements PasswordService {
    
    @Autowired
    PasswordRepository passwordRepository;
    
    @Override
    public String generateTempPassword() {
        return "TempPassword123";
    }

    @Override
    public Response<Password> updatePassword(Password password) {
        if(password == null){
            return new Response<>(ResponseStatus.BadRequest);
        }
        
        passwordRepository.updatePasswordByUserId(password.getPasswordHash(), password.getUser().getId());
        
        return new Response<>(ResponseStatus.Ok);
    }
}
