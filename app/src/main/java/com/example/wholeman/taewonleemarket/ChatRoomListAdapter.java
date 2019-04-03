package com.example.wholeman.taewonleemarket;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatRoomListAdapter extends RecyclerView.Adapter<ChatRoomListAdapter.ViewHolder> {

    public interface Listener {
        void onRoomItemClicked(ChatRoomListItem chatRoomListItem);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageViewProfile;
        TextView mTextViewRoomName;
        TextView mTextViewMessage;
        TextView mTextViewLocation;
        TextView mTextViewTimestamp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageViewProfile = itemView.findViewById(R.id.image_profile);
            mTextViewRoomName = itemView.findViewById(R.id.text_room_name);
            mTextViewMessage = itemView.findViewById(R.id.text_message);
            mTextViewLocation = itemView.findViewById(R.id.text_location);
            mTextViewTimestamp = itemView.findViewById(R.id.text_timestamp);
        }
    }

    private LayoutInflater mInflater;
    private Listener mListener;

    private ArrayList<ChatRoomListItem> mChatRoomList;

    public ChatRoomListAdapter(ArrayList<ChatRoomListItem> chatRoomList,LayoutInflater inflater, Listener listener) {
        mChatRoomList = chatRoomList;
        mInflater = inflater;
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = mInflater.inflate(R.layout.layout_item_chatroom_list, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        viewHolder.mTextViewRoomName.setText(mChatRoomList.get(position).getRoomName());
        viewHolder.mTextViewMessage.setText(mChatRoomList.get(position).getMessage());
        viewHolder.mTextViewLocation.setText(mChatRoomList.get(position).getLocation());
        viewHolder.mTextViewTimestamp.setText(mChatRoomList.get(position).getTimestamp());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onRoomItemClicked(mChatRoomList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != mChatRoomList ? mChatRoomList.size() : 0);
    }
}
