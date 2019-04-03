package com.example.wholeman.taewonleemarket;

public class User {
    private String mNickName;
    private String mProfileUrl;

    public User(String nickName, String profileUrl) {
        mNickName = nickName;
        mProfileUrl = profileUrl;
    }

    public String getNickName() {
        return mNickName;
    }

    public String getProfileUrl() {
        return mProfileUrl;
    }
}
