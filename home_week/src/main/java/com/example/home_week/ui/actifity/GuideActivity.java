package com.example.home_week.ui.actifity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.home_week.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuideActivity extends AppCompatActivity {

    @BindView(R.id.tv_contains)
    TextView tvContains;
    @BindView(R.id.btn_do_animator)
    Button btnDoAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_do_animator)
    public void onViewClicked() {
        ObjectAnimator alpha = ObjectAnimator.ofFloat(tvContains, "alpha", 1f, 0f, 1f, 0f, 1f, 0f, 1f, 0f);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(tvContains, "scaleX", 1f, 3f, 1f, 3f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(tvContains, "scaleY", 1f, 3f, 1f, 3f);
        ObjectAnimator rotation = ObjectAnimator.ofFloat(tvContains, "rotation", 0f, 360f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(alpha,scaleX,scaleY,rotation);
        animatorSet.setDuration(5000);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                startActivity(new Intent(GuideActivity.this,ShowActivity.class));
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet.start();


    }
}
