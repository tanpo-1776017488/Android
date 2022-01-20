package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.example.myapplication.databinding.ActivityMyBarcodeReaderBinding
import com.journeyapps.barcodescanner.CaptureManager
import java.util.zip.Inflater

class MyBarcodeReaderActivity : AppCompatActivity() {
    private var mBind:ActivityMyBarcodeReaderBinding?=null

    private val binding get()=mBind!!

    private lateinit var capture:CaptureManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBind=ActivityMyBarcodeReaderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        capture=CaptureManager(this,binding.barcodeScanner)
        capture.initializeFromIntent(intent,savedInstanceState)
        capture.decode()

    }

    override fun onResume() {
        super.onResume()
        capture.onResume()

    }

    override fun onPause() {
        super.onPause()
        capture.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        capture.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        capture.onSaveInstanceState(outState)
    }

    override fun onRequestPermissionsResult( requestCode: Int,  permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        capture.onRequestPermissionsResult(requestCode,permissions,grantResults)
    }
}