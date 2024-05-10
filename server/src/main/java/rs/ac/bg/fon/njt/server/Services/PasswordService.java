package rs.ac.bg.fon.njt.server.Services;

import rs.ac.bg.fon.njt.server.Models.User;

public interface PasswordService {

    void generateTempPassword(String password, User user);

}
