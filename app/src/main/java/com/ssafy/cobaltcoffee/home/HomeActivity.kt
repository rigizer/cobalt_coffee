package com.ssafy.cobaltcoffee.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ssafy.cobaltcoffee.R
import com.ssafy.cobaltcoffee.config.ApplicationClass
import com.ssafy.cobaltcoffee.databinding.ActivityHomeBinding
import com.ssafy.cobaltcoffee.dto.User
import com.ssafy.cobaltcoffee.repository.UserRepository
import com.ssafy.cobaltcoffee.setting.SettingActivity
import com.ssafy.cobaltcoffee.start.StartActivity
import com.ssafy.cobaltcoffee.viewmodel.UserViewModel
import com.ssafy.smartstore.util.RetrofitCallback

const val HOME_FRAGMENT = 0
const val ORDER_FRAGMENT = 1
const val OTHER_FRAGMENT = 2

private const val TAG = "HomeActivity_코발트"
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val userViewModel : UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        InitializeLayout()  // UI 드로우
        setFunction()       // 기능 설정
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
        super.onBackPressed()
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
        intent.putExtra("user",userViewModel.currentUser)
        startActivity(intent)
        overridePendingTransition(0,0)
    }

}