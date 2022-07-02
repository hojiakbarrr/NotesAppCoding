package com.example.notesapplofcoding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesappcoding.MainActivity
import com.example.notesappcoding.R
import com.example.notesappcoding.databinding.FragmentNotesListBinding
import com.example.notesapplofcoding.viewmodel.NotesViewModel

class NotesListFragment : Fragment(R.layout.fragment_notes_list) {

    private lateinit var binding: FragmentNotesListBinding
    private lateinit var notesAdapter: NotesAdapter
    private lateinit var viewmodel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel = (activity as MainActivity).viewmodel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotesListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecycler()

        lifecycleScope.launchWhenStarted {
            viewmodel.notes.collect{
                notesAdapter.diffor.submitList(it)
            }
        }
        lifecycleScope.launchWhenStarted {
            viewmodel._searchNotes.collect{
                notesAdapter.diffor.submitList(it)
            }
        }

        binding.edSearch.addTextChangedListener {
            viewmodel.searchNote(it.toString().trim())
        }
        binding.btnAddNote.setOnClickListener {
            findNavController().navigate(R.id.action_notesListFragment_to_noteFragment)
        }

        notesAdapter.onCkick = { note ->
            val bundle = Bundle().apply {
                putParcelable("note", note)
            }

            findNavController().navigate(R.id.action_notesListFragment_to_noteFragment,bundle)

        }

    }

    private fun setupRecycler() {
        notesAdapter = NotesAdapter()
        binding.rvNotes.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = notesAdapter
            addItemDecoration(VerticalItemDecoration(40))
        }
    }
}