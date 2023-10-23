package com.example.myapplication

import android.animation.Animator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
import com.example.myapplication.databinding.FragmentBlankBinding


class BlankFragment : Fragment() {
    private lateinit var binding: FragmentBlankBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBlankBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        binding.ivMain.setOnClickListener {
            findNavController().navigate(
                R.id.action_blankFragment_to_blankFragment2,
                Bundle().apply {
                    putInt("image", R.drawable.image)
                },
                null,
                FragmentNavigatorExtras(binding.cardView to "image")
            )
        }
        (requireView().parent as ViewGroup).viewTreeObserver
            .addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
        binding.laArrowRight.setOnClickListener {
            Toast.makeText(requireContext(), "TEST", Toast.LENGTH_SHORT).show()
        }
        binding.laArrowRight.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator) {
                Toast.makeText(requireContext(), "onAnimationStart", Toast.LENGTH_SHORT).show()
            }

            override fun onAnimationEnd(p0: Animator) {
                Toast.makeText(requireContext(), "onAnimationEnd", Toast.LENGTH_SHORT).show()
            }

            override fun onAnimationCancel(p0: Animator) {
                Toast.makeText(requireContext(), "onAnimationCancel", Toast.LENGTH_SHORT).show()
            }

            override fun onAnimationRepeat(p0: Animator) {
                Toast.makeText(requireContext(), "onAnimationRepeat", Toast.LENGTH_SHORT).show()
            }
        })
    }

}