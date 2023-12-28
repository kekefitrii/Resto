package com.ike.resto.ui.foodList

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.firebase.auth.FirebaseAuth
import com.ike.resto.R
import com.ike.resto.databinding.ActivityFoodListBinding
import com.ike.resto.ui.auth.LoginActivity
import com.ike.resto.ui.base.NotificationService
import com.ike.resto.ui.data.Food
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FoodsListActivity : AppCompatActivity() {
    private val viewModel: FoodsListViewModel by viewModels()

    private val binding: ActivityFoodListBinding by lazy {
        ActivityFoodListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val foodsAdapter = FoodsAdapter { food -> adapterOnClick(food) }

        viewModel.getFoodList()

        setSupportActionBar(binding.customToolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        binding.customToolbar.title = getString(R.string.title_food_list)
        binding.customToolbar.setNavigationOnClickListener { logout() }

        binding.recyclerView.adapter = foodsAdapter

        observeList(foodsAdapter)

        val serviceIntent = Intent(this, NotificationService::class.java)
        NotificationService.enqueueWork(this, serviceIntent)
        scheduleJob()
    }

    private fun logout() {
        val builder = AlertDialog.Builder(this)

        builder.setTitle(getString(R.string.logout))
            .setMessage(getString(R.string.subtitle_logout))

        builder.setPositiveButton(getString(R.string.yes)) { _, _ ->
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        builder.setNegativeButton(getString(R.string.no)) { dialog, _ ->
            dialog.dismiss()
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun observeList(foodsAdapter: FoodsAdapter) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.data.collect { data ->
                    data?.let {
                        foodsAdapter.submitList(it as MutableList<Food>)
                    }
                }
            }
        }
    }

    private fun adapterOnClick(food: Food) {
        Toast.makeText(this, food.name, Toast.LENGTH_SHORT).show()
    }

    private fun scheduleJob() {
        val componentName = ComponentName(this, NotificationService::class.java)
        val builder = JobInfo.Builder(NotificationService.JOB_ID, componentName)
            .setPersisted(true)

        builder.setMinimumLatency(NotificationService.JOB_INTERVAL_MILLIS)

        val jobInfo = builder.build()

        val jobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        jobScheduler.schedule(jobInfo)
    }
}
