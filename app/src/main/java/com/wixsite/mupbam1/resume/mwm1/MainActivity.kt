package com.wixsite.mupbam1.resume.mwm1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wixsite.mupbam1.resume.mwm1.dagger_di.MainActivityViewModel
import com.wixsite.mupbam1.resume.mwm1.database.UserEntity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModel:MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btSave.setOnClickListener {
           val userEntity=UserEntity(name = edEnterDescription.text.toString())
            viewModel.insertRecord(userEntity)
            tvResult.setText("")

        }
        initViewModel()
    }
    private fun initViewModel(){
       viewModel= ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getRecordsObserver().observe(this,object:Observer<List<UserEntity>>
        {
            override fun onChanged(t: List<UserEntity>?) {
                tvResult.setText("")
                t?.forEach {
                  tvResult.append(it.name+"\n")
                }
            }
        })
    }


}