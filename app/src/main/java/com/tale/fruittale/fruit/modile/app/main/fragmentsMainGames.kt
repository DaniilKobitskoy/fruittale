package com.tale.fruittale.fruit.modile.app.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Chronometer
import android.widget.GridView
import android.widget.Toast
import com.tale.fruittale.fruit.modile.app.bundleB
import com.tale.fruittale.fruit.modile.app.databinding.FragmentFragmentsMainGamesBinding



class fragmentsMainGames : Fragment() {
    private var gamePaintsViewAdapters: GridView? = null
    private var mainAdapterforLogic: bundleB? = null
    var countStep = 0
    var painListSize: Int = 4
    var painListSize2: Int = 3
    var points = 0
    private var timeViews: Chronometer? = null

    private var binding : FragmentFragmentsMainGamesBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFragmentsMainGamesBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        news()
        posting1()
        posting2()
        posting3()
        gamePaintsViewAdapters = binding!!.gameView
        gamePaintsViewAdapters!!.numColumns = painListSize
        gamePaintsViewAdapters!!.isEnabled = true

        mainAdapterforLogic = bundleB(requireContext(), painListSize, painListSize2)
        gamePaintsViewAdapters!!.adapter = mainAdapterforLogic


        timeViews = binding!!.timers
        binding!!.progresssumm.text = "0"
        timeViews!!.start()

        gamePaintsViewAdapters!!.onItemClickListener =
            AdapterView.OnItemClickListener { parent, v, position, id ->
                mainAdapterforLogic!!.gameLogic()
                mainAdapterforLogic!!.colorsPaints(position)
                points++
                binding!!.progresssumm.setText(points.toString())
                var randompoints = (-3..13).random()
                var spoints = randompoints + countStep
                countStep = spoints
                binding!!.pointsumm.text = spoints.toString()

                if (countStep >= 200 && points >= 200) {
                    news()
                    posting1()
                    posting2()
                    posting3()
                    gamePaintsViewAdapters = binding!!.gameView as GridView
                    gamePaintsViewAdapters!!.numColumns = painListSize
                    gamePaintsViewAdapters!!.isEnabled = true
                    mainAdapterforLogic = bundleB(requireContext(), painListSize, painListSize2)
                    gamePaintsViewAdapters!!.adapter = mainAdapterforLogic
                    Toast.makeText(requireContext(), "No Win", Toast.LENGTH_SHORT).show()
                    countStep = 0
                }else if(mainAdapterforLogic!!.checkGMover() )
                {
                    timeViews!!.stop()
painListSize2++
                    news()
                    posting1()
                    posting2()
                    posting3()
                    next()
                }
            }
    }


    private fun news() {

        var str: String = "skfjkdjkfksladasd"
        var str1: String = "skfjkdjkfksladasd"
        var num = str.length + str1.length
        println(num)

    }
    private fun posting1() {
        var p = "sjkfdlmlakdsdadfdsd".length
        var p1= 10
        if (p >= 10){

            var con = p * p1
            println(con)
            var p2 = con + 10
            println(p2)
        }
        else {

            println("Check")

        }
    }
    private fun posting2() {
        var p = "sjkfdlmlakdsdadfdsd".length
        var p1= 10
        if (p >= 10){

            var con = p * p1
            println(con)
            var p2 = con + 10
            println(p2)
        }
        else {

            println("Check")

        }
    }
    private fun posting3() {
        var text = "lsfisaskdjbsgkfdjhgfmad"
        var p = text.length
        var p1= "shjfdkjhahdjfkmdsfdads".length
        if (p >= 10){

            var con = p * p1
            println(con)
            var p2 = con + 10
            println(p2)
            var p4 = p1 + p
            println(p4)
        }
        else {

            println("Check")

        }
    }
    private fun next() {
        news()
        posting1()
        posting2()
        posting3()
        gamePaintsViewAdapters = binding!!.gameView
        gamePaintsViewAdapters!!.numColumns = painListSize
        gamePaintsViewAdapters!!.isEnabled = true

        mainAdapterforLogic = bundleB(requireContext(), painListSize, painListSize2)
        gamePaintsViewAdapters!!.adapter = mainAdapterforLogic


        timeViews = binding!!.timers
        binding!!.progresssumm.text = "0"
        timeViews!!.start()

        gamePaintsViewAdapters!!.onItemClickListener =
            AdapterView.OnItemClickListener { parent, v, position, id ->
                mainAdapterforLogic!!.gameLogic()
                mainAdapterforLogic!!.colorsPaints(position)
                points++
                news()
                posting1()
                posting2()
                posting3()
                binding!!.progresssumm.setText(points.toString())
                var randompoints = (-3..13).random()
                var spoints = randompoints + countStep
                countStep = spoints
                binding!!.pointsumm.text = spoints.toString()

                if (countStep >= 200 && points >= 200) {
                    news()
                    posting1()
                    posting2()
                    posting3()
                    gamePaintsViewAdapters = binding!!.gameView as GridView
                    gamePaintsViewAdapters!!.numColumns = painListSize
                    gamePaintsViewAdapters!!.isEnabled = true
                    mainAdapterforLogic = bundleB(requireContext(), painListSize, painListSize2)
                    gamePaintsViewAdapters!!.adapter = mainAdapterforLogic
                    Toast.makeText(requireContext(), "No Win", Toast.LENGTH_SHORT).show()
                    countStep = 0
                }else if(mainAdapterforLogic!!.checkGMover() )
                {
                    news()
                    posting1()
                    posting2()
                    posting3()
                    timeViews!!.stop()

                    Toast.makeText(requireContext(), "Win", Toast.LENGTH_SHORT).show()
                }
            }
    }

    companion object {

        fun newInstance() =
            fragmentsMainGames()
    }
}