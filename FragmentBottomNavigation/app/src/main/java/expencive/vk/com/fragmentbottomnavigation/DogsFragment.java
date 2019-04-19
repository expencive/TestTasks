package expencive.vk.com.fragmentbottomnavigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import expencive.vk.com.fragmentbottomnavigation.adapter.AnimalAdapter;
import expencive.vk.com.fragmentbottomnavigation.api.ApiClient;
import expencive.vk.com.fragmentbottomnavigation.api.ApiInterface;
import expencive.vk.com.fragmentbottomnavigation.models.Animal;
import expencive.vk.com.fragmentbottomnavigation.models.Animals;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static expencive.vk.com.fragmentbottomnavigation.CatsFragment.EXTRA_NUMBER;
import static expencive.vk.com.fragmentbottomnavigation.CatsFragment.EXTRA_TITLE;
import static expencive.vk.com.fragmentbottomnavigation.CatsFragment.EXTRA_URL;

public class DogsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private AnimalAdapter mAnimalAdapter;
    private ArrayList<Animal> mAnimalList;

    public static int index = -1;
    RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dogs, container, false);

        mRecyclerView = rootView.findViewById(R.id.recycler_view_dogs);

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        return rootView;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadJson();




    }

    public void loadJson(){

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);


        Call<Animals> call;

        call = apiInterface.getDogs();

        call.enqueue(new Callback<Animals>() {
            @Override
            public void onResponse(Call<Animals> call, Response<Animals> response) {
                if (response.isSuccessful() && response.body().getAnimalList()!=null) {

                    List<Animal> animals = response.body().getAnimalList();
                    mAnimalList = (ArrayList<Animal>) animals;

                    mAnimalAdapter = new AnimalAdapter(getContext(), mAnimalList);
                    mRecyclerView.setAdapter(mAnimalAdapter);
                    mAnimalAdapter.setOnItemClickListener(new AnimalAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            Intent detailIntent = new Intent(getContext(), ItemActivity.class);
                            Animal clickedItem = mAnimalList.get(position);

                            detailIntent.putExtra(EXTRA_URL, clickedItem.getImageUrl());
                            detailIntent.putExtra(EXTRA_NUMBER, String.valueOf(position+1));
                            detailIntent.putExtra(EXTRA_TITLE, clickedItem.getImageTitle());

                            startActivity(detailIntent);
                        }
                    });





                    return;
                } else{
                    Toast.makeText(getContext(), "not succesful", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<Animals> call, Throwable t) {
                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });

    }


    @Override
    public void onPause()
    {
        super.onPause();
        index = ((LinearLayoutManager) mRecyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();


    }

    @Override
    public void onResume()
    {
        super.onResume();
        if(index != -1)
        {
            mLayoutManager.scrollToPosition( index);
        }
    }

}
