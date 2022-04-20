package in.calibrage.easyfarm.Common;

import android.app.Application;

import com.instabug.library.Instabug;
import com.instabug.library.invocation.InstabugInvocationEvent;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        new Instabug.Builder(this, "ccc84a34d8c48f2147f01eaa82efb792")
                .setInvocationEvents(InstabugInvocationEvent.SHAKE, InstabugInvocationEvent.FLOATING_BUTTON)
                .build();
    }
}