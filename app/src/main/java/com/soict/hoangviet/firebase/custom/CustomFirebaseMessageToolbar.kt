package com.soict.hoangviet.firebase.custom

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.soict.hoangviet.firebase.R
import com.soict.hoangviet.firebase.extension.gone
import com.soict.hoangviet.firebase.widget.BaseCustomViewConstrainLayout

class CustomFirebaseMessageToolbar : BaseCustomViewConstrainLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    private var toolbar: ConstraintLayout? = null
    private var imvLeft: ImageView? = null
    private var imvRightOne: ImageView? = null
    private var imvRightTwo: ImageView? = null
    private var imvRightThree: ImageView? = null
    private var imvAvatar: ImageView? = null
    private var toolbarMainName: TextView? = null

    override val layoutRes: Int
        get() = R.layout.layout_toolbar_message
    override val styleRes: IntArray?
        get() = R.styleable.BaseToolbarMessage


    override fun initView() {
        toolbar = findViewById(R.id.toolbar)
        imvLeft = findViewById(R.id.imv_left)
        imvRightOne = findViewById(R.id.imv_right_one)
        imvRightTwo = findViewById(R.id.imv_right_second)
        imvRightThree = findViewById(R.id.imv_right_three)
        imvAvatar = findViewById(R.id.imv_avatar)
        toolbarMainName = findViewById(R.id.toolbar_main_name)
        imvLeft?.setOnClickListener {
        }
    }

    override fun initDataFromStyleable(typeArray: TypedArray?) {
        val avatar = typeArray?.getInt(R.styleable.BaseToolbarMessage_btm_avatar, -1)
        val background = typeArray?.getColor(R.styleable.BaseToolbarMessage_btm_background, -1)
        val imageLeft = typeArray?.getDrawable(R.styleable.BaseToolbarMessage_btm_icon_left)
        val imageRightOne = typeArray?.getDrawable(R.styleable.BaseToolbarMessage_btm_icon_right_one)
        val imageRightTwo = typeArray?.getDrawable(R.styleable.BaseToolbarMessage_btm_icon_right_two)
        val imageRightThree = typeArray?.getDrawable(R.styleable.BaseToolbarMessage_btm_icon_right_three)
        val name = typeArray?.getString(R.styleable.BaseToolbarMessage_btm_main_name)
        imageLeft?.let { setIconLeft(it) }
        imageRightOne?.let { setIconRightOne(it) }
        imageRightTwo?.let { setIconRightTwo(it) }
        imageRightThree?.let { setIconRightThree(it) }
        setAvatar(avatar ?: -1)
        setBackground(background ?: -1)
        setName(name ?: "")
    }

    private fun setName(name: String) {
        toolbarMainName?.let { it.text = name }
    }

    private fun setBackground(background: Int) {
        if (background != -1) {
            toolbar?.setBackgroundColor(background)
        }
    }

    private fun setAvatar(avatar: Int) {
        if (avatar != -1) {
            imvAvatar?.setImageResource(avatar)
        }
    }

    private fun setIconRightThree(imageRightThree: Drawable) {
        imvRightThree?.setImageDrawable(imageRightThree)
    }

    private fun setIconRightTwo(imageRightTwo: Drawable) {
        imvRightTwo?.setImageDrawable(imageRightTwo)
    }

    private fun setIconRightOne(imageRightOne: Drawable) {
        imvRightOne?.setImageDrawable(imageRightOne)
    }

    fun setIconLeft(imageLeft: Drawable) {
        imvLeft?.let { it.setImageDrawable(imageLeft) }
    }

    fun setMainName(name: String?) {
        name?.let { toolbarMainName?.text = it }
    }

    fun hideLeftButton() {
        imvLeft?.let { it.gone() }
    }

    fun hideMainName() {
        toolbarMainName?.let { it.gone() }
    }

    override fun initData() {

    }

    override fun initListener() {
    }
}