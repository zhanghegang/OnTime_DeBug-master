package zhanghegang.com.bawei.onetime.base;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;
import zhanghegang.com.bawei.onetime.R;

/**
 * current package:zhanghegang.com.bawei.onetime.base
 * Created by Mr.zhang
 * date: 2017/11/23
 * decription:开发
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment {

    public View view;
public T presenter;
public abstract T initPresenter();





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if(view==null)
        {
        view = LayoutInflater.from(getActivity()).inflate(getViewId(),null);
        }
        ViewGroup viewGroup= (ViewGroup) view.getParent();
        if(viewGroup!=null)
        {
            viewGroup.removeView(view);
        }
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter=initPresenter();
        initFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        resumFragment();
    }
    public void start(Class<?> tclass,boolean isFinish){
        startActivity(new Intent(getActivity(),tclass));
        if(isFinish)
        {
            getActivity().finish();
        }
    }
    public void start(Class<?> tclass,boolean isFinish,Bundle bundle){
        Intent intent = new Intent(getActivity(), tclass);
        intent.putExtras(bundle);
        startActivity(intent);
        if(isFinish)
        {
            getActivity().finish();
        }
    }
    public void showToast(String msg){
        Toast.makeText(getActivity(), msg+"", Toast.LENGTH_SHORT).show();
    }

    public abstract int getViewId();
    public abstract void initFragment();
    public abstract void resumFragment();
}
