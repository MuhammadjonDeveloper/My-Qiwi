package com.example.muhammadjon.myproject.presentation;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.example.muhammadjon.myproject.activities.IWindowView;
import com.example.muhammadjon.myproject.activities.WindowActivity;
import com.example.muhammadjon.myproject.dbase.Merchants;
import com.example.muhammadjon.myproject.model.IWindowRepository;
import com.example.muhammadjon.myproject.model.WindowRepository;

import java.util.List;

public class WindowPresenter implements IWindowPrisenter {
    private IWindowRepository repository;
    private LifecycleOwner owner;
    private IWindowView view;
    private static IWindowPrisenter INSTAINCE;

    public static IWindowPrisenter getINSTAINCE(WindowActivity activity) {
        if (INSTAINCE==null) {
            INSTAINCE=new WindowPresenter(activity);
        }
        return INSTAINCE;
    }

    private WindowPresenter(WindowActivity activity) {
        this.owner=activity;
        this.view=activity;
        repository= WindowRepository.getINSTAINS(activity);

    }

    @Override
    public void onMerchants(int categoryId) {
      view.onShowPb();
      repository.search(categoryId)
              .observe(owner, this::checklist);
    }

    private void checklist(List<Merchants> merchants){
        if (merchants==null) {
            view.onError("ERROR MASSAGE :");
        }else {
            view.onResult(merchants);
            view.onHidePb();
        }


    }
}
