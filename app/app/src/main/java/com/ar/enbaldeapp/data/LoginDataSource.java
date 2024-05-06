package com.ar.enbaldeapp.data;

import com.ar.enbaldeapp.data.model.LoggedInUser;
import com.ar.enbaldeapp.models.User;
import com.ar.enbaldeapp.models.utilities.SharedPreferencesManager;
import com.ar.enbaldeapp.services.ApiServices;
import com.ar.enbaldeapp.services.IApiServices;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {
    public Result<LoggedInUser> login(String username, String password) {
        try {
            final Result[] result = new Result[1];
            IApiServices apiServices = new ApiServices();
            apiServices.login(username, password,
                    (User u) -> result[0] = new Result.Success<>(new LoggedInUser(u)),
                    () -> result[0] = new Result.Error(new IOException("Could not log in")));

            return result[0];
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public Result<String> logout() {
        try {
            final Result[] result = new Result[1];
            IApiServices apiServices = new ApiServices();
            apiServices.logout(
                    () -> new Result.Success<String>("Logout successful!"),
                    () -> new Result.Error(new IOException("Could not log out")));

            return result[0];
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging out", e));
        }
    }
}