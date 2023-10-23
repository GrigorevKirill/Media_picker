package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.databinding.BottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomFragment : BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetBinding
    lateinit var list: List<RecyclerViewAdapter.Types.MediaItem>
    private var count = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetBinding.bind(inflater.inflate(R.layout.bottom_sheet, container))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = RecyclerViewAdapter(list, object : RecyclerViewAdapter.Listener {
            override fun onClick(chooseIndex: Int, callback: (Int) -> Unit) {
                if (calculateIndex(chooseIndex)) {
                    callback.invoke(++count)
                } else {

                }
            }
        })
        val gridLayout = GridLayoutManager(requireContext(), 3)
        binding.revMediaPicker.adapter = adapter
        binding.revMediaPicker.layoutManager = gridLayout
        dialog?.layoutInflater
        dialog?.show()
    }

    private fun calculateIndex(chooseIndex: Int): Boolean {
        return chooseIndex == 0
    }
}