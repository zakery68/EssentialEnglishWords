package com.example.essentialenglishwords

import android.app.ActivityOptions
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.essentialenglishwords.Process.DataProcess
import com.example.essentialenglishwords.RecyclerView.Adapter.TransferDataUnit
import com.example.essentialenglishwords.RecyclerView.Adapter.UnitAdapter
import com.example.essentialenglishwords.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), TransferDataUnit {

    private val dataProcess: DataProcess = DataProcess()
    private val adapter: UnitAdapter = UnitAdapter(this@MainActivity, this@MainActivity)

    lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        dataProcess.fillUnit(this, adapter)

        mainBinding.recyclerView.layoutManager =
            LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)

        mainBinding.recyclerView.adapter = adapter


    }

    override fun notifyDataForTransferUnit(position: Int, imageView: ImageView) {

        Intent(this@MainActivity, UnitPage::class.java).apply {
            putExtra("key", position)

            startActivity(
                this@apply,
                ActivityOptions.makeSceneTransitionAnimation(
                    this@MainActivity,
                    imageView,
                    "transitionImage"
                )
                    .toBundle()
            )

        }
    }
}