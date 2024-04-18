// Generated by view binder compiler. Do not edit!
package com.example.adminhungryhopper.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.adminhungryhopper.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMainBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ConstraintLayout addMenuMain;

  @NonNull
  public final ConstraintLayout allItemMenu;

  @NonNull
  public final CardView cardView;

  @NonNull
  public final CardView cardView2;

  @NonNull
  public final CardView cardView3;

  @NonNull
  public final CardView cardView4;

  @NonNull
  public final CardView cardView5;

  @NonNull
  public final CardView cardView7;

  @NonNull
  public final ConstraintLayout createUser;

  @NonNull
  public final Guideline guideline;

  @NonNull
  public final Guideline guideline2;

  @NonNull
  public final Guideline guideline3;

  @NonNull
  public final ImageView imageView10;

  @NonNull
  public final ImageView imageView11;

  @NonNull
  public final ImageView imageView2;

  @NonNull
  public final ImageView imageView3;

  @NonNull
  public final ImageView imageView4;

  @NonNull
  public final ImageView imageView5;

  @NonNull
  public final ImageView imageView6;

  @NonNull
  public final ImageView imageView7;

  @NonNull
  public final ImageView imageView8;

  @NonNull
  public final ImageView imageView9;

  @NonNull
  public final ConstraintLayout logOutBtn;

  @NonNull
  public final ConstraintLayout main;

  @NonNull
  public final CardView outForDeliveryBtn;

  @NonNull
  public final TextView pendingOrders;

  @NonNull
  public final ConstraintLayout profileBtn;

  @NonNull
  public final TextView texView19;

  @NonNull
  public final TextView textView10;

  @NonNull
  public final TextView textView7;

  @NonNull
  public final TextView textView9;

  private ActivityMainBinding(@NonNull ConstraintLayout rootView,
      @NonNull ConstraintLayout addMenuMain, @NonNull ConstraintLayout allItemMenu,
      @NonNull CardView cardView, @NonNull CardView cardView2, @NonNull CardView cardView3,
      @NonNull CardView cardView4, @NonNull CardView cardView5, @NonNull CardView cardView7,
      @NonNull ConstraintLayout createUser, @NonNull Guideline guideline,
      @NonNull Guideline guideline2, @NonNull Guideline guideline3, @NonNull ImageView imageView10,
      @NonNull ImageView imageView11, @NonNull ImageView imageView2, @NonNull ImageView imageView3,
      @NonNull ImageView imageView4, @NonNull ImageView imageView5, @NonNull ImageView imageView6,
      @NonNull ImageView imageView7, @NonNull ImageView imageView8, @NonNull ImageView imageView9,
      @NonNull ConstraintLayout logOutBtn, @NonNull ConstraintLayout main,
      @NonNull CardView outForDeliveryBtn, @NonNull TextView pendingOrders,
      @NonNull ConstraintLayout profileBtn, @NonNull TextView texView19,
      @NonNull TextView textView10, @NonNull TextView textView7, @NonNull TextView textView9) {
    this.rootView = rootView;
    this.addMenuMain = addMenuMain;
    this.allItemMenu = allItemMenu;
    this.cardView = cardView;
    this.cardView2 = cardView2;
    this.cardView3 = cardView3;
    this.cardView4 = cardView4;
    this.cardView5 = cardView5;
    this.cardView7 = cardView7;
    this.createUser = createUser;
    this.guideline = guideline;
    this.guideline2 = guideline2;
    this.guideline3 = guideline3;
    this.imageView10 = imageView10;
    this.imageView11 = imageView11;
    this.imageView2 = imageView2;
    this.imageView3 = imageView3;
    this.imageView4 = imageView4;
    this.imageView5 = imageView5;
    this.imageView6 = imageView6;
    this.imageView7 = imageView7;
    this.imageView8 = imageView8;
    this.imageView9 = imageView9;
    this.logOutBtn = logOutBtn;
    this.main = main;
    this.outForDeliveryBtn = outForDeliveryBtn;
    this.pendingOrders = pendingOrders;
    this.profileBtn = profileBtn;
    this.texView19 = texView19;
    this.textView10 = textView10;
    this.textView7 = textView7;
    this.textView9 = textView9;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.addMenuMain;
      ConstraintLayout addMenuMain = ViewBindings.findChildViewById(rootView, id);
      if (addMenuMain == null) {
        break missingId;
      }

      id = R.id.allItemMenu;
      ConstraintLayout allItemMenu = ViewBindings.findChildViewById(rootView, id);
      if (allItemMenu == null) {
        break missingId;
      }

      id = R.id.cardView;
      CardView cardView = ViewBindings.findChildViewById(rootView, id);
      if (cardView == null) {
        break missingId;
      }

      id = R.id.cardView2;
      CardView cardView2 = ViewBindings.findChildViewById(rootView, id);
      if (cardView2 == null) {
        break missingId;
      }

      id = R.id.cardView3;
      CardView cardView3 = ViewBindings.findChildViewById(rootView, id);
      if (cardView3 == null) {
        break missingId;
      }

      id = R.id.cardView4;
      CardView cardView4 = ViewBindings.findChildViewById(rootView, id);
      if (cardView4 == null) {
        break missingId;
      }

      id = R.id.cardView5;
      CardView cardView5 = ViewBindings.findChildViewById(rootView, id);
      if (cardView5 == null) {
        break missingId;
      }

      id = R.id.cardView7;
      CardView cardView7 = ViewBindings.findChildViewById(rootView, id);
      if (cardView7 == null) {
        break missingId;
      }

      id = R.id.createUser;
      ConstraintLayout createUser = ViewBindings.findChildViewById(rootView, id);
      if (createUser == null) {
        break missingId;
      }

      id = R.id.guideline;
      Guideline guideline = ViewBindings.findChildViewById(rootView, id);
      if (guideline == null) {
        break missingId;
      }

      id = R.id.guideline2;
      Guideline guideline2 = ViewBindings.findChildViewById(rootView, id);
      if (guideline2 == null) {
        break missingId;
      }

      id = R.id.guideline3;
      Guideline guideline3 = ViewBindings.findChildViewById(rootView, id);
      if (guideline3 == null) {
        break missingId;
      }

      id = R.id.imageView10;
      ImageView imageView10 = ViewBindings.findChildViewById(rootView, id);
      if (imageView10 == null) {
        break missingId;
      }

      id = R.id.imageView11;
      ImageView imageView11 = ViewBindings.findChildViewById(rootView, id);
      if (imageView11 == null) {
        break missingId;
      }

      id = R.id.imageView2;
      ImageView imageView2 = ViewBindings.findChildViewById(rootView, id);
      if (imageView2 == null) {
        break missingId;
      }

      id = R.id.imageView3;
      ImageView imageView3 = ViewBindings.findChildViewById(rootView, id);
      if (imageView3 == null) {
        break missingId;
      }

      id = R.id.imageView4;
      ImageView imageView4 = ViewBindings.findChildViewById(rootView, id);
      if (imageView4 == null) {
        break missingId;
      }

      id = R.id.imageView5;
      ImageView imageView5 = ViewBindings.findChildViewById(rootView, id);
      if (imageView5 == null) {
        break missingId;
      }

      id = R.id.imageView6;
      ImageView imageView6 = ViewBindings.findChildViewById(rootView, id);
      if (imageView6 == null) {
        break missingId;
      }

      id = R.id.imageView7;
      ImageView imageView7 = ViewBindings.findChildViewById(rootView, id);
      if (imageView7 == null) {
        break missingId;
      }

      id = R.id.imageView8;
      ImageView imageView8 = ViewBindings.findChildViewById(rootView, id);
      if (imageView8 == null) {
        break missingId;
      }

      id = R.id.imageView9;
      ImageView imageView9 = ViewBindings.findChildViewById(rootView, id);
      if (imageView9 == null) {
        break missingId;
      }

      id = R.id.logOutBtn;
      ConstraintLayout logOutBtn = ViewBindings.findChildViewById(rootView, id);
      if (logOutBtn == null) {
        break missingId;
      }

      ConstraintLayout main = (ConstraintLayout) rootView;

      id = R.id.outForDeliveryBtn;
      CardView outForDeliveryBtn = ViewBindings.findChildViewById(rootView, id);
      if (outForDeliveryBtn == null) {
        break missingId;
      }

      id = R.id.pendingOrders;
      TextView pendingOrders = ViewBindings.findChildViewById(rootView, id);
      if (pendingOrders == null) {
        break missingId;
      }

      id = R.id.profileBtn;
      ConstraintLayout profileBtn = ViewBindings.findChildViewById(rootView, id);
      if (profileBtn == null) {
        break missingId;
      }

      id = R.id.texView19;
      TextView texView19 = ViewBindings.findChildViewById(rootView, id);
      if (texView19 == null) {
        break missingId;
      }

      id = R.id.textView10;
      TextView textView10 = ViewBindings.findChildViewById(rootView, id);
      if (textView10 == null) {
        break missingId;
      }

      id = R.id.textView7;
      TextView textView7 = ViewBindings.findChildViewById(rootView, id);
      if (textView7 == null) {
        break missingId;
      }

      id = R.id.textView9;
      TextView textView9 = ViewBindings.findChildViewById(rootView, id);
      if (textView9 == null) {
        break missingId;
      }

      return new ActivityMainBinding((ConstraintLayout) rootView, addMenuMain, allItemMenu,
          cardView, cardView2, cardView3, cardView4, cardView5, cardView7, createUser, guideline,
          guideline2, guideline3, imageView10, imageView11, imageView2, imageView3, imageView4,
          imageView5, imageView6, imageView7, imageView8, imageView9, logOutBtn, main,
          outForDeliveryBtn, pendingOrders, profileBtn, texView19, textView10, textView7,
          textView9);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
