package piashsotware.ltd.movietune;


import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import piashsotware.ltd.movietune.fragment.MainFragment;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* android.app.FragmentManager fm = getFragmentManager();
        android.app.FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.activity_main, new MainFragment());
        ft.commit();*/
       addFragment(new MainFragment(), true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }



    public void addFragment(android.support.v4.app.Fragment newFragment, boolean isStacked) {
        Log.i("tadd", "addFragment: ");
        FragmentManager fragmentManager = getCurrentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        android.support.v4.app.Fragment availableFragment = fragmentManager.findFragmentByTag(newFragment.getClass().getName());
        if (isStacked && availableFragment != null) {
            int stackPosition = isFragmentInBackStack(fragmentManager, newFragment.getClass().getName());

            //if current fragment exists already in back stack, pop it from stack
            if (stackPosition != -1 && fragmentManager.getBackStackEntryCount() > stackPosition) {
                FragmentManager.BackStackEntry first = fragmentManager.getBackStackEntryAt(stackPosition);
                fragmentManager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        }

        //add new fragment to frame layout
        fragmentTransaction.add(R.id.activity_main, newFragment, newFragment.getClass().getName());
        fragmentTransaction.addToBackStack(newFragment.getClass().getName());
        fragmentTransaction.commitAllowingStateLoss();

    }
    public void clearBackStack() {
        getCurrentFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    public void removeFragment(){
        getCurrentFragmentManager().popBackStack();
    }

    protected FragmentManager getCurrentFragmentManager() {
        return getSupportFragmentManager();
    }
    public int isFragmentInBackStack(FragmentManager fragmentManager, String fragmentTag) {
        for (int entry = 0; entry < fragmentManager.getBackStackEntryCount(); entry++) {
            if (fragmentTag.equals(fragmentManager.getBackStackEntryAt(entry).getName())) {
                return entry;
            }
        }
        return -1;
    }

    private boolean backOptionFragment(String currentFragId) {
        return !currentFragId.equalsIgnoreCase(MainActivity.class.getName());
    }

}
