// Generated by view binder compiler. Do not edit!
package com.example.hungryhopper.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.hungryhopper.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityDetailsBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final AppCompatButton button;

  @NonNull
  public final CardView cardView4;

  @NonNull
  public final TextView descriptiontextView;

  @NonNull
  public final ImageView detailFoodImage;

  @NonNull
  public final TextView detailFoodName;

  @NonNull
  public final ImageButton imageButton;

  @NonNull
  public final TextView ingredientsTextView;

  @NonNull
  public final ConstraintLayout main;

  @NonNull
  public final TextView textView26;

  @NonNull
  public final TextView textView28;

  private ActivityDetailsBinding(@NonNull ConstraintLayout rootView,
      @NonNull AppCompatButton button, @NonNull CardView cardView4,
      @NonNull TextView descriptiontextView, @NonNull ImageView detailFoodImage,
      @NonNull TextView detailFoodName, @NonNull ImageButton imageButton,
      @NonNull TextView ingredientsTextView, @NonNull ConstraintLayout main,
      @NonNull TextView textView26, @NonNull TextView textView28) {
    this.rootView = rootView;
    this.button = button;
    this.cardView4 = cardView4;
    this.descriptiontextView = descriptiontextView;
    this.detailFoodImage = detailFoodImage;
    this.detailFoodName = detailFoodName;
    this.imageButton = imageButton;
    this.ingredientsTextView = ingredientsTextView;
    this.main = main;
    this.textView26 = textView26;
    this.textView28 = textView28;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityDetailsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityDetailsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_details, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityDetailsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.button;
      AppCompatButton button = ViewBindings.findChildViewById(rootView, id);
      if (button == null) {
        break missingId;
      }

      id = R.id.cardView4;
      CardView cardView4 = ViewBindings.findChildViewById(rootView, id);
      if (cardView4 == null) {
        break missingId;
      }

      id = R.id.descriptiontextView;
      TextView descriptiontextView = ViewBindings.findChildViewById(rootView, id);
      if (descriptiontextView == null) {
        break missingId;
      }

      id = R.id.detailFoodImage;
      ImageView detailFoodImage = ViewBindings.findChildViewById(rootView, id);
      if (detailFoodImage == null) {
        break missingId;
      }

      id = R.id.detailFoodName;
      TextView detailFoodName = ViewBindings.findChildViewById(rootView, id);
      if (detailFoodName == null) {
        break missingId;
      }

      id = R.id.imageButton;
      ImageButton imageButton = ViewBindings.findChildViewById(rootView, id);
      if (imageButton == null) {
        break missingId;
      }

      id = R.id.ingredientsTextView;
      TextView ingredientsTextView = ViewBindings.findChildViewById(rootView, id);
      if (ingredientsTextView == null) {
        break missingId;
      }

      ConstraintLayout main = (ConstraintLayout) rootView;

      id = R.id.textView26;
      TextView textView26 = ViewBindings.findChildViewById(rootView, id);
      if (textView26 == null) {
        break missingId;
      }

      id = R.id.textView28;
      TextView textView28 = ViewBindings.findChildViewById(rootView, id);
      if (textView28 == null) {
        break missingId;
      }

      return new ActivityDetailsBinding((ConstraintLayout) rootView, button, cardView4,
          descriptiontextView, detailFoodImage, detailFoodName, imageButton, ingredientsTextView,
          main, textView26, textView28);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}