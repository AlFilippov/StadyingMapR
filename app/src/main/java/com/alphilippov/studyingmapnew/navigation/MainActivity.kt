package com.alphilippov.studyingmapnew.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.alphilippov.studyingmapnew.R
import com.alphilippov.studyingmapnew.features.searchcourses.filter.FilterCoursesFragment
import kotlinx.android.synthetic.main.activity_navigation.*
import moxy.MvpAppCompatActivity

class MainActivity : MvpAppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        nav_bar.selectedItemId = R.id.navigation_favorites
        nav_bar.setOnNavigationItemReselectedListener { item ->
            when (item.itemId) {
                R.id.navigation_favorites -> replaceFragment(FilterCoursesFragment.newInstance(), FilterCoursesFragment.TAG)

            }
        }
    }


    private fun replaceFragment(fragment: Fragment, tag: String) {
        val currentFragment = getCurrentFragment()
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()

        if (currentFragment != null && currentFragment.tag == tag) {
            transaction.replace(R.id.main_container, currentFragment, tag)
                    .commit()
            return
        }
        val resultFragment = fragmentManager.findFragmentByTag(tag)

        if (resultFragment == null) {
            transaction.replace(R.id.main_container, fragment, tag)
        } else {
            transaction.replace(R.id.main_container, resultFragment, tag)
        }
        transaction.commit()
    }

    private fun getCurrentFragment(): Fragment? {
        for (fragment in supportFragmentManager.fragments) {
            if (fragment.isVisible) {
                return fragment
            }
        }
        return null
    }
}