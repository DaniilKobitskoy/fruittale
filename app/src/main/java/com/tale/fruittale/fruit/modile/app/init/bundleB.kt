package com.tale.fruittale.fruit.modile.app

import android.content.Context
import android.content.res.Resources
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.Toast
import java.util.*
import kotlin.collections.ArrayList

internal class bundleB (
    private val v2con: Context,
    private val numberposts: Int,
    private val getumage: Int) : BaseAdapter() {
    private val imageview: ArrayList<String?> = ArrayList()
    private val imNum : String
    private val resourseD : Resources



    private enum class imageset {
        kakashka, kakashka2, kakashka3
    }
    private enum class number {
        pos1, pos2
    }
    private val ImagePosts : ArrayList<imageset> = ArrayList()
    private val getWindowImages: ArrayList<number>
    init {
        getWindowImages = ArrayList()
        imNum = "fruit"
        resourseD = v2con.resources
        paintWindow()
    news()
        posting1()
        posting2()
        posting3()
        colorsPaints()
    }

    private fun news() {

        var str: String = "skfjkdjkfksladasd"
        var str1: String = "skfjkdjkfksladasd"
        var num = str.length + str1.length
        println(num)

    }

    private fun paintWindow() {
        imageview.clear()
        for (i in 0 until numberposts * getumage / 2) {
            imageview.add(imNum + Integer.toString(i))
            imageview.add(imNum + Integer.toString(i))
            posting1()
            posting3()
            posting2()
        }
        Collections.shuffle(imageview)
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
    private fun colorsPaints() {
        ImagePosts.clear()
        for (i in 0 until numberposts * getumage) ImagePosts.add(imageset.kakashka2)
    }

    override fun getCount(): Int {
        return numberposts * getumage
    }

    override fun getItem(geters: Int): Any? {
        return null
    }

    override fun getItemId(seters: Int): Long {
        return 0
    }

    override fun getView(getViewNum: Int, close: View?, viewPaint: ViewGroup): View {
        posting3()
        val statusImage: ImageView =
            if (close == null) ImageView(v2con) else close as ImageView
        when (ImagePosts[getViewNum]) {
            imageset.kakashka -> {
                val randomImage = resourseD.getIdentifier(imageview[getViewNum], "drawable", v2con.packageName)
                statusImage.setImageResource(randomImage)
                posting1()
                posting2()
                posting3()
            }

            imageset.kakashka2 -> statusImage.setImageResource(R.drawable.noopen)
            else -> statusImage.setImageResource(R.drawable.delete)

        }
        return statusImage
    }

    fun gameLogic() {
        posting1()
        posting2()
        val set1 = ImagePosts.indexOf(imageset.kakashka)
        val set2 = ImagePosts.lastIndexOf(imageset.kakashka)
        if (set1 == set2) return
        if (imageview[set1] == imageview[set2]) {
            ImagePosts[set1] = imageset.kakashka3
            ImagePosts[set2] = imageset.kakashka3
            posting1()
            posting2()
            posting3()
        } else {
            posting3()
            ImagePosts[set1] = imageset.kakashka2
            ImagePosts[set2] = imageset.kakashka2
        }
        return
    }

    fun colorsPaints(positions: Int): Boolean {
        posting3()
        if (ImagePosts[positions] === imageset.kakashka3 || ImagePosts[positions] === imageset.kakashka) return false
        if (ImagePosts[positions] !== imageset.kakashka3) ImagePosts[positions] = imageset.kakashka
        notifyDataSetChanged()
        return true
    }

    fun checkGMover(): Boolean {
        posting3()
        return if (ImagePosts.indexOf(imageset.kakashka2) < 0) true else false
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


}