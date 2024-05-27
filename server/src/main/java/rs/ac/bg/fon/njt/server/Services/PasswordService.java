package rs.ac.bg.fon.njt.server.Services;

import rs.ac.bg.fon.njt.server.Models.Password;

public interface PasswordService {

    String generateTempPassword();

    void savePassword(Password password);

    void updatePassword(Password password);
}
