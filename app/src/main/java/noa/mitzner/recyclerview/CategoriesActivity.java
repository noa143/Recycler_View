package noa.mitzner.recyclerview;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import noa.mitzner.model.Categories;
import noa.mitzner.model.Category;
import noa.mitzner.model.Toy;
import noa.mitzner.model.Toys;
import noa.mitzner.viewmodel.ToyCategoryViewModel;

public class CategoriesActivity extends AppCompatActivity {
        private RecyclerView recyclerViewCategory;
        private EditText etCategoryName;
        private ImageView ivX;
        private ImageView ivV;
        private CategoryAdapter adapter;
        private ToyCategoryViewModel toyCategoryViewModel;
        private FloatingActionButton btnPlus;
        private ConstraintLayout constraintLayout;
        private Category category;

        private final View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CategoryAdapter", "Item clicked");
            }
        };

        private final View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d("CategoryAdapter", "Item long clicked");
                Category category = (Category) v.getTag();
                showCategoryUpdateDialog(category);
                return true;
            }
        };

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.categories);
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_categories), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
            initializeViews();
            setupRecyclerView();
            setupViewModel();
            setupListeners();
        }

        private void initializeViews() {
            recyclerViewCategory = findViewById(R.id.recyclerViewCategory);
            etCategoryName = findViewById(R.id.etCategoryName);
            ivX = findViewById(R.id.ivX);
            ivV = findViewById(R.id.ivV);
            btnPlus = findViewById(R.id.btnPlus);
            constraintLayout = findViewById(R.id.constraintLayout);
        }

        private void setupRecyclerView() {
            adapter = new CategoryAdapter(this , null , listener, longClickListener);
            recyclerViewCategory.setLayoutManager(new LinearLayoutManager(this));
            recyclerViewCategory.setAdapter(adapter);
        }

        private void setupListeners() {
            ivV.setOnClickListener(v -> {
                String categoryName = etCategoryName.getText().toString().trim();
                if (validate(categoryName)) {
                    Category newCategory = new Category(categoryName);
                    toyCategoryViewModel.addCategory((newCategory));
                    etCategoryName.setText("");
                }
            });
            btnPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    constraintLayout.setVisibility(View.VISIBLE);
                }
            });
            ivX.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(constraintLayout.getVisibility() == View.VISIBLE )
                        constraintLayout.setVisibility(View.GONE);
                }
            });
        }

        private void setupViewModel() {
            toyCategoryViewModel = new ViewModelProvider(this).get(ToyCategoryViewModel.class);

            toyCategoryViewModel.getToyCategoriesLiveData().observe(this, toyCategories -> {
                Log.d("CategoriesActivity", "Categories updated, new size: " + toyCategories.size());
                adapter.setCategories(toyCategories);
            });
        }
        private boolean validate(String categoryName) {
            if (categoryName == null || categoryName.trim().isEmpty()) {
                Toast.makeText(this, "the name of the category cannot be null", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (categoryName.length() <= 2) {
                Toast.makeText(this, "the name of the category cant be only two letters", Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;
        }
    public void addCategory(Category categoryName) {
        if (!validate(categoryName.getName())) {
            return;
        }

        if (toyCategoryViewModel.exist(categoryName)) {
            Toast.makeText(this, "This category already exists", Toast.LENGTH_SHORT).show();
            return;
        }
        toyCategoryViewModel.addCategory(categoryName);
        Toast.makeText(this, "The category has been added", Toast.LENGTH_SHORT).show();
        etCategoryName.setText("");
    }

    private void showCategoryUpdateDialog(Category category) {
        final EditText editText = new EditText(this);
        editText.setText(category.getName());

        new AlertDialog.Builder(this)
                .setTitle("Update Category")
                .setView(editText)
                .setPositiveButton("Update", (dialog, which) -> {
                    String updatedName = editText.getText().toString().trim();
                    if (!updatedName.isEmpty() && !updatedName.equals(category.getName())) {
                        Category updatedCategory = new Category(updatedName);
                        toyCategoryViewModel.Update(category, updatedCategory);
                    } else {
                        Toast.makeText(CategoriesActivity.this, "No change in category name", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}

