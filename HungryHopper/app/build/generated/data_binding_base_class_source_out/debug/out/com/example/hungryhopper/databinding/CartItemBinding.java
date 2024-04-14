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
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.hungryhopper.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class CartItemBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final TextView cartFoodName;

  @NonNull
  public final ImageView cartImage;

  @NonNull
  public final TextView cartItemPrice;

  @NonNull
  public final ImageButton deleteButton;

  @NonNull
  public final ImageButton minusButton;

  @NonNull
  public final ImageButton plusButton;

  @NonNull
  public final TextView quantity;

  private CartItemBinding(@NonNull CardView rootView, @NonNull TextView cartFoodName,
      @NonNull ImageView cartImage, @NonNull TextView cartItemPrice,
      @NonNull ImageButton deleteButton, @NonNull ImageButton minusButton,
      @NonNull ImageButton plusButton, @NonNull TextView quantity) {
    this.rootView = rootView;
    this.cartFoodName = cartFoodName;
    this.cartImage = cartImage;
    this.cartItemPrice = cartItemPrice;
    this.deleteButton = deleteButton;
    this.minusButton = minusButton;
    this.plusButton = plusButton;
    this.quantity = quantity;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static CartItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static CartItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.cart_item, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static CartItemBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.cartFoodName;
      TextView cartFoodName = ViewBindings.findChildViewById(rootView, id);
      if (cartFoodName == null) {
        break missingId;
      }

      id = R.id.cartImage;
      ImageView cartImage = ViewBindings.findChildViewById(rootView, id);
      if (cartImage == null) {
        break missingId;
      }

      id = R.id.cartItemPrice;
      TextView cartItemPrice = ViewBindings.findChildViewById(rootView, id);
      if (cartItemPrice == null) {
        break missingId;
      }

      id = R.id.deleteButton;
      ImageButton deleteButton = ViewBindings.findChildViewById(rootView, id);
      if (deleteButton == null) {
        break missingId;
      }

      id = R.id.minusButton;
      ImageButton minusButton = ViewBindings.findChildViewById(rootView, id);
      if (minusButton == null) {
        break missingId;
      }

      id = R.id.plusButton;
      ImageButton plusButton = ViewBindings.findChildViewById(rootView, id);
      if (plusButton == null) {
        break missingId;
      }

      id = R.id.quantity;
      TextView quantity = ViewBindings.findChildViewById(rootView, id);
      if (quantity == null) {
        break missingId;
      }

      return new CartItemBinding((CardView) rootView, cartFoodName, cartImage, cartItemPrice,
          deleteButton, minusButton, plusButton, quantity);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
