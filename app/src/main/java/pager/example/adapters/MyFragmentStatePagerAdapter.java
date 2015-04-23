package pager.example.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

import pager.example.framents.SampleFragment;
import refresh.example.com.fragmentpagerrefresh.Utils;

/**
 * Created by noor on 31/03/15.
 */
public class MyFragmentStatePagerAdapter extends FragmentStatePagerAdapter {
    private static final String TAG = "FragmentStatPgrAdapter";
    private ArrayList<Utils.DummyItem> mImageItemList;
    private FragmentManager mFragmentManager;

    public MyFragmentStatePagerAdapter(FragmentManager fm,ArrayList<Utils.DummyItem> imageItemList) {
        super(fm);
        this.mImageItemList = imageItemList;
        this.mFragmentManager = fm;
    }

    @Override
    public int getCount() {
        return mImageItemList.size();
    }

    @Override
    public Fragment getItem(int position) {

        //We are doing this only for checking the total number of fragments in the fragment manager.
        List<Fragment> fragmentsList= mFragmentManager.getFragments();
        int size = 0;
        if(fragmentsList != null){
            size = fragmentsList.size();
        }
        Utils.DummyItem dummyItem = mImageItemList.get(position);
        Log.i(TAG, "********getItem position:" + position + " size:" + size + " title:" + dummyItem.getImageTitle() + " url:" + dummyItem.getImageUrl());


        //Create a new instance of the fragment and return it.
        SampleFragment sampleFragment = (SampleFragment) SampleFragment.getInstance(/*dummyItem.getImageUrl(), dummyItem.getImageTitle()*/);
        //We will not pass the data through bundle because it will not gets updated by calling notifyDataSetChanged()  method. We will do it through getter and setter.
        sampleFragment.setDummyItem(dummyItem);
        return sampleFragment;
    }


    /**
     * This method is only gets called when we invoke {@link #notifyDataSetChanged()} on this adapter.
     * Returns the index of the currently active fragments.
     * There could be minimum two and maximum three active fragments(suppose we have 3 or more  fragments to show).
     * If there is only one fragment to show that will be only active fragment.
     * If there are only two fragments to show, both will be in active state.
     * PagerAdapter keeps left and right fragments of the currently visible fragment in ready/active state so that it could be shown immediate on swiping.
     * Currently Active Fragments means one which is currently visible one is before it and one is after it.
     *
     * @param object Active Fragment reference
     * @return Returns the index of the currently active fragments.
     */
    @Override
    public int getItemPosition(Object object) {
        List<Fragment> fragmentsList= mFragmentManager.getFragments();
        SampleFragment fragment = (SampleFragment)object;
        Utils.DummyItem dummyItem = fragment.getDummyItem();
        int position = mImageItemList.indexOf(dummyItem);

        /*_____________________________________________*/
        /*Only for logging purpose*/
        int size = fragmentsList.size();
        Utils.DummyItem dummyItemNew = null;
        if(position>=0){
            dummyItemNew = mImageItemList.get(position);
        }
        Log.i(TAG, "************getItemPosition Old_Item: Position:"+position+" size:"+size+" title:"+dummyItem.getImageTitle()+" url:"+dummyItem.getImageUrl());
        if(dummyItemNew !=null){
            Log.i(TAG, "************getItemPosition New_Item: Position:"+position+" title:"+dummyItemNew.getImageTitle()+" url:"+dummyItemNew.getImageUrl());
        }
        /*_____________________________________________*/


        if (position >= 0) {
            // The current data matches the data in this active fragment, so let it be as it is.
            return position;
        } else {
            // Returning POSITION_NONE means the current data does not matches the data this fragment is showing right now.  Returning POSITION_NONE constant will force the fragment to redraw its view layout all over again and show new data.
            return POSITION_NONE;
        }
    }
}
