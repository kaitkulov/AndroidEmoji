package kaitkulov.kg.android_emoji_sample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kg.kaitkulov.androidemoji.Helper.EmojiconTextView;

/**
 * Created by kani on 1/26/17.
 */

public class EmojiAdapter extends RecyclerView.Adapter<EmojiAdapter.ViewHolder> {
    List<String> items;
    private boolean useSystemDefault;

    public EmojiAdapter() {
        this.items = new ArrayList<>();
    }

    public void addItem(String text) {
        items.add(text);
        notifyItemInserted(items.size());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setUseSystemDefault(useSystemDefault);
        holder.textView.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setUseSystemDefault(boolean useSystemDefault) {
        this.useSystemDefault = useSystemDefault;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        EmojiconTextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (EmojiconTextView) itemView.findViewById(R.id.textView);
        }

    }
}
