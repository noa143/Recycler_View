package noa.mitzner.recyclerview;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import noa.mitzner.model.Categories;
import noa.mitzner.model.Category;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private Context context;
    private Categories categoryList;
    private View.OnClickListener listener;
    private View.OnLongClickListener longClickListener;

    public CategoryAdapter( Context context, Categories categoryList,
                           View.OnClickListener listener, View.OnLongClickListener longClickListener ) {
        this.listener = listener;
        this.longClickListener = longClickListener;
        this.categoryList = categoryList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = categoryList.get(position);
            String categoryName = category.getName();
            holder.textView.setText(categoryName);
        holder.itemView.setOnClickListener(listener);
        holder.itemView.setOnLongClickListener(v -> {
           showUpdateDeleteDialog(category);
            return true;
        });
    }

    @Override
    public int getItemCount() {

        return categoryList != null ? categoryList.size() : 0;
    }

    public void setCategories(Categories categories) {
        this.categoryList = categories;
        notifyDataSetChanged();
    }

    public void removeCategory(int position) {
        if (categoryList != null && position >= 0 && position < categoryList.size()) {
            categoryList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void updateCategory(int position, String newName) {
        if (categoryList != null && position >= 0 && position < categoryList.size()) {
            Category category = categoryList.get(position);
            if (category != null) {
                category.setName(newName);
                notifyItemChanged(position);
            }
        }
    }

    public void showUpdateDeleteDialog(Category category) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose Action")
                .setItems(new String[]{"Update", "Remove"}, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            showUpdateDialog(category);
                            break;
                        case 1:
                            int position = categoryList.indexOf(category);
                            if (position != -1) {
                                removeCategory(position);
                            }
                            break;
                    }
                })
                .show();
    }

    private void showUpdateDialog(Category category) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Update Category");

        // Set up the input
        final EditText input = new EditText(context);
        input.setText(category.getName());
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("Update", (dialog, which) -> {
            String newName = input.getText().toString().trim();
            if (!newName.isEmpty()) {
                int position = categoryList.indexOf(category);
                if (position != -1) {
                    updateCategory(position, newName);
                }
            }
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            TextView textView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(android.R.id.text1);
            }
        }

}

