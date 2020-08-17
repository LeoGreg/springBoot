package com.example.demo.util.message;

public interface Message {

    String INTERNAL_ERROR_MESSAGE = "Something went wrong, please try later";

    String PASSWORD_INVALID_MESSAGE = "Password  must be more then 8 characters";

    String USERNAME_INVALID_MESSAGE = "Username must be more then 5 characters";

    String CODE_INVALID_MESSAGE = "Code must be equal 5 digits";




    String DUPLICATE_USER_MESSAGE = "duplicate.user";

    String CODE_NEEDED_MESSAGE = "we've sent you a code please verify our account";

    String USER_NOT_FOUND_MESSAGE = "user.not.found";

    String NOT_MATCHING_COED_MESSAGE = "not.matching.codes";

    String UNVERIFIED_STATUS_MESSAGE = "unverified.status";

    String UNAUTHORIZED_PASSWORD_MESSAGE = "Wrong.password";





    String USER_NOT_EXIST_MESSAGE = "There is some problem with your account";

    String SESSION_EXPIRED_MESSAGE = "Your session expired please log in ";

    String WRONG_PASSWORD_MESSAGE = "Incorrect password";

    String WRONG_CODE_MESSAGE = "Incorrect verification code";

    String PASSWORD_CHANGE_SUCCESS_MESSAGE = "Your password successfully changed";

    String VERIFICATION_SUCCESS_MESSAGE = "Verification was success, please log in";

    String REGISTRATION_SUCCESS_MESSAGE = "Registration was success, now please verify your email";

    String CODE_SUCCESSFULLY_SEND_MESSAGE = "Your code is successfully sent to your email !";

}
