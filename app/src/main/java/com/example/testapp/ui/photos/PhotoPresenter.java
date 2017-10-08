package com.example.testapp.ui.photos;

import com.example.testapp.core.BasePresenter;
import com.example.testapp.di.DaggerService;
import com.example.testapp.di.components.PhotoComponent;

public class PhotoPresenter extends BasePresenter<PhotoActivity> {

    @Override
    protected void initDaggerComponent() {
        PhotoComponent component = DaggerService.getComponent(DaggerService.PHOTO_SCOPE_NAME);
        if (component != null)
            component.inject(this);
    }

    void initView(int albumId) {
        if (getView() != null) {
            repository.getAlbumPhoto(albumId)
                    .subscribe(
                            photo -> getView().addPhoto(photo)/*,
                            throwable -> getView().showMessage(throwable.getMessage())*/
                    );
        }
    }
}
