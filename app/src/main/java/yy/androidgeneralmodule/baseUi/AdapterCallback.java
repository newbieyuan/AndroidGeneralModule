package yy.androidgeneralmodule.baseUi;

/**
 * Created by YY on 2019/5/15.
 * Adapter 回调
 */

public interface AdapterCallback<Data> {
    void update(Data data, RecyclerAdapter.ViewHolder<Data> holder);
}
