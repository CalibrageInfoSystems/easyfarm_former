package in.calibrage.easyfarm.Views;

import android.os.Binder;

import in.calibrage.easyfarm.model.vendordata;
public class ObjectWrapperForBinder extends Binder {

    private final vendordata mData;

    public ObjectWrapperForBinder(vendordata data) {
        mData = data;
    }

    public vendordata getData() {
        return mData;
    }
}