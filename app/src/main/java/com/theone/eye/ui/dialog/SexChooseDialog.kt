package com.theone.eye.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.theone.eye.R
import com.theone.eye.databinding.DialogChooseSexBinding
import com.theone.framework.ext.clickWithTrigger
import com.theone.framework.widget.dialog.BaseDialogFragment
import com.theone.framework.widget.toast.ToastUtil

/**
 * @Author ZhiQiang
 * @Date 4/11/21
 * @Description
 */
class SexChooseDialog : BaseDialogFragment() {
    var onChooseListener: OnChooseListener? = null
    lateinit var binding: DialogChooseSexBinding
    var isMan: Int = -1
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DialogChooseSexBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.sexRg.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.manRb -> {
                    isMan = 1
                }
                R.id.womanRb -> {
                    isMan = 0
                }
            }
        }
        binding.confirmBtn.clickWithTrigger {
            if (isMan == -1) {
                ToastUtil.show(getString(R.string.sex_not_choose))
                return@clickWithTrigger
            }
            dismiss()
            onChooseListener?.onConfirmChoose(isMan == 1)
        }
    }

    interface OnChooseListener {
        fun onConfirmChoose(isMan: Boolean)
    }

    companion object {

        fun newInstance(listener: OnChooseListener): SexChooseDialog {
            val fragment = SexChooseDialog().apply {
                setScale(0.9)
                setScaleHeight(0.85)
                setCanceledOnTouchOutside(false)
            }
            fragment.onChooseListener = listener
            /*val bundle = fragment.arguments ?: Bundle()
            bundle.putSerializable(LIST_INFO_KEY, totalInfoList)
            fragment.arguments = bundle*/

            return fragment
        }
    }
}