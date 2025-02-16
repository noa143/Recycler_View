package noa.mitzner.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import noa.mitzner.model.Toy;
import noa.mitzner.model.Toys;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvToys;
    private Toys toys;
    private ToyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initializeViews();
        getAllToys();
        setRecyclerView();
    }
    private void getAllToys(){
        toys = new Toys();
        toys.add(new Toy("Car" , 50));
        toys.add(new Toy("Doll" , 100));
        toys.add(new Toy("Katan" , 115));
        toys.add(new Toy("Monapol" , 150));
        toys.add(new Toy("Jungle spid" , 89));
        toys.add(new Toy("Kokoriko" , 200));

        Log.d("Toys", "Number of toys: " + toys.size());
    }

    private void initializeViews(){
        rvToys = findViewById(R.id.rvToys);
    }

    private void setRecyclerView(){
        ToyAdapter.OnItemClickListener listener = new ToyAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(Toy toy) {
                Toast.makeText(MainActivity.this, "Name: " + toy.getName(), Toast.LENGTH_LONG).show();
            }
        };
        ToyAdapter.OnItemLongClickListener longListener = new ToyAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClicked(Toy toy) {
                Toast.makeText(MainActivity.this, "Price: " +
                        String.valueOf(toy.getPrice()), Toast.LENGTH_LONG).show();
                return true;
            }
        };
        adapter = new ToyAdapter(this , toys , R.layout.toy_single_layout , listener , longListener);
        rvToys.setAdapter(adapter);
        rvToys.setLayoutManager(new LinearLayoutManager(this));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // Handle menu item clicks
        if (id == R.id.mnuCategory) {
            Intent intent = new Intent(this , CategoriesActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.mnuExit) {
            Toast.makeText(this, "Exit", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}