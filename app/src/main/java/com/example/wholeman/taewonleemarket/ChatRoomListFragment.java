package com.example.wholeman.taewonleemarket;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ChatRoomListFragment extends Fragment implements ChatRoomListAdapter.Listener {

    private ChatRoomListAdapter mChatRoomListAdapter;

    private ArrayList<ChatRoomListItem> mChatRoomList = new ArrayList<>();

    private RecyclerView mRecyclerChatRoomList;

    public ChatRoomListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_chatroomlist, container, false);

        mChatRoomListAdapter = new ChatRoomListAdapter(mChatRoomList, inflater, this);

        mRecyclerChatRoomList = view.findViewById(R.id.recycler_room_list);

        mRecyclerChatRoomList.setLayoutManager(new LinearLayoutManager(getActivity()));

        mRecyclerChatRoomList.setItemAnimator(new DefaultItemAnimator());

        mRecyclerChatRoomList.setAdapter(mChatRoomListAdapter);

        for (int i=0; i<100; i++) {
            mChatRoomList.add(new ChatRoomListItem(""+i, "방제목"+i, "채팅메시지"+i, "이태원", "10분 전"));
        }

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        fetchChatRoomList();
    }

    private void fetchChatRoomList() {

        bindChatRoomList(mChatRoomList);
    }

    public void bindChatRoomList(List<ChatRoomListItem> chatRoomList) {
        mChatRoomListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRoomItemClicked(ChatRoomListItem chatRoomListItem) {
        ChatRoomActivity.start(getContext(), chatRoomListItem.getChatRoomId());
    }
}
