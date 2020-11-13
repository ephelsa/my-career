package com.github.ephelsa.mycareer.ui.utils

import androidx.viewbinding.ViewBinding

abstract class BaseFragment <Binding: ViewBinding> {
    protected lateinit var binding: Binding


}