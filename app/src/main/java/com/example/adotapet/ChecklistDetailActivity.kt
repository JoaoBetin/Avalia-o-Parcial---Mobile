package com.example.adotapet

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.adotapet.data.ChecklistMocks
import com.example.adotapet.databinding.ActivityChecklistDetailBinding
import com.example.adotapet.databinding.ChecklistItemLayoutBinding
import com.example.adotapet.model.Checklist

class ChecklistDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChecklistDetailBinding
    private var checkedIds: Set<String> = emptySet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChecklistDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }
        binding.back.setOnClickListener { finish() }
        val checklist = ChecklistMocks.findById(intent.getStringExtra(EXTRA_CHECKLIST_ID))
        if (checklist == null) {
            binding.title.text = getString(R.string.not_found)
            binding.description.text = getString(R.string.not_found_description)
            binding.progressPanel.visibility = View.GONE
            binding.reset.visibility = View.GONE
            binding.sessionNote.visibility = View.GONE
            return
        }
        checkedIds = savedInstanceState?.getStringArrayList(STATE_CHECKED)
            ?.filter { id -> checklist.items.any { it.id == id } }?.toSet() ?: emptySet()
        binding.title.text = checklist.title
        binding.description.text = checklist.description
        checklist.items.forEach { item ->
            val row = ChecklistItemLayoutBinding.inflate(layoutInflater, binding.itemsContainer, false)
            row.check.text = item.title
            row.note.text = item.note.orEmpty()
            row.note.visibility = if (item.note.isNullOrBlank()) View.GONE else View.VISIBLE
            row.check.isChecked = item.id in checkedIds
            row.check.setOnCheckedChangeListener { _, checked ->
                checkedIds = if (checked) checkedIds + item.id else checkedIds - item.id
                updateProgress(checklist)
            }
            binding.itemsContainer.addView(row.root)
        }
        binding.reset.setOnClickListener {
            checkedIds = emptySet()
            for (index in 0 until binding.itemsContainer.childCount) {
                ChecklistItemLayoutBinding.bind(binding.itemsContainer.getChildAt(index)).check.isChecked = false
            }
            updateProgress(checklist)
        }
        updateProgress(checklist)
    }

    private fun updateProgress(checklist: Checklist) {
        val complete = checkedIds.size == checklist.items.size
        binding.progress.text = getString(R.string.progress_count, checkedIds.size, checklist.items.size)
        binding.status.text = getString(if (complete) R.string.all_ready else R.string.keep_checking)
        binding.reset.isEnabled = checkedIds.isNotEmpty()
        binding.reset.alpha = if (binding.reset.isEnabled) 1f else 0.45f
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putStringArrayList(STATE_CHECKED, ArrayList(checkedIds))
        super.onSaveInstanceState(outState)
    }

    companion object {
        const val EXTRA_CHECKLIST_ID = "com.example.adotapet.CHECKLIST_ID"
        private const val STATE_CHECKED = "checked_ids"
    }
}
