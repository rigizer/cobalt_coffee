package com.ssafy.cobaltcoffee.home.order

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.ssafy.cobaltcoffee.R
import com.ssafy.cobaltcoffee.databinding.ActivityProductListBinding
import com.ssafy.cobaltcoffee.dto.User
import com.ssafy.cobaltcoffee.home.OrderFragment
import com.ssafy.cobaltcoffee.viewmodel.UserViewModel

class ProductListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductListBinding

    private val tabTitleArray = arrayOf(
        "NEW",
        "BEST",
        "커피",
        "차",
        "쿠키"
    )

    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {

        userViewModel.currentUser = intent.getSerializableExtra("userInfo") as User

        initTb()

        binding.apply {
            viewPager.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = tabTitleArray[position]
            }.attach()
        }
    }

    //툴바 적용하기
    private fun initTb() {
        binding.apply {
            setSupportActionBar(productToolBar.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
            productToolBar.toolbarTitle.text = "코발트오더"
        }
    }

    //뒤로가기 버튼 클릭 시
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    inner class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
        override fun getItemCount(): Int = tabTitleArray.size
        override fun createFragment(position: Int): Fragment {
            return MenuFragment(position)
        }
    }
}