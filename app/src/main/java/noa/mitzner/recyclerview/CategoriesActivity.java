package noa.mitzner.recyclerview;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        private ImageView ivCategories;
        private List<String> categoryList;
        private CategoryAdapter adapter;
        private ToyCategoryViewModel toyCategoryViewModel;

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
            ivCategories = findViewById(R.id.ivCategories);
        }

        private void setupRecyclerView() {
            adapter = new CategoryAdapter(this , null , listener, longClickListener);
            recyclerViewCategory.setLayoutManager(new LinearLayoutManager(this));
            recyclerViewCategory.setAdapter(adapter);
        }

        private void setupListeners() {
            ivCategories.setOnClickListener(v -> {
                String category = etCategoryName.getText().toString().trim();
                if (!category.isEmpty()) {
                    Category newCategory = new Category(category);
                    toyCategoryViewModel.addCategory(newCategory);
                    etCategoryName.setText("");
                }
            });
        }

        private void setupViewModel() {
            toyCategoryViewModel = new ViewModelProvider(this).get(ToyCategoryViewModel.class);

            toyCategoryViewModel.getToyCategoriesLiveData().observe(this, toyCategories -> {
                adapter.setCategories(toyCategories);
            });
        }
    }

