package model;

import android.provider.BaseColumns;
public final  class Profile {
    public Profile() {
    }

    public static class ProfileTable implements BaseColumns {
        public static final String TABLE_NAME = "profile";
        public static final String COL_ID = "id";
        public static final String COL_NAME = "name";
        public static final String COL_EMAIL = "email";
        public static final String COL_PASSWORD = "password";
        public static final String COL_PHONE = "phone";
    }

}
