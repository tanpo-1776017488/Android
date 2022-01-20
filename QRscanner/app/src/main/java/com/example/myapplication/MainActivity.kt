package com.example.myapplication

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.zxing.integration.android.IntentIntegrator

class MainActivity : AppCompatActivity() {
    private var mBind:ActivityMainBinding?=null

    private val binding get()=mBind!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBind= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun startBarcodeReader(view : View){
        val result=IntentIntegrator(this).initiateScan()
    }
    fun startBarcodeReaderCustom(view: View){
        val integrator=IntentIntegrator(this)
        integrator.apply{
            setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            setPrompt("QR 코드를 스캔하여 주세요")
            setCameraId(0)
            setBeepEnabled(true)
            setBarcodeImageEnabled(true)
            initiateScan()
        }
    }

    fun startBarcodeReaderCustomActivity(view: View){
        val integrator=IntentIntegrator(this)
        integrator.apply{
            setBarcodeImageEnabled(true)
            captureActivity=MyBarcodeReaderActivity::class.java
            initiateScan()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data)
        if(result!=null){
            if(result.contents !=null){
                Toast.makeText(this,"scanned : ${result.contents} format : ${result.formatName}",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"failed",Toast.LENGTH_SHORT).show()
            }
            if(result.barcodeImagePath!=null){
                val bitmap=BitmapFactory.decodeFile(result.barcodeImagePath)
                binding.scanedBitmap.setImageBitmap(bitmap)
            }
        }
        else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}