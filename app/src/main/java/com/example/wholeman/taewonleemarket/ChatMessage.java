package com.example.wholeman.taewonleemarket;

public class ChatMessage {

    private final static int TYPE_TEXT = 0;
    private final static int TYPE_IMAGE = 1;

    private String mChatRoomId;
    private String mMessageId;
    private User mSender;
    private String mMessageBody;
    private long mTimestamp;
    private int mMessageType;

    public ChatMessage(String chatRoomId, String messageId, User sender, String messageBody, long timestamp) {
        mChatRoomId = chatRoomId;
        mMessageId = messageId;
        mSender = sender;
        mMessageBody = messageBody;
        mTimestamp = timestamp;
    }

    public String getChatRoomId() {
        return mChatRoomId;
    }

    public String getMessageId() {
        return mMessageId;
    }

    public User getSender() {
        return mSender;
    }

    public String getMessageBody() {
        return mMessageBody;
    }

    public long getTimestamp() {
        return mTimestamp;
    }

    public int getMessageType() {
        return mMessageType;
    }
}
