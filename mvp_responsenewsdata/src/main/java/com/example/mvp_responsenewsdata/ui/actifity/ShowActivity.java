package com.example.mvp_responsenewsdata.ui.actifity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mvp_responsenewsdata.R;

public class ShowActivity extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        button = findViewById(R.id.btn_show);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator alpha = ObjectAnimator.ofFloat(v, "alpha", 1f, 0f, 1f);
                ObjectAnimator y = ObjectAnimator.ofFloat(v, "Y", 50, 800, 50);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.setDuration(3000);
                animatorSet.playTogether(alpha,y);
                animatorSet.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        startActivity(new Intent(ShowActivity.this,ImageActivity.class));
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
        });
    }
}
