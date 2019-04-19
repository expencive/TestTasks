package com.gmail.expencive.jsonplaceholderapi.UserListRecyclerView;

import org.json.JSONObject;

public class UserListItem {

    private String mUserName;
    private String mId;

    public UserListItem(String userName, String id) {
        mUserName = userName;
        mId = id;
    }


    public String getUserName() {
        return mUserName;
    }

    public String getId() {
        return mId;
    }
}
