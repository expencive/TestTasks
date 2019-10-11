package expencive.vk.com.fragmentbottomnavigation;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    Fragment selectedFrament = null;
    String selectedFrag = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState!=null){
            selectedFrag = savedInstanceState.getString("selectedFrag");
            if (selectedFrag.equals("Dogs")){
                selectedFrament = DogsFragment.newInstance();
            }else {
                selectedFrament = CatsFragment.newInstance();
            }
        }else {
            selectedFrament = CatsFragment.newInstance();
        }

        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        setBottomNavigation();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.nav_home:
                    selectedFrament = CatsFragment.newInstance();
                    selectedFrag = "Cats";
                    break;
                case R.id.nav_favorits:
                    selectedFrament = DogsFragment.newInstance();
                    selectedFrag = "Dogs";
                    break;
            }
            setBottomNavigation();
            return true;
        }

    };

    private void setBottomNavigation(){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                selectedFrament).commit();
    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("selectedFrag", selectedFrag);
        super.onSaveInstanceState(outState);
    }
}
