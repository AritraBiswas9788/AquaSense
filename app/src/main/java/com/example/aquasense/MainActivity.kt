package com.example.aquasense

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.aquasense.Adapter.FragmentBodyAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.wwdablu.soumya.lottiebottomnav.*

class MainActivity : AppCompatActivity(), ILottieBottomNavCallback {
    private lateinit var lottieNav: LottieBottomNav
    private lateinit var viewPager: ViewPager2
    private lateinit var fab: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lottieNav = findViewById(R.id.bottomNav)
        viewPager = findViewById(R.id.viewPager)
        fab = findViewById(R.id.fab)
        //Set font item
        var fontItem: FontItem = FontBuilder.create("HOME")
            .selectedTextColor(Color.WHITE)
            .unSelectedTextColor(Color.GRAY)
            .selectedTextSize(12) //SP
            .unSelectedTextSize(10) //SP
            .setTypeface(ResourcesCompat.getFont(this, R.font.comfortaa_bold))
            .build()
        //Menu Dashboard
        val item1: MenuItem =
            MenuItemBuilder.create("home.json", MenuItem.Source.Assets, fontItem, "dash")
                .pausedProgress(1.0f)
                .loop(false)
                .build()
        //Menu Gifts
        fontItem = FontBuilder.create(fontItem).setTitle("ISSUES").build()
        val item2: MenuItem = MenuItemBuilder.createFrom(item1, fontItem)
            .selectedLottieName("issues.json")
            .unSelectedLottieName("issues.json")
            .pausedProgress(0.9f)
            .loop(false)
            .build()
        //Menu Mail
        fontItem = FontBuilder.create(fontItem).setTitle("").build()
        val blankItem: MenuItem = MenuItemBuilder.createFrom(item2, fontItem)
            .selectedLottieName("blank.json")
            .unSelectedLottieName("blank.json")
            .loop(false)
            .build()
        fontItem = FontBuilder.create(fontItem).setTitle("SOLUTIONS").build()
        val item3: MenuItem = MenuItemBuilder.createFrom(item1, fontItem)
            .selectedLottieName("checklist.json")
            .unSelectedLottieName("checklist.json")
            .pausedProgress(0.6f)
            .loop(true)
            .build()
        //Menu Settings
        fontItem = FontBuilder.create(fontItem).setTitle("SETTINGS").build()
        val item4: MenuItem = MenuItemBuilder.createFrom(item1, fontItem)
            .selectedLottieName("settings.json")
            .unSelectedLottieName("settings.json")
            .loop(false)
            .build()
        val list: ArrayList<MenuItem> = ArrayList(5)
        list.add(item1)
        list.add(item2)
        list.add(blankItem)
        list.add(item3)
        list.add(item4)
        val fragmentAdapter = FragmentBodyAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = fragmentAdapter
        viewPager.isUserInputEnabled = false
        lottieNav.setCallback(this)
        lottieNav.setMenuItemList(list)
        lottieNav.selectedIndex = 0
        fab.setImageResource(R.drawable.mapiconfab)

    }

    override fun onMenuSelected(oldIndex: Int, newIndex: Int, menuItem: MenuItem?) {
        if (newIndex == 2 && oldIndex == 2) {
            lottieNav.selectedIndex = viewPager.currentItem
            return
        }

        if (newIndex == 2) {
            lottieNav.selectedIndex = oldIndex
            return
        }
        if (newIndex < 2) {
            viewPager.currentItem = newIndex
            if (newIndex == 0)
                fab.setImageResource(R.drawable.mapiconfab)
            else
                fab.setImageResource(R.drawable.uploadissuefab)

        } else {
            viewPager.currentItem = newIndex - 1
            if (newIndex == 3)
                fab.setImageResource(R.drawable.uploadsolnfab)
            else
                fab.setImageResource(R.drawable.profilefab)
        }

    }

    override fun onAnimationStart(index: Int, menuItem: MenuItem?) {
    }

    override fun onAnimationEnd(index: Int, menuItem: MenuItem?) {
    }

    override fun onAnimationCancel(index: Int, menuItem: MenuItem?) {
    }
}