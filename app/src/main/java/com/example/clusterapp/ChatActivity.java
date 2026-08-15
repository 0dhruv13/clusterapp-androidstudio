package com.example.clusterapp;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.os.Looper;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView chatRecyclerView;
    private MessageAdapter messageAdapter;
    private List<Message> messageList;
    private EditText messageInput;
    private String communityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        communityName = getIntent().getStringExtra("community_name");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.chat_title, communityName));
        }

        chatRecyclerView = findViewById(R.id.chatRecyclerView);
        messageInput = findViewById(R.id.messageInput);
        Button sendButton = findViewById(R.id.sendButton);

        messageList = new ArrayList<>();
        messageAdapter = new MessageAdapter(messageList);
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatRecyclerView.setAdapter(messageAdapter);

        sendButton.setOnClickListener(v -> {
            String text = messageInput.getText().toString().trim();
            if (!text.isEmpty()) {
                addMessage("You", text);
                messageInput.setText("");
            }
        });

        startBotConversation();
    }

    private void addMessage(String sender, String text) {
        messageList.add(new Message(sender, text));
        messageAdapter.notifyItemInserted(messageList.size() - 1);
        chatRecyclerView.scrollToPosition(messageList.size() - 1);
    }

    private void startBotConversation() {
        String[] bots = {"Alex", "Jordan", "Charlie", "Sam"};
        String[] generalMessages = {
            "Hello everyone!",
            "Glad to be here in " + communityName,
            "What's the latest news?",
            "I'm new here, nice to meet you all.",
            "Any recommendations for someone starting out?"
        };

        String[] gamingMessages = {
            "Did you see the new update?",
            "I'm stuck on level 5, any tips?",
            "GG everyone!",
            "Who's up for a raid in 10 mins?",
            "I just got a legendary drop!",
            "The graphics are insane.",
            "Can't wait for the tournament next week.",
            "Add me if you want to party up."
        };

        String[] techMessages = {
            "Is anyone using the new framework?",
            "I love the latest AI integration.",
            "Can someone review my code?",
            "Tech is moving so fast!",
            "Which IDE is better for this?"
        };

        final String[] messages;
        if (communityName.toLowerCase().contains("gaming")) {
            messages = gamingMessages;
        } else if (communityName.toLowerCase().contains("tech")) {
            messages = techMessages;
        } else {
            messages = generalMessages;
        }

        Handler handler = new Handler(Looper.getMainLooper());
        Random random = new Random();

        // Add some initial messages
        for (int i = 0; i < 3; i++) {
            int botIndex = random.nextInt(bots.length);
            int msgIndex = random.nextInt(messages.length);
            addMessage(bots[botIndex], messages[msgIndex]);
        }

        // Periodically add more messages
        Runnable botTask = new Runnable() {
            @Override
            public void run() {
                int botIndex = random.nextInt(bots.length);
                int msgIndex = random.nextInt(messages.length);
                addMessage(bots[botIndex], messages[msgIndex]);
                handler.postDelayed(this, 3000 + random.nextInt(5000));
            }
        };
        handler.postDelayed(botTask, 2000);
    }
}