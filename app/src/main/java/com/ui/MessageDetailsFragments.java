package com.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.viewmodel.CreationExtras;

import com.example.charafsandroidlabs.databinding.DetailsLayoutBinding;


    public class MessageDetailsFragments extends Fragment {
        ChatMessage selected;
        public MessageDetailsFragments(ChatMessage m){
            selected = m;
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            super.onCreateView(inflater, container, savedInstanceState);

            DetailsLayoutBinding binding = DetailsLayoutBinding.inflate(inflater);
            binding.messageText.setText(selected.getMessage());
            binding.timeText.setText(selected.getTimeSent());
            binding.sentOrReceive.setText("" + selected.isSentButton());
            binding.databaseText.setText("ID = " + (selected.id));

            return binding.getRoot();
        }
    }

