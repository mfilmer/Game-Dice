package com.example.gamedice;

import java.util.Locale;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
     * will keep every loaded fragment in memory. If this becomes too memory
     * intensive, it may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the action bar.
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the
        // primary sections of the app.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a DummySectionFragment (defined as a static inner class
            // below) with the page number as its lone argument.
        	Fragment fragment;
        	switch (position) {
	        	case 0:
	        		fragment = new TaoFragment();
	        		break;
	        	case 1:
	        		fragment = new CurseFragment();
	        		break;
	        	default:
	        		fragment = new TaoFragment();
        	}
            Bundle args = new Bundle();
            //args.putInt(CurseFragment.ARG_SECTION_NUMBER, position + 1);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.taoTitle).toUpperCase(l);
                case 1:
                    return getString(R.string.curseTitle).toUpperCase(l);
            }
            return null;
        }
    }
    
    public static class CurseFragment extends Fragment {
    	@Override
    	public View onCreateView(LayoutInflater inflater, ViewGroup container,
    			Bundle savedInstanceState) {
    		return inflater.inflate(R.layout.curse_fragment, container, false);
    	}
    }
    
    public static class TaoFragment extends Fragment {
    	@Override
    	public View onCreateView(LayoutInflater inflater, ViewGroup container,
    			Bundle savedInstanceState) {
    		return inflater.inflate(R.layout.tao_fragment, container, false);
    	}
    }
    
    public void rollTaoDice (View view) {
    	//DrawingPanel panel = (DrawingPanel) findViewById(R.id.taoCanvas);
    	//panel.invalidate();

    	//findViewById(R.id.taoCanvas).invalidate();
    	
    	DrawingPanel drawingPanel = (DrawingPanel) findViewById(R.id.taoCanvas);
    	
    	switch(view.getId()) {
    	case R.id.roll1:
    		drawingPanel.rollDice(1);
    		break;
    	case R.id.roll2:
    		drawingPanel.rollDice(2);
    		break;
    	case R.id.roll3:
    		drawingPanel.rollDice(3);
    		break;
    	case R.id.roll4:
    		drawingPanel.rollDice(4);
    		break;
    	default:
    		throw new RuntimeException("Unknown Button ID");
    	}
    }
    
    public void rollCurseDie (View view) {
    	ImageView image = (ImageView) findViewById(R.id.curseDieImage);
    	image.setImageResource(R.drawable.testimage);
    }
    
    public void clearSelected (View view) {
    	final DrawingPanel canvas = (DrawingPanel) findViewById(R.id.taoCanvas);
    	canvas.clearSelected();
    }
    
    public void reRoll (View view) {
    	final DrawingPanel canvas = (DrawingPanel) findViewById(R.id.taoCanvas);
    	canvas.reRollDice();
    }
}
