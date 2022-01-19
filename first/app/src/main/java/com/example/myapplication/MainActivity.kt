package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import com.airbnb.lottie.LottieAnimationView
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var mBinding: ActivityMainBinding?=null
    private val binding get()=mBinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // view binding을 사용하기 위해서 기존의 setContentView를 삭제한다.
        //setContentView(R.layout.activity_main)

        // activity에서 사용할 바인딩 클래스의 인스턴스를 생성함
        mBinding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 뷰의 id는 카멜케이스 규칙이 적용되어 사용
        val lotteryNumber= arrayListOf(binding.ball1,binding.ball2,binding.ball3,binding.ball4,binding.ball5,binding.ball6)

        val countDownTimer=object: CountDownTimer(3000,100){
            override fun onFinish() {

            }

            override fun onTick(p0: Long) {
                lotteryNumber.forEach {
                    val randomNumber=(Math.random()*45+1).toInt()
                    it.text="$randomNumber"
                }
            }
        }
        binding.lotteryButton.setOnClickListener {
            binding.lotteryButton.playAnimation()
            countDownTimer.start()
        }

    }


}