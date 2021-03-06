package com.example.proyectg.radionipon;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

public class radio extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager =  findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout =  findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_radio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            WebSettings configuracion;
            View rootView = null;
            Log.i("[[SECTION NUMBER]]",String.valueOf(getArguments().getInt(ARG_SECTION_NUMBER)));
            int numero = getArguments().getInt(ARG_SECTION_NUMBER);
            if(numero == 1)
            {
                rootView = inflater.inflate(R.layout.fragment_radio, container, false);
                WebView radio = rootView.findViewById(R.id.radioweb);
                radio.setVisibility(View.INVISIBLE);
                radio.setWebViewClient(new WebViewClient()
                {
                    public void onPageFinished(WebView view, String url) {
                        // do your stuff here
                        ProgressBar barra = view.findViewById(R.id.progressBar);
                        WebView ra = view.findViewById(R.id.radioweb);
                        barra.setVisibility(View.INVISIBLE);
                        ra.setVisibility(View.VISIBLE);
                    }


                });
                configuracion = radio.getSettings();
                configuracion.setJavaScriptEnabled(true);
                radio.loadUrl("http://radio.niponanimeproject.com");
            }
            else if (numero == 2)
            {
                rootView = inflater.inflate(R.layout.fragment_chat, container, false);
                WebView chat =  rootView.findViewById(R.id.chatweb);
                chat.setVisibility(View.VISIBLE);
                chat.setWebViewClient(new WebViewClient());
                configuracion = chat.getSettings();
                configuracion.setJavaScriptEnabled(true);
                chat.loadUrl("https://niponanimeproject.com/chat");
            }
            else if (numero == 3)
            {
                rootView = inflater.inflate(R.layout.fragment_reglas, container, false);
                WebView reglas =  rootView.findViewById(R.id.reglasweb);
                reglas.setVisibility(View.VISIBLE);
                reglas.setWebViewClient(new WebViewClient());
                configuracion = reglas.getSettings();
                configuracion.setJavaScriptEnabled(true);
                reglas.loadUrl("https://niponanimeproject.com/#reglas");
            }


            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }
}
