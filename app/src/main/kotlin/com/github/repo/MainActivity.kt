package com.github.repo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.repo.base.BaseActivity
import com.github.repo.base.BaseFragment
import com.github.repo.repositories.RepositoriesFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragment(RepositoriesFragment())
    }

}
