package rs.ac.bg.fon.njt.server.Services;

import rs.ac.bg.fon.njt.server.Models.Password;
import rs.ac.bg.fon.njt.server.Utils.Response;

public interface PasswordService {

    String generateTempPassword();
    Response<Password> updatePassword(Password password);
}
