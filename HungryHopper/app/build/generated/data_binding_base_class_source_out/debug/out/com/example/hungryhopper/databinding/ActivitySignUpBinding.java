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

public final class ActivitySignUpBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView alreadyHaveAcc;

  @NonNull
  public final AppCompatButton button2;

  @NonNull
  public final AppCompatButton button3;

  @NonNull
  public final EditText editTextText;

  @NonNull
  public final EditText editTextTextEmailAddress2;

  @NonNull
  public final EditText editTextTextPassword2;

  @NonNull
  public final ImageView imageView4;

  @NonNull
  public final ConstraintLayout main;

  @NonNull
  public final AppCompatButton signUpbtn;

  @NonNull
  public final TextView textView10;

  @NonNull
  public final TextView textView12;

  @NonNull
  public final TextView textView13;

  @NonNull
  public final TextView textView14;

  @NonNull
  public final TextView textView9;

  private ActivitySignUpBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextView alreadyHaveAcc, @NonNull AppCompatButton button2,
      @NonNull AppCompatButton button3, @NonNull EditText editTextText,
      @NonNull EditText editTextTextEmailAddress2, @NonNull EditText editTextTextPassword2,
      @NonNull ImageView imageView4, @NonNull ConstraintLayout main,
      @NonNull AppCompatButton signUpbtn, @NonNull TextView textView10,
      @NonNull TextView textView12, @NonNull TextView textView13, @NonNull TextView textView14,
      @NonNull TextView textView9) {
    this.rootView = rootView;
    this.alreadyHaveAcc = alreadyHaveAcc;
    this.button2 = button2;
    this.button3 = button3;
    this.editTextText = editTextText;
    this.editTextTextEmailAddress2 = editTextTextEmailAddress2;
    this.editTextTextPassword2 = editTextTextPassword2;
    this.imageView4 = imageView4;
    this.main = main;
    this.signUpbtn = signUpbtn;
    this.textView10 = textView10;
    this.textView12 = textView12;
    this.textView13 = textView13;
    this.textView14 = textView14;
    this.textView9 = textView9;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySignUpBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySignUpBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_sign_up, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySignUpBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.alreadyHaveAcc;
      TextView alreadyHaveAcc = ViewBindings.findChildViewById(rootView, id);
      if (alreadyHaveAcc == null) {
        break missingId;
      }

      id = R.id.button2;
      AppCompatButton button2 = ViewBindings.findChildViewById(rootView, id);
      if (button2 == null) {
        break missingId;
      }

      id = R.id.button3;
      AppCompatButton button3 = ViewBindings.findChildViewById(rootView, id);
      if (button3 == null) {
        break missingId;
      }

      id = R.id.editTextText;
      EditText editTextText = ViewBindings.findChildViewById(rootView, id);
      if (editTextText == null) {
        break missingId;
      }

      id = R.id.editTextTextEmailAddress2;
      EditText editTextTextEmailAddress2 = ViewBindings.findChildViewById(rootView, id);
      if (editTextTextEmailAddress2 == null) {
        break missingId;
      }

      id = R.id.editTextTextPassword2;
      EditText editTextTextPassword2 = ViewBindings.findChildViewById(rootView, id);
      if (editTextTextPassword2 == null) {
        break missingId;
      }

      id = R.id.imageView4;
      ImageView imageView4 = ViewBindings.findChildViewById(rootView, id);
      if (imageView4 == null) {
        break missingId;
      }

      ConstraintLayout main = (ConstraintLayout) rootView;

      id = R.id.signUpbtn;
      AppCompatButton signUpbtn = ViewBindings.findChildViewById(rootView, id);
      if (signUpbtn == null) {
        break missingId;
      }

      id = R.id.textView10;
      TextView textView10 = ViewBindings.findChildViewById(rootView, id);
      if (textView10 == null) {
        break missingId;
      }

      id = R.id.textView12;
      TextView textView12 = ViewBindings.findChildViewById(rootView, id);
      if (textView12 == null) {
        break missingId;
      }

      id = R.id.textView13;
      TextView textView13 = ViewBindings.findChildViewById(rootView, id);
      if (textView13 == null) {
        break missingId;
      }

      id = R.id.textView14;
      TextView textView14 = ViewBindings.findChildViewById(rootView, id);
      if (textView14 == null) {
        break missingId;
      }

      id = R.id.textView9;
      TextView textView9 = ViewBindings.findChildViewById(rootView, id);
      if (textView9 == null) {
        break missingId;
      }

      return new ActivitySignUpBinding((ConstraintLayout) rootView, alreadyHaveAcc, button2,
          button3, editTextText, editTextTextEmailAddress2, editTextTextPassword2, imageView4, main,
          signUpbtn, textView10, textView12, textView13, textView14, textView9);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
