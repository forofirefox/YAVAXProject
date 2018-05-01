package com.example.eduadogurrola.yavax.Pestañas;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.WindowManager;

/**
 * Created by Eduado Gurrola on 07/02/2018.
 */

public class Pegga extends FragmentStatePagerAdapter {
    int tabCount;

    public Pegga(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount= tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){

            case 0:
                PInicio pInicio=new PInicio();

                return pInicio;
            case 1:
                PAmigos pAmigos=new PAmigos();

                return pAmigos;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
