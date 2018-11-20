package com.chaplin.test2.ui.adapters.binders;

public abstract class BaseViewBinder<T extends BaseViewBinderItem> {
    public abstract void bind(T item);
}
