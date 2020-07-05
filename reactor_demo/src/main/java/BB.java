import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.locks.LockSupport;
import java.util.function.Supplier;

public class BB {

    public static void main(String[] args) {
        Observable.create(r -> {
            r.onNext("msg 1");
            r.onNext("msg 2");
        }).subscribeOn(Schedulers.io()).map(r -> r + "  1").subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {
                System.out.println(o);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });


        LockSupport.park();
    }
}
