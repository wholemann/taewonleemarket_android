package com.example.wholeman.taewonleemarket;

class ChatRoomListItem {

    private String mChatRoomId;
    private String mImageUrl;
    private String mRoomName;
    private String mLocation;
    private String mTimestamp;
    private String mMessage;



    public ChatRoomListItem(String chatRoomId, String roomName, String message, String location, String timestamp) {
        mChatRoomId = chatRoomId;
        mRoomName = roomName;
        mMessage = message;
        mLocation = location;
        mTimestamp = timestamp;
    }

    public String getChatRoomId() {
        return mChatRoomId;
    }

    public String getRoomName() {
        return mRoomName;
    }

    public String getMessage() {
        return mMessage;
    }

    public String getLocation() {
        return mLocation;
    }

    public String getTimestamp() {
        return mTimestamp;
    }
}
