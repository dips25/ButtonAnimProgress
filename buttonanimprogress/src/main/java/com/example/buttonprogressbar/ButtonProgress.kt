package com.example.buttonprogressbar

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.Color
import android.util.AttributeSet
import android.widget.LinearLayout
import  android.os.Handler
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.findViewTreeLifecycleOwner

class ButtonProgress : FrameLayout {

    private val ANDROID_ATTRS = intArrayOf(android.R.attr.text
    ,android.R.attr.textSize
        ,android.R.attr.textColor
        , com.google.android.material.R.attr.backgroundColor)

    var mWidth = 0;
    var mHeight = 0;

    var anim1:MyButtonProgressAnim?=null;
    var anim2:MyButtonProgressAnim?=null;

    var duration = 500;

    var listener: ValueAnimator.AnimatorUpdateListener? = null

    var animObservable:Observer<Anim>?=null;

    var progress:ProgressBar?=null;
    var button: TextView?=null;

    var mutableData:MutableLiveData<Anim> = MutableLiveData<Anim>()


    constructor(c: Context, width: Int) : super(c) {

        Handler().post(){

            mWidth = getWidth();
            mHeight = getHeight();
        }



    }

    constructor(c: Context, a: AttributeSet) : super(c,a) {

        init(c,a)

    }

    constructor(c: Context, a: AttributeSet, d: Int) : super(c,a,d) {

        init(c,a)

    }

    inner class MyButtonProgressAnim : ValueAnimator() {

        override fun addUpdateListener(listener: AnimatorUpdateListener?) {
            super.addUpdateListener(listener)
        }
    }

    fun setAnim(anim:Anim) {

        mutableData.value = anim


    }

    @SuppressLint("ResourceType")
    fun init(c:Context, a: AttributeSet) {

        val typedArray:TypedArray = c.theme.obtainStyledAttributes(a,R.styleable.ButtonProgress,0,0)

        val text = typedArray.getString(R.styleable.ButtonProgress_text)
        val textSize = typedArray.getInt(R.styleable.ButtonProgress_textSize,14);
        val textColor = typedArray.getColor(R.styleable.ButtonProgress_textColor
            ,resources.getColor(android.R.color.black));
        val background = typedArray.getColor(R.styleable.ButtonProgress_backgroundColor
            ,resources.getColor(android.R.color.holo_blue_bright))

        val progressBackground = typedArray.getColor(R.styleable.ButtonProgress_progressBackground
            ,resources.getColor(android.R.color.system_accent1_400))

        val drawable = typedArray.getDrawable(R.styleable.ButtonProgress_drawable)
        val typeFace = typedArray.getResourceId(R.styleable.ButtonProgress_typeface,-1)
        duration = typedArray.getInt(R.styleable.ButtonProgress_duration,500)


        typedArray.recycle()

        Toast.makeText(c,text,Toast.LENGTH_SHORT).show()







        Handler().post(){

            mWidth = width;
            mHeight = height;



            animObservable = object : Observer<Anim> {

                override fun onChanged(value: Anim) {

                    if (value == Anim.RESUME) {

                        if (anim1!!.isRunning) {

                            anim1!!.cancel()
                        }

                        anim2!!.start()

                    } else if (value == Anim.STOP) {

                        if (anim2!!.isRunning) {

                            anim2!!.cancel()
                        }

                        anim1!!.start()
                    }

                }


            }

            mutableData.observe(findViewTreeLifecycleOwner()!!,animObservable!!)


            var bp = LayoutInflater.from(c).inflate(R.layout.layout_progress,this,true)


            progress = bp.findViewById(R.id.progress)
            progress!!.indeterminateTintList = ColorStateList.valueOf(progressBackground)


            button = bp.findViewById(R.id.button)

            button!!.setBackgroundColor(background)
            button!!.setTextSize(Utils.getPxFromDp(c,textSize).toFloat())
            button!!.setText(text)
            button!!.setTextColor(textColor)

            if (typeFace != -1) {

                val tp = ResourcesCompat.getFont(c,typeFace)

                button!!.typeface = tp
            }


            drawable?.let {

                button!!.background = drawable
            }

            val blp:FrameLayout.LayoutParams = button!!.layoutParams as FrameLayout.LayoutParams;

            blp.width = mWidth;
            blp.height = mHeight
            blp.gravity = Gravity.CENTER
            button!!.gravity = Gravity.CENTER

            button!!.layoutParams = blp

            val plp:FrameLayout.LayoutParams = progress!!.layoutParams as FrameLayout.LayoutParams;
            plp.width = FrameLayout.LayoutParams.WRAP_CONTENT
            plp.height = FrameLayout.LayoutParams.WRAP_CONTENT
            plp.gravity = Gravity.CENTER
            progress!!.layoutParams = plp


            progress!!.visibility = View.GONE







            listener = object :ValueAnimator.AnimatorUpdateListener {

                override fun onAnimationUpdate(animation: ValueAnimator) {

                    val blp:FrameLayout.LayoutParams = button!!.layoutParams as FrameLayout.LayoutParams;

                    blp.width = animation.getAnimatedValue() as Int;
                    blp.gravity = Gravity.CENTER
                    button!!.gravity = Gravity.CENTER

                    button!!.layoutParams = blp
                    progress!!.layoutParams = plp


                    if (animation.getAnimatedValue() as Int == 0) {

                        button!!.visibility = View.INVISIBLE
                        progress!!.visibility = View.VISIBLE


                    } else  {

                        button!!.visibility = View.VISIBLE
                        progress!!.visibility = View.GONE


                    }

                }

            }

            anim1 = MyButtonProgressAnim();
            anim1!!.setIntValues(0,mWidth);
            anim1!!.interpolator = LinearInterpolator()
            anim1!!.setDuration(duration.toLong())
            anim1!!.addUpdateListener(listener);

            anim2 = MyButtonProgressAnim();
            anim2!!.setIntValues(mWidth,0);
            anim2!!.interpolator = LinearInterpolator()
            anim2!!.setDuration(duration.toLong())
            anim2!!.addUpdateListener(listener);


        }


    }








}