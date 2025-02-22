package noa.mitzner.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import noa.mitzner.model.Categories;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private List<String> categoryList;
    private View.OnClickListener listener;
    private View.OnLongClickListener longClickListener;

    public CategoryAdapter(CategoriesActivity categoriesActivity, List<String> categoryList,
                           View.OnClickListener listener, View.OnLongClickListener longClickListener) {
        this.categoryList = categoryList != null ? categoryList : new ArrayList<>();
        this.listener = listener;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String category = categoryList.get(position);
        holder.textView.setText(category);
        holder.itemView.setOnClickListener(listener);
        holder.itemView.setOnLongClickListener(longClickListener);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }

    public void setCategories(Categories categories) {
        if (categories != null) {
            categoryList.clear();
            for (int i = 0; i < categories.size(); i++) {
                categoryList.add(categories.get(i).getName());
            }
            notifyDataSetChanged();
        }
    }
}

