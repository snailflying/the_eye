/*
 * Copyright 2013 Inmite s.r.o. (www.inmite.eu).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.theone.framework.widget.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.theone.framework.widget.dialog.BaseDialogBuilder.Companion.DEFAULT_CANCELABLE_ON_TOUCH_OUTSIDE
import com.theone.framework.widget.dialog.BaseDialogBuilder.Companion.DEFAULT_DIM_AMOUNT
import com.theone.framework.widget.dialog.BaseDialogBuilder.Companion.DEFAULT_EXPAND_BOTTOM_SHEET
import com.theone.framework.widget.dialog.BaseDialogBuilder.Companion.DEFAULT_REQUEST_CODE
import com.theone.framework.widget.dialog.BaseDialogBuilder.Companion.DEFAULT_SCALE
import com.theone.framework.widget.dialog.BaseDialogBuilder.Companion.DEFAULT_SHOW_FROM_BOTTOM
import com.theone.framework.widget.dialog.iface.IDialogCancelListener
import com.theone.framework.widget.dialog.iface.IDialogDismissListener
import com.theone.framework.widget.dialog.iface.IDialogNegativeListener
import com.theone.framework.widget.dialog.iface.IDialogPositiveListener
import com.themone.theone.library.R
import java.util.*

/**
 * @Author zhiqiang
 * @Email liuzhiqiang@theone.com
 * @Date 2019/2/16
 * @Description dialog的基类
 */

/**
 * @Author zhiqiang
 * @Email liuzhiqiang@moretickets.com
 * @Date 2019/2/16
 * @Description dialog的基类
 */
abstract class BaseDialogFragment : AppCompatDialogFragment() {

    protected var mRequestCode = DEFAULT_REQUEST_CODE

    /**
     * 点击外部隐藏dialog，默认开启
     */
    private var canceledOnTouchOutside = DEFAULT_CANCELABLE_ON_TOUCH_OUTSIDE

    /**
     * 灰度深浅
     */
    private var dimAmount = DEFAULT_DIM_AMOUNT

    /**
     * 宽度缩放
     */
    private var scale = DEFAULT_SCALE

    /**
     * 是否底部显示
     */
    private var showFromBottom = DEFAULT_SHOW_FROM_BOTTOM

    /**
     * 当Dialog为BottomSheet时，是否完全展开
     */
    private var expandBottomSheet = DEFAULT_EXPAND_BOTTOM_SHEET

    /**
     * 主题
     */
    private var mTheme: Int = R.style.Dialog

    /**
     * 动画
     */
    private var animStyle: Int = 0


    protected lateinit var mContext: Context

    protected val cancelListeners: List<IDialogCancelListener>
        get() = getDialogListeners(IDialogCancelListener::class.java)

    protected val dismissListeners: List<IDialogDismissListener>
        get() = getDialogListeners(IDialogDismissListener::class.java)


    /**
     * positive 按钮事件，可能不止一个（activity嵌套fragment）
     *
     * @return Dialog listeners
     */
    protected val positiveListeners: List<IDialogPositiveListener>
        get() = getDialogListeners(IDialogPositiveListener::class.java)

    /**
     * negative 按钮事件，可能不止一个（activity嵌套fragment）
     *
     * @return Dialog listeners
     */
    protected val negativeListeners: List<IDialogNegativeListener>
        get() = getDialogListeners(IDialogNegativeListener::class.java)

    /**
     * 步骤1
     * @param savedInstanceState Bundle?
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initArguments()
    }

    /**
     * 步骤2
     * 支持BottomSheetDialog
     */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return (if (showFromBottom) {
            BottomSheetDialog(this.context!!, this.theme)
        } else {
            Dialog(activity!!, theme)
        })
    }

    /**
     * 步骤3
     * @param savedInstanceState Bundle?
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //放在onCreateDialog()不起作用
        initDialogParams(dialog)
    }

    override fun onDestroyView() {
        if (dialog != null && retainInstance) {
            dialog?.setDismissMessage(null)
        }
        super.onDestroyView()
    }

    override fun onDismiss(dialog: DialogInterface) {
        for (listener in dismissListeners) {
            listener.onDismissed(mRequestCode)
        }
        super.onDismiss(dialog)
    }

    override fun onCancel(dialog: DialogInterface) {
        for (listener in cancelListeners) {
            listener.onCancelled(mRequestCode)
        }
        super.onCancel(dialog)
    }


    /**
     * 采用fragment interface pattern方式传递callback回调
     * targetFragment需要配合[BaseDialogBuilder.setTargetFragment]
     *
     * @param listenerInterface
     * @param <T>
     * @return
    </T> */
    fun <T> getDialogListeners(listenerInterface: Class<T>): List<T> {
        val targetFragment = targetFragment
        val listeners = ArrayList<T>(2)
        if (targetFragment != null && listenerInterface.isAssignableFrom(targetFragment.javaClass)) {
            listeners.add(targetFragment as T)
        }
        if (activity != null && listenerInterface.isAssignableFrom(activity!!.javaClass)) {
            listeners.add(activity as T)
        }
        return Collections.unmodifiableList(listeners)
    }

    /**
     * 因为setStyle()的原因，必须在必须在[onCreate]或[onStart]或[onResume]内调用
     */
    private fun initArguments() {
        val targetFragment = targetFragment
        if (targetFragment != null) {
            mRequestCode = targetRequestCode
        } else {
            val args = arguments
            if (args != null) {
                mRequestCode = args.getInt(BaseDialogBuilder.ARG_REQUEST_CODE, DEFAULT_REQUEST_CODE)
            }
        }

        val args = arguments
        if (args != null) {
            canceledOnTouchOutside = args.getBoolean(
                BaseDialogBuilder.ARG_CANCELABLE_ON_TOUCH_OUTSIDE,
                DEFAULT_CANCELABLE_ON_TOUCH_OUTSIDE
            )
            showFromBottom = args.getBoolean(
                BaseDialogBuilder.ARG_SHOW_FROM_BOTTOM,
                DEFAULT_SHOW_FROM_BOTTOM
            )
            expandBottomSheet = args.getBoolean(
                BaseDialogBuilder.ARG_EXPAND_BOTTOM_SHEET,
                DEFAULT_EXPAND_BOTTOM_SHEET
            )
            dimAmount = args.getFloat(BaseDialogBuilder.ARG_DIM_AMOUNT, DEFAULT_DIM_AMOUNT)
            scale = args.getDouble(BaseDialogBuilder.ARG_SCALE, DEFAULT_SCALE)
            animStyle = args.getInt(BaseDialogBuilder.ARG_ANIM_STYLE)
            val defaultTheme = if (theme == 0) -1 else theme
            mTheme = args.getInt(BaseDialogBuilder.ARG_USE_THEME, R.style.Dialog)
        }
        //必须在onCreate()内调用才起作用
        setStyle(DialogFragment.STYLE_NO_TITLE, mTheme)
    }

    private fun initDialogParams(dialog: Dialog?) {
        if (dialog == null) return
        dialog.setCanceledOnTouchOutside(canceledOnTouchOutside)
        val window = dialog.window
        if (window != null) {
            val lp = window.attributes
            //调节灰色背景透明度[0-1]，默认0.5f
            lp.dimAmount = dimAmount
            //是否在底部显示
            if (showFromBottom) {
                lp.gravity = Gravity.BOTTOM
                if (animStyle == 0) {
                    animStyle = R.style.Dialog_Animation
                }
                expandBottomSheet(dialog)
            }
            //占用屏幕宽度一定比例
            if (scale > 1) {
                scale = 1.0
            }
            lp.width = (getScreenWidth(mContext) * scale).toInt()
            //设置dialog高度
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT
            //设置dialog进入、退出的动画
            if (animStyle != 0) {
                window.setWindowAnimations(animStyle)
            }
            window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window.attributes = lp
        }
    }

    /**
     * BottomSheetDialog的默认高度为256dp，所以要处理一下全部展开。
     * @param dialog Dialog
     */
    private fun expandBottomSheet(dialog: Dialog) {
        if (expandBottomSheet && dialog is BottomSheetDialog) {
            view?.run {
                if (this is CoordinatorLayout) {
                    val child = this.getChildAt(0)
                    BottomSheetBehavior.from((child))
                        .state = BottomSheetBehavior.STATE_EXPANDED
                } else {
                    BottomSheetBehavior.from(this.parent as View)
                        .state = BottomSheetBehavior.STATE_EXPANDED
                }

            }
        }

    }

    private fun getScreenWidth(context: Context): Int {
        val dm = context.resources.displayMetrics
        return dm.widthPixels
    }

    /**
     * 如果两个dialog的tag一致，则隐藏上一个dialog
     *
     * 报"IllegalStateException : Can not perform this action after onSaveInstanceState()"异常的时候使用此show
     * @param manager FragmentManager
     * @param tag String
     */
    fun showAllowingStateLoss(manager: FragmentManager, tag: String, dismissPreDialog: Boolean?) {
        val ft = manager.beginTransaction()
        //将之前的dialog隐藏
        val targetFragment = manager.findFragmentByTag(tag)
        if (dismissPreDialog!! && targetFragment is BaseDialogFragment) {
            ft.remove(targetFragment)
        }

        ft.add(this, tag)
        ft.commitAllowingStateLoss()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}