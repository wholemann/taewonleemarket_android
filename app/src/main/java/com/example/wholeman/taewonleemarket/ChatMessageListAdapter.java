package com.example.wholeman.taewonleemarket;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wholeman.taewonleemarket.utils.DateUtils;
import com.example.wholeman.taewonleemarket.utils.ImageUtils;

import java.util.ArrayList;

public class ChatMessageListAdapter extends RecyclerView.Adapter {

    private static final int VIEW_TYPE_MESSAGE_SENT = 0;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 1;

    private class SentMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText;
        TextView timeText;

        SentMessageHolder(View itemView) {
            super(itemView);

            messageText = (TextView) itemView.findViewById(R.id.text_message_body);
            timeText = (TextView) itemView.findViewById(R.id.text_message_time);
        }

        void bind(ChatMessage message) {
            messageText.setText(message.getMessageBody());

            // Format the stored timestamp into a readable String using method.
            timeText.setText(DateUtils.formatDateTime(message.getTimestamp()));
        }
    }

    private class ReceivedMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText;
        TextView timeText;
        TextView nameText;
        ImageView profileImage;

        ReceivedMessageHolder(View itemView) {
            super(itemView);

            messageText = (TextView) itemView.findViewById(R.id.text_message_body);
            timeText = (TextView) itemView.findViewById(R.id.text_message_time);
            nameText = (TextView) itemView.findViewById(R.id.text_message_name);
            profileImage = (ImageView) itemView.findViewById(R.id.image_message_profile);
        }

        void bind(ChatMessage message) {
            messageText.setText(message.getMessageBody());

            // Format the stored timestamp into a readable String using method.
            timeText.setText(DateUtils.formatDateTime(message.getTimestamp()));

            nameText.setText(message.getSender().getNickName());

            // Insert the profile image from the URL into the ImageView.
            ImageUtils.displayRoundImageFromUrl(mContext, message.getSender().getProfileUrl(), profileImage);
        }
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageViewProfile;
        TextView mTextViewUserName;
        TextView mTextViewUserMessageBody;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageViewProfile = itemView.findViewById(R.id.image_message_profile);
            mTextViewUserName = itemView.findViewById(R.id.text_message_name);
            mTextViewUserMessageBody = itemView.findViewById(R.id.text_message_body);
        }
    }

    private LayoutInflater mInflater;

    private ArrayList<ChatMessage> mChatMessageList;

    private Context mContext;

    public ChatMessageListAdapter(Context context, ArrayList<ChatMessage> chatMessageList, LayoutInflater inflater) {
        mContext = context;
        mChatMessageList = chatMessageList;
        mInflater = inflater;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;

        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(mContext)
                    .inflate(R.layout.layout_item_message_sent, viewGroup, false);
            return new SentMessageHolder(view);
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater.from(mContext)
                    .inflate(R.layout.layout_item_message_received, viewGroup, false);
            return new ReceivedMessageHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ChatMessage message = (ChatMessage) mChatMessageList.get(position);

        switch (viewHolder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentMessageHolder) viewHolder).bind(message);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessageHolder) viewHolder).bind(message);
        }
    }

    @Override
    public int getItemCount() {
        return mChatMessageList.size();
    }

    @Override
    public int getItemViewType(int position) {
        ChatMessage message = (ChatMessage) mChatMessageList.get(position);

        if (message.getSender().getNickName().equals("태원이")) {
            return VIEW_TYPE_MESSAGE_SENT;
        } else {
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }

    // TODO: 이부분은 추후에 notifyItemChanged로 수정.
    public void appendMessage(ChatMessage userMessage) {
        mChatMessageList.add(0, userMessage);
        notifyDataSetChanged();
    }

}
