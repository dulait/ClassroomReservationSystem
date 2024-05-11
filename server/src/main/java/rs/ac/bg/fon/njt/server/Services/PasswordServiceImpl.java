package rs.ac.bg.fon.njt.server.Services;

import org.springframework.stereotype.Service;

@Service
public class PasswordServiceImpl implements PasswordService {
    @Override
    public String generateTempPassword() {
        return "TempPassword123";
    }
}
