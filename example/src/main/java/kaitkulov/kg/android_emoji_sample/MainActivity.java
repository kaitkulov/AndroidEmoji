package kaitkulov.kg.android_emoji_sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import kg.kaitkulov.androidemoji.Actions.EmojIconActions;
import kg.kaitkulov.androidemoji.Helper.EmojiconEditText;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    EmojiconEditText emojiconEditText;
    ImageView emojiButton;
    ImageView submitButton;
    View rootView;
    EmojIconActions emojIcon;
    private boolean defaultEmoji;
    private EmojiAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootView = findViewById(R.id.root_view);
        emojiButton = (ImageView) findViewById(R.id.emoji_btn);
        submitButton = (ImageView) findViewById(R.id.submit_btn);
        emojiconEditText = (EmojiconEditText) findViewById(R.id.emojicon_edit_text);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        adapter = new EmojiAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        emojIcon = new EmojIconActions(this, rootView, emojiconEditText, emojiButton);
        emojIcon.showEmojIcon();
        emojIcon.setKeyboardListener(new EmojIconActions.KeyboardListener() {
            @Override
            public void onKeyboardOpen() {
                Log.e("Keyboard", "open");
            }

            @Override
            public void onKeyboardClose() {
                Log.e("Keyboard", "close");
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addItem(emojiconEditText.getText().toString());
                emojiconEditText.setText("");
                recyclerView.scrollToPosition(adapter.getItemCount() - 1);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_default) {
            defaultEmoji = !defaultEmoji;
            item.setTitle(defaultEmoji ? " Use Custom" : "Use Default");
            emojIcon.setUseSystemEmoji(defaultEmoji);
            adapter.setUseSystemDefault(defaultEmoji);
        }
        return true;
    }
}
