package com.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.data.ChatRoomViewModel;
import com.example.charafsandroidlabs.R;
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
    private ChatMessageDAO mDAO;

    private String TextMessageNotify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.myToolbar);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);

        chatModel.selectedMessage.observe(this, (newMessageValue) -> {
            // Pass the selected message to the MessageDetailsFragments constructor
            MessageDetailsFragments chatFragment = new MessageDetailsFragments(newMessageValue);

            FragmentManager fMgr = getSupportFragmentManager();
            FragmentTransaction transaction = fMgr.beginTransaction();
            transaction.addToBackStack("Char");
            transaction.replace(R.id.fragmentLocation, chatFragment);
            transaction.commit();
        });

        MessageDatabase db = Room.databaseBuilder(getApplicationContext(), MessageDatabase.class, "MessageDB").build();
        mDAO = db.cmDAO();

        if (messages == null) {
            chatModel.messages.setValue(messages = new ArrayList<>());
            Executor thread = Executors.newSingleThreadExecutor();
            thread.execute(() -> {
                messages.addAll(mDAO.getAllMessages());
                runOnUiThread(() -> binding.recyclerView.setAdapter(myAdapter));
            });
        }

        binding.sendButton.setOnClickListener(click -> {
            TextMessageNotify = binding.messageEntered.getText().toString();
            String messageText = binding.messageEntered.getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd-MMM-yyyy hh-mm-ss a");
            String currentDateandTime = sdf.format(new Date());
            ChatMessage chatMessage = new ChatMessage(messageText, currentDateandTime, true);
            messages.add(chatMessage);

            binding.messageEntered.setText("");
            Executor thread = Executors.newSingleThreadExecutor();
            thread.execute(() -> {
                mDAO.insertMessage(chatMessage);
            });
            myAdapter.notifyItemInserted(messages.size() - 1);
        });

        binding.receiveButton.setOnClickListener(click -> {
            TextMessageNotify = binding.messageEntered.getText().toString();
            String messageText = binding.messageEntered.getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd-MMM-yyyy hh-mm-ss a");
            String currentDateandTime = sdf.format(new Date());
            ChatMessage chatMessage = new ChatMessage(messageText, currentDateandTime, false);
            messages.add(chatMessage);
            binding.messageEntered.setText("");
            Executor thread = Executors.newSingleThreadExecutor();
            thread.execute(() -> {
                mDAO.insertMessage(chatMessage);
            });
            myAdapter.notifyItemInserted(messages.size() - 1);
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

    private void showDeleteConfirmationDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Message");
        builder.setMessage("Do you wish to delete this message: " + TextMessageNotify);
        ChatMessage toDelete = messages.get(position);
        chatModel.selectedMessage.postValue(toDelete);
        /* builder.setPositiveButton("Yes", (dialog, which) -> {
           ; // Make a copy
            messages.remove(position);
            myAdapter.notifyItemRemoved(position);

            Executor thread = Executors.newSingleThreadExecutor();
            thread.execute(() -> {
                mDAO.deleteMessage(mDAO.getAllMessages().get(position));
            });

            onBackPressed();
        });
        builder.setNegativeButton("No", null);
        builder.create().show();*/
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

    if (item.getItemId() == R.id.item_1) {showDeleteMessage();}
    else {Toast.makeText(this, "Version 1.0, created by Charaf", Toast.LENGTH_LONG).show();}
        return true;
    }
    private void showDeleteMessage() {
        if (chatModel.selectedMessage.getValue() != null) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Do you want to delete this message?")
                    .setTitle("Delete")
                    .setNegativeButton("No", (dialog, which) -> {
                        // if "No" is clicked
                    })
                    .setPositiveButton("Yes", (dialog, which) -> {
                        // if "Yes" is clicked
                        ChatMessage toDelete = chatModel.selectedMessage.getValue();
                        if (toDelete != null) {
                            Executor thread1 = Executors.newSingleThreadExecutor();
                            thread1.execute(() -> {
                                // delete from the database
                                mDAO.deleteMessage(toDelete);
                            });

                            int position = messages.indexOf(toDelete);
                            messages.remove(position); // remove from the array list
                            myAdapter.notifyItemRemoved(position); // notify the adapter of the removal

                            // give feedback: anything on the screen
                            Snackbar.make(findViewById(android.R.id.content), "You deleted the message", Snackbar.LENGTH_LONG)
                                    .setAction("Undo", (btn) -> {
                                        Executor thread2 = Executors.newSingleThreadExecutor();
                                        thread2.execute(() -> {
                                            mDAO.insertMessage(toDelete);
                                        });

                                        messages.add(position, toDelete);
                                        myAdapter.notifyItemInserted(position); // notify the adapter of the insertion
                                    })
                                    .show();
                            onBackPressed();
                        }
                    });

            builder.create().show();
        }
    }



}


