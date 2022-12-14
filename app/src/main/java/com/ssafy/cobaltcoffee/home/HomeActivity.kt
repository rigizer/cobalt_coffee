package com.ssafy.cobaltcoffee.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.accessibility.AccessibilityEventCompat.setAction
import com.forms.sti.progresslitieigb.ProgressLoadingIGB
import com.forms.sti.progresslitieigb.finishLoadingIGB
import com.google.android.material.snackbar.Snackbar
import com.ssafy.cobaltcoffee.R
import com.ssafy.cobaltcoffee.config.ApplicationClass
import com.ssafy.cobaltcoffee.coupon.CouponActivity
import com.ssafy.cobaltcoffee.databinding.ActivityHomeBinding
import com.ssafy.cobaltcoffee.dto.Product
import com.ssafy.cobaltcoffee.dto.User
import com.ssafy.cobaltcoffee.home.order.*
import com.ssafy.cobaltcoffee.setting.SettingActivity
import com.ssafy.cobaltcoffee.stamp.StampActivity
import com.ssafy.cobaltcoffee.start.StartActivity
import com.ssafy.cobaltcoffee.viewmodel.UserViewModel

const val HOME_FRAGMENT = 0
const val ORDER_FRAGMENT = 1
const val OTHER_FRAGMENT = 2

private const val TAG = "HomeActivity_코발트"
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val userViewModel : UserViewModel by viewModels()

    private var backKeyPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        InitializeLayout()  // UI 드로우
        setFunction()       // 기능 설정
        initUser()
    }

    private fun InitializeLayout() {
        initNavigation()
    }

    private fun initNavigation() {
        binding.apply {
            supportFragmentManager.beginTransaction()
                .replace(R.id.home_frame, HomeFragment()).commit()
            bottomNavigationView.setOnItemSelectedListener  {
                navigationSelected(it)
            }
        }
    }

    private fun navigationSelected(item: MenuItem): Boolean {
        binding.apply {
            val checked = item.setChecked(true)
            when (checked.itemId) {
                R.id.home_fragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.home_frame, HomeFragment()).commit()
                    true
                }
                R.id.order_fragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.home_frame, OrderFragment()).commit()
                    true
                }
                R.id.other_fragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.home_frame, OtherFragment()).commit()
                    true
                }
            }
            return false
        }
    }

    private fun setFunction() {

    }

    override fun onBackPressed() {
        val snackbar = Snackbar
            .make(binding.homeFrame, "뒤로 버튼을 한번 더 누르시면 종료됩니다.", Snackbar.LENGTH_SHORT)
            .setAction("닫기", {

            })

        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis()
            snackbar.show()
            return
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            finish()
        }
    }

    //자동로그인 체크 여부에 따른 유저 초기화
    private fun initUser(){
        val user = ApplicationClass.sharedPreferencesUtil.getUser()
        if (user.id.isEmpty()){ //sp에 저장된 객체가 없으면 id값은 이전 액티비티에서 받은 인텐트 값으로 초기화
            val data = intent.getStringExtra("userId")!!
            userViewModel.userId = data
        }
    }

    fun logout(){
        //preference 지우기
        ApplicationClass.sharedPreferencesUtil.deleteUser()

        //화면이동
        val intent = Intent(this, StartActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent)
    }

    //설정 페이지로 이동
    fun moveSettingPage() {
        val intent = Intent(this, SettingActivity::class.java)
        intent.putExtra("user", userViewModel.currentUser)
        startActivity(intent)
    }

    //상세 페이지로 이동
    fun detailPage(user: User) {
        val intent = Intent(this, ProductActivity::class.java)
        intent.putExtra("product", Product().apply {
            this.id = 3
        })
        intent.putExtra("user", user)
        startActivity(intent)
    }

    //장바구니 페이지로 이동
    fun cartPage(user: User) {
        val intent = Intent(this, CartActivity::class.java)
        intent.putExtra("user", user)
        startActivity(intent)
    }

    //주문 상세 페이지로 이동
    fun orderDetailPage(orderId: Int) {
        val intent = Intent(this, OrderDetailActivity::class.java)
        intent.putExtra("orderId", orderId)
        startActivity(intent)
    }

    //주문 내역 페이지로 이동
    fun orderHistoryPage(user: User) {
        val intent = Intent(this, OrderHistoryActivity::class.java)
        intent.putExtra("user",user)
        startActivity(intent)
    }

    //스탬프 페이지로 이동
    fun stampPage(user: User) {
        val intent = Intent(this, StampActivity::class.java)
        intent.putExtra("user",user)
        startActivity(intent)
    }

    //쿠폰 페이지로 이동
    fun couponPage(user: User) {
        val intent = Intent(this, CouponActivity::class.java)
        intent.putExtra("user",user)
        startActivity(intent)
    }
}