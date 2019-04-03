package com.example.wholeman.taewonleemarket;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class ChatRoomActivity extends AppCompatActivity {

    public static final String EXTRA_CHAT_ROOM_ID = "EXTRA_CHAT_ROOM_ID";

    private String mRoomId;

    private User mCurrentUser;

    private ChatMessageListAdapter mChatMessageListAdapter;

    private ArrayList<ChatMessage> mChatMessagesList = new ArrayList<>();

    private RecyclerView mRecyclerChatMessageList;

    private LinearLayoutManager mLinearLayoutManager;

    private Button mButtonSendMessage;

    private EditText mEditTextInputMessage;

    // TODO: 삭제할 부분
    private User[] users = new User[2];

    TextWatcher mInputMessageTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() > 0 && s.toString().trim().length() != 0) {
                mButtonSendMessage.setEnabled(true);
            } else {
                mButtonSendMessage.setEnabled(false);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    public static void start(Context context, String chatRoomId) {
        Intent intent = new Intent(context, ChatRoomActivity.class);
        intent.putExtra(EXTRA_CHAT_ROOM_ID, chatRoomId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chatroom);

        mRoomId = getIntent().getStringExtra(EXTRA_CHAT_ROOM_ID);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        mButtonSendMessage = findViewById(R.id.button_send_message);

        mEditTextInputMessage = findViewById(R.id.edit_input_message);

        mEditTextInputMessage.addTextChangedListener(mInputMessageTextWatcher);

        mButtonSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        mChatMessageListAdapter = new ChatMessageListAdapter(this, mChatMessagesList, getLayoutInflater());

        mRecyclerChatMessageList = findViewById(R.id.recycler_message_list);

        mLinearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, true);

//        mLinearLayoutManager.setStackFromEnd(true);

        mRecyclerChatMessageList.setLayoutManager(mLinearLayoutManager);

        mRecyclerChatMessageList.setItemAnimator(new DefaultItemAnimator());

        mRecyclerChatMessageList.setAdapter(mChatMessageListAdapter);

        // TODO: 삭제할 부분
        users[0] = new User("태원이", null);
        users[1] = new User("태원리", null);

        mCurrentUser = users[0];

        Random random = new Random();


        for (int i = 0; i < 100; i++) {
            int x = random.nextInt(2);
            mChatMessagesList.add(new ChatMessage(
                    mRoomId,
                    UUID.randomUUID().toString(),
                    users[x],
                    "안녕하세요",
                    System.currentTimeMillis()));
        }
    }

    private void sendMessage() {
        // 1. 메시지를 리싸이클러뷰에 뜨운다 adapter notify
        // 2. 메시지를 로컬 db에 저장한다.(전송완료 여부 false)
        // 3. 메시지를 서버로 보낸다.

        // 4. 전송이 완료되면 읽음 표시 여부와 시간 표시(로컬에 db 전송완료 여부 true)
        // 4-1. 전송 중일 땐 진행 중(프로그레시브)
        // 4-2. 전송 실패일 땐 재전송 버튼 활성화

        String messageText = mEditTextInputMessage.getText().toString();

        if (!TextUtils.isEmpty(messageText)) {
            mChatMessageListAdapter.appendMessage(new ChatMessage(
                    mRoomId,
                    UUID.randomUUID().toString(),
                    mCurrentUser,
                    messageText,
                    System.currentTimeMillis()
            ));
            mRecyclerChatMessageList.scrollToPosition(0);
            mEditTextInputMessage.setText("");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        fetchChatMessages();
    }

    private void fetchChatMessages() {
        bindChatMessages();
    }

    private void bindChatMessages() {
        mChatMessageListAdapter.notifyDataSetChanged();
    }

}