package com.example.notesapplofcoding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notesappcoding.databinding.NotesItemBinding
import com.example.notesapplofcoding.data.Note

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    inner class NotesViewHolder(private val binding: NotesItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(note: Note){
            binding.tvNoteTitle.text = note.noteTitle
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.noteId == newItem.noteId
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }

    val diffor = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            NotesItemBinding.inflate(LayoutInflater.from(parent.context),
        parent,false))
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {

        val note = diffor.currentList[position]
        holder.bind(note)
        holder.itemView.setOnClickListener {
            onCkick!!.invoke(note)
        }

    }

    override fun getItemCount() = diffor.currentList.size

    var onCkick: ((Note) -> Unit)? = null

}