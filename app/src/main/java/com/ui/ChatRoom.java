package com.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import com.data.ChatRoomViewModel;
import com.example.charafsandroidlabs.databinding.ActivityChatRoomBinding;
import com.example.charafsandroidlabs.databinding.SentMessageBinding;
import com.example.charafsandroidlabs.databinding.ReceiveMessageBinding;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ChatRoom extends AppCompatActivity {

    private ActivityChatRoomBinding binding;
    private ChatRoomViewModel chatModel;
    private ArrayList<ChatMessage> messages;
    private RecyclerView.Adapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        chatModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);

        messages = chatModel.messages.getValue();

        MessageDatabase db = Room.databaseBuilder(getApplicationContext(), MessageDatabase.class, "MessageDB").build();
        ChatMessageDAO mDAO = db.cmDAO();

        if (messages == null) {
            chatModel.messages.setValue(messages = new ArrayList<>());

            Executor thread = Executors.newSingleThreadExecutor();
            thread.execute(() -> {
                messages.addAll(mDAO.getAllMessages());

                runOnUiThread(() -> binding.recyclerView.setAdapter(myAdapter));
                Log.d("ChatRoom", "Number of messages: " + messages.size());
            });
        }

        binding.sendButton.setOnClickListener(click -> {
            String messageText = binding.textInput.getText().toString();

            SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd-MMM-yyyy hh-mm-ss a");
            String currentDateandTime = sdf.format(new Date());

            ChatMessage chatMessage = new ChatMessage(messageText, currentDateandTime, true);
            messages.add(chatMessage);

            myAdapter.notifyItemInserted(messages.size()-1);
            binding.textInput.setText("");
        });

        binding.receiveButton.setOnClickListener(click -> {
            String messageText = binding.textInput.getText().toString();

            SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd-MMM-yyyy hh-mm-ss a");
            String currentDateandTime = sdf.format(new Date());

            ChatMessage chatMessage = new ChatMessage(messageText, currentDateandTime, false);
            messages.add(chatMessage);

            myAdapter.notifyItemInserted(messages.size()- 1);
            binding.textInput.setText("");
        });

        binding.recyclerView.setAdapter(myAdapter = new RecyclerView.Adapter<MyRowHolder>() {
            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                if (viewType == 0) {
                    SentMessageBinding binding = SentMessageBinding.inflate(getLayoutInflater(), parent, false);
                    return new MyRowHolder(binding);
                } else {
                    ReceiveMessageBinding binding = ReceiveMessageBinding.inflate(getLayoutInflater(), parent, false);
                    return new MyRowHolder(binding);
                }
            }

            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
                ChatMessage chatMessage = messages.get(position);
                holder.bind(chatMessage);

                holder.itemView.setOnClickListener(view -> {
                    showDeleteConfirmationDialog(position);
                });
            }

            @Override
            public int getItemCount() {
                return messages.size();
            }

            @Override
            public int getItemViewType(int position) {
                if (messages.get(position).isSentButton()) {
                    return 0; // Sent message
                } else {
                    return 1; // Received message
                }
            }
        });
    }

    // Method to show delete confirmation dialog
    private void showDeleteConfirmationDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Message");
        builder.setMessage("Do you want to delete this message?");
        builder.setPositiveButton("Yes", (dialog, which) -> {
            // Delete message from list and database
            deleteMessage(position);

            // Show Snackbar with undo option
            showUndoSnackbar(position);
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }

    // Method to delete a message
    private void deleteMessage(int position) {
        ChatMessage deletedMessage = messages.remove(position);
        myAdapter.notifyItemRemoved(position);

        // Delete message from the database
        // Make sure you have a reference to your ChatMessageDAO (mDAO) for database operations
        // mDAO.delete(deletedMessage);
    }

    // Method to show Snackbar with undo option
    private void showUndoSnackbar(int position) {
        Snackbar.make(binding.getRoot(), "Message deleted", Snackbar.LENGTH_LONG)
                .setAction("Undo", v -> {
                    // Undo the deletion by re-inserting the message
                    undoDelete(position);
                })
                .show();
    }

    // Method to undo the deletion
    private void undoDelete(int position) {
        ChatMessage undoMessage = messages.get(position);
        messages.add(position, undoMessage);
        myAdapter.notifyItemInserted(position);

        // Insert the message back into the database
        // Make sure you have a reference to your ChatMessageDAO (mDAO) for database operations
        // mDAO.insert(undoMessage);
    }

    class MyRowHolder extends RecyclerView.ViewHolder {
        private SentMessageBinding sentMessageBinding;
        private ReceiveMessageBinding receiveMessageBinding;

        public MyRowHolder(SentMessageBinding sentMessageBinding) {
            super(sentMessageBinding.getRoot());
            this.sentMessageBinding = sentMessageBinding;
        }

        public MyRowHolder(ReceiveMessageBinding receiveMessageBinding) {
            super(receiveMessageBinding.getRoot());
            this.receiveMessageBinding = receiveMessageBinding;
        }

        public void bind(ChatMessage chatMessage) {
            if (chatMessage.isSentButton()) {
                sentMessageBinding.message.setText(chatMessage.getMessage());
                sentMessageBinding.time.setText(chatMessage.getTimeSent());

            } else {
                receiveMessageBinding.message.setText(chatMessage.getMessage());
                receiveMessageBinding.time.setText(chatMessage.getTimeSent());
            }
        }
    }
}
