package com.example.demo.util.exception.user.userExceptionHandler;

import com.example.demo.model.ISP.UserStatus;
import com.example.demo.model.User;
import com.example.demo.util.encoder.Md5Encoder;
import com.example.demo.util.exception.user.userException.*;

import static com.example.demo.util.message.Message.*;

public class UserExceptionHandler {



    public static void seeIfDuplicated(User user) throws DuplicateUserException {
        DuplicateUserException.check(user != null, DUPLICATE_USER_MESSAGE);
    }

    public static void isThereUserWithGivenUsername(User user) throws UserNotFoundException {
        UserNotFoundException.check(user == null, USER_NOT_FOUND_MESSAGE);
    }

    public static void ifUserLocked(UserStatus userStatus) throws UnverifiedException {
        UnverifiedException.check(userStatus != UserStatus.ACTIVE, UNVERIFIED_STATUS_MESSAGE);
    }

    public static void checkPassword(String password, String enPassword) throws UnauthorizedException {
        UnauthorizedException.check(!new Md5Encoder().matches(password, enPassword), UNAUTHORIZED_PASSWORD_MESSAGE);
    }

    public static void checkCodes(String dbCode, String userGivenCode) throws AccessDeniedException {
        AccessDeniedException.check(!dbCode.equals(userGivenCode), UNAUTHORIZED_PASSWORD_MESSAGE);
    }

}
