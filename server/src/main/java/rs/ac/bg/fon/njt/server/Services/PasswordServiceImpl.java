package rs.ac.bg.fon.njt.server.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.njt.server.Models.Password;
import rs.ac.bg.fon.njt.server.Repositories.PasswordRepository;

@Service
public class PasswordServiceImpl implements PasswordService {

    @Autowired
    PasswordRepository passwordRepository;

    @Override
    public String generateTempPassword() {
        return "TempPassword123";
    }

    @Override
    public void savePassword(Password password) {
        passwordRepository.save(password);
    }

    @Override
    public void updatePassword(Password password) {
        if (password == null) {
            return;
        }

        passwordRepository.updatePasswordByUserId(password.getPasswordHash(), password.getUser().getId());

    }
}
