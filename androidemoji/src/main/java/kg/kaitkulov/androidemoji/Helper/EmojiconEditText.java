/*
 * Copyright 2014 Hieu Rocker
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package kg.kaitkulov.androidemoji.Helper;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.style.DynamicDrawableSpan;
import android.util.AttributeSet;
import android.widget.EditText;

import kg.kaitkulov.androidemoji.R;

/**
 * @author Hieu Rocker (rockerhieu@gmail.com).
 * @author Hani Al Momani (hani.momanii@gmail.com)
 * @author Kanimet Aitkulov (kani.aitkulov@gmail.com)
 */
public class EmojiconEditText extends EditText implements TextWatcher {
    private int mEmojiconSize;
    private int mEmojiconAlignment;
    private int mEmojiconTextSize;
    private boolean mUseSystemDefault = false;
    private int start = -1;
    private int count = -1;


    public EmojiconEditText(Context context) {
        super(context);
        mEmojiconSize = (int) getTextSize();
        mEmojiconTextSize = (int) getTextSize();
    }

    public EmojiconEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public EmojiconEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.Emojicon);
        mEmojiconSize = (int) a.getDimension(R.styleable.Emojicon_emojiconSize, getTextSize());
        mEmojiconAlignment = a.getInt(R.styleable.Emojicon_emojiconAlignment, DynamicDrawableSpan.ALIGN_BASELINE);
        mUseSystemDefault = a.getBoolean(R.styleable.Emojicon_emojiconUseSystemDefault, false);
        a.recycle();
        mEmojiconTextSize = (int) getTextSize();
        setText(getText());
        this.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (this.start == -2) return;
        if (this.start == -1 && count > 0) {
            // When text is added
            this.start = start;
            this.count = count;
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (start >= 0) {
            float textSize = getTextSize();
            int pos = start;

            start = -2;
            EmojiconHandler.addEmojis(getContext(), s, mEmojiconSize,
                    mEmojiconAlignment, mEmojiconTextSize, pos, count, mUseSystemDefault);
            start = -1;
        }
    }

    /**
     * Set the size of emojicon in pixels.
     */
    public void setEmojiconSize(int pixels) {
        mEmojiconSize = pixels;

        updateText();
    }

    private void updateText() {
        EmojiconHandler.addEmojis(getContext(), getText(), mEmojiconSize, mEmojiconAlignment, mEmojiconTextSize, mUseSystemDefault);
    }

    /**
     * Set whether to use system default emojicon
     */
    public void setUseSystemDefault(boolean useSystemDefault) {
        mUseSystemDefault = useSystemDefault;
    }
}
