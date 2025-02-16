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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import noa.mitzner.model.Categories;
import noa.mitzner.model.Category;
import noa.mitzner.model.Toy;
import noa.mitzner.model.Toys;

public class CategoriesActivity extends AppCompatActivity {
    private RecyclerView recyclerViewCategory;
    private EditText etCategoryName;
    private ImageView ivCategories;
    private List<String> categoryList;
    private CategoryAdapter adapter;
    private Category category;

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

        categoryList = new ArrayList<>();
        categoryList.add("Cars");
        categoryList.add("Doll");
        categoryList.add("Katan");
        categoryList.add("Monapol");
        categoryList.add("Jungle spid");

        initializeViews();
        setupRecyclerView();
        setupListeners();
    }

    private void initializeViews()
    {
        recyclerViewCategory = findViewById(R.id.recyclerViewCategory);
        etCategoryName = findViewById(R.id.etCategoryName);
        ivCategories = findViewById(R.id.ivCategories);
    }

    private void setupRecyclerView() {
        adapter = new CategoryAdapter(categoryList);
        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCategory.setAdapter(adapter);
    }

    private void setupListeners() {
        ivCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = etCategoryName.getText().toString().trim();
                if (!category.isEmpty()) {
                    categoryList.add(category);
                    adapter.notifyItemInserted(categoryList.size() - 1);
                    etCategoryName.setText("");
                }
            }
        });
    }
}
