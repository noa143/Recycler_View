package noa.mitzner.recyclerview;

import android.os.Bundle;
import android.util.Log;

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

        Log.d("Toys", "Number of toys: " + toys.size());
    }

    private void initializeViews(){
        rvToys = findViewById(R.id.rvToys);
    }

    private void setRecyclerView(){
        adapter = new ToyAdapter(this , toys , R.layout.toy_single_layout);
        rvToys.setAdapter(adapter);
        rvToys.setLayoutManager(new LinearLayoutManager(this));
    }
}