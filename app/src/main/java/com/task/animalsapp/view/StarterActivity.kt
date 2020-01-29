package com.task.animalsapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.google.android.material.tabs.TabLayout
import com.task.animalsapp.R
import com.task.animalsapp.view.cat.CatsFragment
import com.task.animalsapp.view.dog.DogsFragment
import kotlinx.android.synthetic.main.activity_starter.*


class StarterActivity : AppCompatActivity() {

    companion object {
        var tabPosition = 0
        const val TAB_POSITION_KEY = "tab_position_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_starter)
        initListener()
        supportFragmentManager.commit {
            replace(R.id.container, CatsFragment.newInstance())
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(TAB_POSITION_KEY, tabPosition)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        tabs.getTabAt(savedInstanceState?.getInt(TAB_POSITION_KEY) ?: 0)?.select()
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
                            replace(R.id.container, CatsFragment.newInstance())
                        }
                    }
                    1 -> {
                        supportFragmentManager.commit {
                            replace(R.id.container, DogsFragment.newInstance())
                        }
                    }
                }
            }

        })
    }
}
