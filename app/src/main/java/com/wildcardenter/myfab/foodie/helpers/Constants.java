package com.wildcardenter.myfab.foodie.helpers;

/*
    Class On Package com.wildcardenter.myfab.foodie.helpers
    
    Created by Asif Mondal on 21-09-2019 at 16:55
*/


import java.util.Arrays;
import java.util.List;

public class Constants {
    public static final int TYPE_BOOLEAN = 54;
    public static final int TYPE_STRING = 58;
    public static final int TYPE_INTEGER = 57;
    public static final int TYPE_FLOAT = 56;
    public static final int TYPE_STRING_SET = 61;

    public static final int TYPE_STUDENT = 1212;
    public static final int TYPE_TEACHER = 1313;
    public static final int TYPE_ADMIN = 1414;

    public static final String SWIPECARD_KEY = "isFirstSwipeCard";
    public static final String PLAYER_KEY = "isFirstMusicPlayer";
    public static final String ASSSTANT_KEY = "isFirstAssistant";
    public static final String AUTHORIZATION_KEY = "isAuthorized";

    public static final String USER_TYPE_KEY = "user_type";
    public static final String LOGIN_TYPE_FILE = "userinfo";
    public static final String STUDENT_SUBCODES_FILE = "student_subs";
    public static final String STUDENT_SUBCODE_REF = "students_subs";
    public static final String STUDENT_SUBCODEKEY = "attended_subs";
    public static final String TEACHER_ALLOTED_SUBS = "teacher_subs";
    public static final String PASSWORD = "try_time_is_limited";

    public static final String ROLL_NO = "roll_no";
    public static final String NAME = "sname";
    public static final String EMAIL = "email";
    public static final String AC = "access_code";

    public static final String NOTIFICATIN_COLLECTION_NAME = "notifications";
    public static final String NOTIFICATON_TOPIC = "classes";
    public static final List<String> category = Arrays.asList("All", "Italian",
            "Continental","Chinese","Indian");

    //references
    public static final String PRODUCT_COLLECTION="Products";
    public static final String CART_ITEM_REFER="CartItems";
    public static final String FAB_ITEM_REF="Favorites";
}
