// Generated by view binder compiler. Do not edit!
package com.example.hungryhopper.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.hungryhopper.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityLoginBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView dontHaveAccount;

  @NonNull
  public final EditText email;

  @NonNull
  public final AppCompatButton googleBtn;

  @NonNull
  public final ImageView imageView3;

  @NonNull
  public final AppCompatButton loginbtn;

  @NonNull
  public final ConstraintLayout main;

  @NonNull
  public final EditText password;

  @NonNull
  public final TextView textView10;

  @NonNull
  public final TextView textView6;

  @NonNull
  public final TextView textView7;

  @NonNull
  public final TextView textView8;

  @NonNull
  public final TextView textView9;

  private ActivityLoginBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextView dontHaveAccount, @NonNull EditText email,
      @NonNull AppCompatButton googleBtn, @NonNull ImageView imageView3,
      @NonNull AppCompatButton loginbtn, @NonNull ConstraintLayout main, @NonNull EditText password,
      @NonNull TextView textView10, @NonNull TextView textView6, @NonNull TextView textView7,
      @NonNull TextView textView8, @NonNull TextView textView9) {
    this.rootView = rootView;
    this.dontHaveAccount = dontHaveAccount;
    this.email = email;
    this.googleBtn = googleBtn;
    this.imageView3 = imageView3;
    this.loginbtn = loginbtn;
    this.main = main;
    this.password = password;
    this.textView10 = textView10;
    this.textView6 = textView6;
    this.textView7 = textView7;
    this.textView8 = textView8;
    this.textView9 = textView9;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_login, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityLoginBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.dontHaveAccount;
      TextView dontHaveAccount = ViewBindings.findChildViewById(rootView, id);
      if (dontHaveAccount == null) {
        break missingId;
      }

      id = R.id.email;
      EditText email = ViewBindings.findChildViewById(rootView, id);
      if (email == null) {
        break missingId;
      }

      id = R.id.googleBtn;
      AppCompatButton googleBtn = ViewBindings.findChildViewById(rootView, id);
      if (googleBtn == null) {
        break missingId;
      }

      id = R.id.imageView3;
      ImageView imageView3 = ViewBindings.findChildViewById(rootView, id);
      if (imageView3 == null) {
        break missingId;
      }

      id = R.id.loginbtn;
      AppCompatButton loginbtn = ViewBindings.findChildViewById(rootView, id);
      if (loginbtn == null) {
        break missingId;
      }

      ConstraintLayout main = (ConstraintLayout) rootView;

      id = R.id.password;
      EditText password = ViewBindings.findChildViewById(rootView, id);
      if (password == null) {
        break missingId;
      }

      id = R.id.textView10;
      TextView textView10 = ViewBindings.findChildViewById(rootView, id);
      if (textView10 == null) {
        break missingId;
      }

      id = R.id.textView6;
      TextView textView6 = ViewBindings.findChildViewById(rootView, id);
      if (textView6 == null) {
        break missingId;
      }

      id = R.id.textView7;
      TextView textView7 = ViewBindings.findChildViewById(rootView, id);
      if (textView7 == null) {
        break missingId;
      }

      id = R.id.textView8;
      TextView textView8 = ViewBindings.findChildViewById(rootView, id);
      if (textView8 == null) {
        break missingId;
      }

      id = R.id.textView9;
      TextView textView9 = ViewBindings.findChildViewById(rootView, id);
      if (textView9 == null) {
        break missingId;
      }

      return new ActivityLoginBinding((ConstraintLayout) rootView, dontHaveAccount, email,
          googleBtn, imageView3, loginbtn, main, password, textView10, textView6, textView7,
          textView8, textView9);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
