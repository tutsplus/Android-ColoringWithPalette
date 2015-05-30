package com.tutsplus.palette;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private SwatchAdapter mAdapter;

    private TextView mVibrant;
    private TextView mLightVibrant;
    private TextView mDarkVibrant;
    private TextView mMuted;
    private TextView mLightMuted;
    private TextView mDarkMuted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView( R.layout.activity_main );

        initViews();

        mAdapter = new SwatchAdapter( this );
        mListView.setAdapter( mAdapter );

        Bitmap bitmap = BitmapFactory.decodeResource( getResources(), R.drawable.union_station );
        Palette.from( bitmap ).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {

                setViewSwatch( mVibrant, palette.getVibrantSwatch() );
                setViewSwatch( mLightVibrant, palette.getLightVibrantSwatch() );
                setViewSwatch( mDarkVibrant, palette.getDarkVibrantSwatch() );
                setViewSwatch( mMuted, palette.getMutedSwatch() );
                setViewSwatch( mLightMuted, palette.getLightMutedSwatch() );
                setViewSwatch( mDarkMuted, palette.getDarkMutedSwatch() );

                for (Palette.Swatch swatch : palette.getSwatches()) {
                    mAdapter.add(swatch);
                }

                mAdapter.sortSwatches();
                mAdapter.notifyDataSetChanged();


            }
        });
    }

    public void setViewSwatch( TextView view, Palette.Swatch swatch ) {
        if( swatch != null ) {
            view.setTextColor( swatch.getTitleTextColor() );
            view.setBackgroundColor( swatch.getRgb() );
            view.setVisibility( View.VISIBLE );
        } else {
            view.setVisibility( View.GONE );
        }
    }

    private void initViews() {
        mListView = (ListView) findViewById( R.id.list );
        mVibrant = (TextView) findViewById( R.id.vibrant );
        mLightVibrant = (TextView) findViewById( R.id.light_vibrant );
        mDarkVibrant = (TextView) findViewById( R.id.dark_vibrant );
        mMuted = (TextView) findViewById( R.id.muted );
        mLightMuted = (TextView) findViewById( R.id.light_muted );
        mDarkMuted = (TextView) findViewById( R.id.dark_muted );
    }
}
