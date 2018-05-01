package com.example.eduadogurrola.yavax.CCamara;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.eduadogurrola.yavax.Pestañas.PCamara;
import com.example.eduadogurrola.yavax.Pestañas.PInicio;

/**
 * Created by Eduado Gurrola on 25/04/2018.
 */

public class PeggarFRAN  extends FragmentStatePagerAdapter {

    public PeggarFRAN(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){

            case 0:
                PCamara peg=new PCamara();
                return peg;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 1;
    }
}
