package sp.udaan.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sp.udaan.R;

public class MainFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;
    String eventCategory="Literary Arts";

    public MainFragment(){}

    public static MainFragment newInstance(){
        MainFragment mainFragment = new MainFragment();
        return mainFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,container,false);
        viewPager = (ViewPager)view.findViewById(R.id.fragment_viewPager_tabLayout);
        tabLayout =(TabLayout)view.findViewById(R.id.main_fragment_tabLayout);

        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));
        //viewPager.setOffscreenPageLimit(0);

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });
        return view;
    }


    class MyAdapter extends FragmentPagerAdapter{

        MyAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            try
            {
                eventCategory=getActivity().getIntent().getStringExtra("EventCategory");
                if (eventCategory==null)
                {
                    eventCategory="Literary Arts";
                }
            }catch (Exception e)
            {
                eventCategory="Literary Arts";
            }

            EventListFragment megaEventPage = EventListFragment.newInstance(eventCategory);

            switch (position) {
                case 0:
                    return megaEventPage;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            try
            {
                eventCategory=getActivity().getIntent().getStringExtra("EventCategory");
                if (eventCategory==null)
                {
                    eventCategory="Literary Arts";
                }
            }catch (Exception e)
            {
                eventCategory="Literary Arts";
            }
            switch (position){
                case 0:return eventCategory;
            }
            return null;
        }
    }
}
