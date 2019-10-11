package expencive.vk.com.fragmentbottomnavigation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static expencive.vk.com.fragmentbottomnavigation.CatsFragment.EXTRA_NUMBER;
import static expencive.vk.com.fragmentbottomnavigation.CatsFragment.EXTRA_TITLE;
import static expencive.vk.com.fragmentbottomnavigation.CatsFragment.EXTRA_URL;


public class ItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        String number = intent.getStringExtra(EXTRA_NUMBER);
        String title = intent.getStringExtra(EXTRA_TITLE);

        ImageView imageView = (ImageView) findViewById(R.id.image_view_detail);
        TextView textViewNumber = (TextView) findViewById(R.id.text_view_number_detail);
        TextView textViewTitle = (TextView) findViewById(R.id.text_view_title_detail);

        Picasso.get().load(imageUrl).fit().centerInside().into(imageView);
        textViewNumber.setText(number);
        textViewTitle.setText(title);
    }
}
