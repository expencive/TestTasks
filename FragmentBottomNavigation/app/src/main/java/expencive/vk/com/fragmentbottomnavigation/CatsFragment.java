package expencive.vk.com.fragmentbottomnavigation;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
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
import expencive.vk.com.fragmentbottomnavigation.models.AnimalViewModel;
import expencive.vk.com.fragmentbottomnavigation.models.Animals;
import expencive.vk.com.fragmentbottomnavigation.models.CatsViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatsFragment extends Fragment{

        public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_NUMBER = "number";
    public static final String EXTRA_TITLE = "title";

    private CatsViewModel dashboardViewModel;
    RecyclerView.LayoutManager mLayoutManager;
    private AnimalAdapter mAnimalAdapter;
    RecyclerView recyclerView;

    private static CatsFragment fragment;


    public static CatsFragment newInstance() {
        if (fragment == null){
            fragment = new CatsFragment();
        }

        Bundle bundle = new Bundle();
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(CatsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_cats, container, false);


        recyclerView = root.findViewById(R.id.recycler_view_cats);
        mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dashboardViewModel.getAnimalList().observe(this, new Observer<List<Animal>>() {
            @Override
            public void onChanged(final List<Animal> animals) {

                mAnimalAdapter = new AnimalAdapter(getContext(), (ArrayList<Animal>) animals);
                recyclerView.setAdapter(mAnimalAdapter);

                                mAnimalAdapter.setOnItemClickListener(new AnimalAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent detailIntent = new Intent(getContext(), ItemActivity.class);
                        Animal clickedItem = animals.get(position);

                        detailIntent.putExtra(EXTRA_URL, clickedItem.getImageUrl());
                        detailIntent.putExtra(EXTRA_NUMBER, String.valueOf(position+1));
                        detailIntent.putExtra(EXTRA_TITLE, clickedItem.getImageTitle());

                        startActivity(detailIntent);
                    }
                });

            }
        });
    }


//    public static final String EXTRA_URL = "imageUrl";
//    public static final String EXTRA_NUMBER = "number";
//    public static final String EXTRA_TITLE = "title";
//
//
//    private RecyclerView mRecyclerView;
//    private AnimalAdapter mAnimalAdapter;
//    private ArrayList<Animal> mAnimalList;
//
//    public static int index = -1;
//    RecyclerView.LayoutManager mLayoutManager;
//
//    CatsViewModel animalViewModel;
//
//
//
//
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.fragment_cats, container, false);
//
//        mRecyclerView = rootView.findViewById(R.id.recycler_view_cats);
//
//        mLayoutManager = new LinearLayoutManager(getContext());
//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setLayoutManager(mLayoutManager);
//
//        return rootView;
//
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setRetainInstance(true);
//        animalViewModel = ViewModelProviders.of(this).get(CatsViewModel.class);
//        animalViewModel.getAnimalList().observe(this, new Observer<List<Animal>>() {
//            @Override
//            public void onChanged(@Nullable List<Animal> animals) {
//
//                mAnimalList = (ArrayList<Animal>) animals;
//
//                mAnimalAdapter = new AnimalAdapter(getContext(), mAnimalList);
//                mRecyclerView.setAdapter(mAnimalAdapter);
//                mAnimalAdapter.setOnItemClickListener(new AnimalAdapter.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(int position) {
//                        Intent detailIntent = new Intent(getContext(), ItemActivity.class);
//                        Animal clickedItem = mAnimalList.get(position);
//
//                        detailIntent.putExtra(EXTRA_URL, clickedItem.getImageUrl());
//                        detailIntent.putExtra(EXTRA_NUMBER, String.valueOf(position+1));
//                        detailIntent.putExtra(EXTRA_TITLE, clickedItem.getImageTitle());
//
//                        startActivity(detailIntent);
//                    }
//                });
//
//            }
//        });
//
//        //animalViewModel.setCatsList();
//
//        //loadJson();
//
//
//
//    }
//
//    public void loadJson(){
//
//        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
//        Call<Animals> call;
//        call = apiInterface.getCats();
//
//        call.enqueue(new Callback<Animals>() {
//            @Override
//            public void onResponse(Call<Animals> call, Response<Animals> response) {
//                if (response.isSuccessful() && response.body().getAnimalList()!=null) {
//                    mAnimalList = new ArrayList<>();
//
//
//
//                    List<Animal> animals = response.body().getAnimalList();
//                    mAnimalList = (ArrayList<Animal>) animals;
//
//
//                    mAnimalAdapter = new AnimalAdapter(getContext(), mAnimalList);
//                    mRecyclerView.setAdapter(mAnimalAdapter);
//                    mAnimalAdapter.setOnItemClickListener(new AnimalAdapter.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(int position) {
//                            Intent detailIntent = new Intent(getContext(), ItemActivity.class);
//                            Animal clickedItem = mAnimalList.get(position);
//
//                            detailIntent.putExtra(EXTRA_URL, clickedItem.getImageUrl());
//                            detailIntent.putExtra(EXTRA_NUMBER, String.valueOf(position+1));
//                            detailIntent.putExtra(EXTRA_TITLE, clickedItem.getImageTitle());
//
//                            startActivity(detailIntent);
//                        }
//                    });
//
//                    return;
//                } else{
//                    Toast.makeText(getContext(), "not succesful", Toast.LENGTH_SHORT).show();
//                }
//
//
//            }
//
//            @Override
//            public void onFailure(Call<Animals> call, Throwable t) {
//                Toast.makeText(getContext(), String.valueOf(t.getMessage()), Toast.LENGTH_SHORT).show();
//
//
//            }
//        });
//
//    }
//
//    @Override
//    public void onPause()
//    {
//        super.onPause();
//        index = ((LinearLayoutManager) mRecyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
//    }
//
//    @Override
//    public void onResume()
//    {
//        super.onResume();
//        if(index != -1)
//        {
//            mLayoutManager.scrollToPosition( index);
//        }
//    }



}
