package com.kaellah.switchappkotlin.view.base;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kaellah.switchappkotlin.utils.ContentLoadingController;
import com.kaellah.switchappkotlin.utils.ContentLoadingController.ContentLoadingListener;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;

public abstract class BaseFragment<V extends ViewModel> extends Fragment
        implements ContentLoadingListener {

    protected CompositeDisposable onStopDisposable = new CompositeDisposable();
    protected CompositeDisposable onDestroyViewDisposable = new CompositeDisposable();
    protected V viewModel;
    @Inject
    protected ViewModelProvider.Factory viewModelFactory;
    private PresentationDelegate presentationDelegate;
    private ContentLoadingController contentLoadingController;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        presentationDelegate = new PresentationDelegate(context);
        contentLoadingController = new ContentLoadingController();
        contentLoadingController.setListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getContentView() != View.NO_ID) {
            return inflater.inflate(getContentView(), container, false);
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViewModel();
    }

    @Override
    public void onStop() {
        onStopDisposable.clear();
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        contentLoadingController.clear();
        onDestroyViewDisposable.clear();
        super.onDestroyView();
    }

    @LayoutRes
    protected abstract int getContentView();

    protected abstract Class<V> getViewModelClass();

    /**
     * If you don't want to init view model, you can override method and stay empty
     */
    protected void initViewModel() {
        if (viewModelFactory == null) {
            Timber.w("ViewModelFactory is null!!! Please, check your dagger inject logic.");
            return;
        }
        //todo change this to get current fragment. This would be necessary on Sign Screen. Changed Sign Screen on SubComponent and then remove getBaseActivity and use current class (this)
        viewModel = ViewModelProviders.of(getBaseActivity(), viewModelFactory).get(getViewModelClass());
    }


    public BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

    public void showError(Throwable throwable) {
        presentationDelegate.showError(throwable);
    }

    public void showError(@Nullable Object tag, Throwable throwable) {
        presentationDelegate.showError(throwable);
    }

    public void showError(@Nullable Object tag, @StringRes int errorRes) {
        presentationDelegate.showError(errorRes);
    }

    public void showError(@Nullable Object tag, String error) {
        presentationDelegate.showError(error);
    }

    public void showProgress() {
        showProgress(null);
    }

    public void showProgress(@Nullable Object tag) {
        presentationDelegate.showProgress();
    }

    public void showProgressContent() {
        contentLoadingController.show();
    }

    public void hideProgress() {
        hideProgress(null);
    }

    public void hideProgress(@Nullable Object tag) {
        presentationDelegate.hideProgress();
    }

    public void hideProgressContent() {
        contentLoadingController.hide();
    }

    protected void startFragment(@NonNull BaseFragment fragment, boolean addToBackStack) {
        getBaseActivity().startFragment(fragment, addToBackStack);
    }

    @Override
    public void onShowContentProgress() {
        showProgress();
    }

    @Override
    public void onHideContentProgress() {
        hideProgress();
    }
}
