package work.umatech.security.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Dictionary {
    public static final String USER_NAME = "userName";
    public static final String USER_EMAIL = "email";
    public static final String USER_ROLE = "role";

    public static final String USER = "user";

    public static final String ADMIN = "admin";

    public static final String GUEST = "guest";


    public static final Integer STATUS_SUCCESS = 0;
    public static final Integer STATUS_FAIL = 1;

    public static final String SUBJECT = "sub";

    public static final List<String> AUTH_URL_LIST = Arrays.asList("/auth","/test");


}
