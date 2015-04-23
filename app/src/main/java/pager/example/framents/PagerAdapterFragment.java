package pager.example.framents;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import pager.example.adapters.MyPagerAdapter;
import refresh.example.com.fragmentpagerrefresh.R;
import refresh.example.com.fragmentpagerrefresh.Utils;

/**
 * Created by noor on 10/04/15.
 */
public class PagerAdapterFragment extends Fragment implements View.OnClickListener{

    private static final String TAG = "PagerAdapterFragment";
    private MyPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;
    private ArrayList<Utils.DummyItem> dummyItems;

    /* Avoid non-default constructors in fragments: use a default constructor plus Fragment.setArguments(Bundle) instead and use Type value = getArguments().getType("key") to retrieve back the values in the bundle in onCreateView()*/
    public PagerAdapterFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sample,container, false);
        rootView.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
        Button buttonThumb = (Button) rootView.findViewById(R.id.button_thumb);
        Button buttonFull = (Button) rootView.findViewById(R.id.button_full);
        buttonThumb.setOnClickListener(this);
        buttonFull.setOnClickListener(this);
        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        mViewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        dummyItems=new ArrayList<>();
        dummyItems.addAll(Utils.getThumbImageList());
        mPagerAdapter = new MyPagerAdapter(dummyItems, getActivity());
        mViewPager.setAdapter(mPagerAdapter);
        return  rootView;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_thumb:
                dummyItems.clear();
                dummyItems.addAll(Utils.getThumbImageList());
                mPagerAdapter.notifyDataSetChanged();
                break;
            case R.id.button_full:
                dummyItems.clear();
                dummyItems.addAll(Utils.getFullImageList());
                mPagerAdapter.notifyDataSetChanged();
                break;

        }
    }
}
