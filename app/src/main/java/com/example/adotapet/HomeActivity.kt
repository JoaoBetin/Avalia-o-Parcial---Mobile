package com.example.adotapet

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.adotapet.data.ChecklistMocks
import com.example.adotapet.databinding.ActivityHomeBinding
import com.example.adotapet.databinding.HomeListItemLayoutBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }
        ChecklistMocks.lists.forEachIndexed { index, checklist ->
            val item = HomeListItemLayoutBinding.inflate(layoutInflater, binding.listContainer, false)
            item.number.text = getString(R.string.list_number, index + 1)
            item.title.text = checklist.title
            item.description.text = checklist.description
            item.count.text = getString(R.string.item_count, checklist.items.size)
            item.root.setOnClickListener {
                startActivity(Intent(this, ChecklistDetailActivity::class.java).apply {
                    putExtra(ChecklistDetailActivity.EXTRA_CHECKLIST_ID, checklist.id)
                })
            }
            binding.listContainer.addView(item.root)
        }
    }
}
