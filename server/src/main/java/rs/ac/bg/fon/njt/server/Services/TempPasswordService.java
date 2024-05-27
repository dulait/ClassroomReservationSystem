package rs.ac.bg.fon.njt.server.Services;

import rs.ac.bg.fon.njt.server.Models.User;

public interface TempPasswordService {

    void saveTempPassword(User user, String tempPassword);

    boolean isTempPassword(User user, String password);

    void invalidateTempPassword(User user);
}
