package rs.ac.bg.fon.njt.server.Services;

import rs.ac.bg.fon.njt.server.Utils.Response;

public interface AuthService {
    Response<String> registerUser(String email);

    Response<String> loginUser(String email, String password);

    Response<String> changePassword(String email, String oldPassword, String newPassword);

}
