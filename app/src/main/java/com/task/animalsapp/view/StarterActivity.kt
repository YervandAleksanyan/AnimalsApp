package com.task.animalsapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.google.android.material.tabs.TabLayout
import com.task.animalsapp.R
import com.task.animalsapp.view.cats.CatsFragment
import com.task.animalsapp.view.dogs.DogsFragment
import kotlinx.android.synthetic.main.activity_starter.*


class StarterActivity : AppCompatActivity() {

    companion object {
        var tabPosition = 0
        const val TAB_POSITION_KEY = "tab_position_key"
        const val CAT_FRAG_TAG = "cat_frag_tag"
        const val DOG_FRAG_TAG = "dog_frag_tag"
    }

    private var catsFragment: Fragment? = null
    private var dogsFragment: Fragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_starter)
        initListener()
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                catsFragment = CatsFragment.newInstance()
                add(R.id.container, catsFragment!!, CAT_FRAG_TAG)
            }
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(TAB_POSITION_KEY, tabPosition)
        catsFragment?.let {
            supportFragmentManager.putFragment(outState, CAT_FRAG_TAG, it)
        }
        dogsFragment?.let {
            supportFragmentManager.putFragment(outState, DOG_FRAG_TAG, it)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        catsFragment =
            supportFragmentManager.getFragment(savedInstanceState!!, CAT_FRAG_TAG)
        dogsFragment =
            supportFragmentManager.getFragment(savedInstanceState, DOG_FRAG_TAG)
        tabs.getTabAt(savedInstanceState.getInt(TAB_POSITION_KEY))?.select()
    }

    private fun initListener() {
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                tabPosition = tab.position
                when (tabPosition) {
                    0 -> {
                        supportFragmentManager.commit {
                            show(catsFragment!!)
                            hide(dogsFragment!!)
                        }
                    }
                    1 -> {
                        if (dogsFragment == null) {
                            supportFragmentManager.commit {
                                dogsFragment = DogsFragment.newInstance()
                                add(R.id.container, dogsFragment!!, DOG_FRAG_TAG)
                            }
                        } else {
                            supportFragmentManager.commit {
                                hide(catsFragment!!)
                                show(dogsFragment!!)
                            }
                        }

                    }
                }
            }

        })
    }
}
