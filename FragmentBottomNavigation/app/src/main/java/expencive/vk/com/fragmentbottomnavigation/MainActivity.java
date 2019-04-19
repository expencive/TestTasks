package expencive.vk.com.fragmentbottomnavigation;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    Fragment selectedFrament = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);


    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch (menuItem.getItemId()){
                case R.id.nav_home:
                    selectedFrament = new CatsFragment();
                    break;
                case R.id.nav_favorits:
                    selectedFrament = new DogsFragment();
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


}
