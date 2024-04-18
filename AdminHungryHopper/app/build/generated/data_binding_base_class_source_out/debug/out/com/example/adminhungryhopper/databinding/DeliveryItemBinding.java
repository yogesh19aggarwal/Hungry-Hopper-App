// Generated by view binder compiler. Do not edit!
package com.example.adminhungryhopper.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.adminhungryhopper.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class DeliveryItemBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView customerName;

  @NonNull
  public final CardView orderStatus;

  @NonNull
  public final TextView payment;

  @NonNull
  public final TextView textView16;

  @NonNull
  public final TextView textView2;

  private DeliveryItemBinding(@NonNull ConstraintLayout rootView, @NonNull TextView customerName,
      @NonNull CardView orderStatus, @NonNull TextView payment, @NonNull TextView textView16,
      @NonNull TextView textView2) {
    this.rootView = rootView;
    this.customerName = customerName;
    this.orderStatus = orderStatus;
    this.payment = payment;
    this.textView16 = textView16;
    this.textView2 = textView2;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static DeliveryItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DeliveryItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.delivery_item, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DeliveryItemBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.customerName;
      TextView customerName = ViewBindings.findChildViewById(rootView, id);
      if (customerName == null) {
        break missingId;
      }

      id = R.id.orderStatus;
      CardView orderStatus = ViewBindings.findChildViewById(rootView, id);
      if (orderStatus == null) {
        break missingId;
      }

      id = R.id.payment;
      TextView payment = ViewBindings.findChildViewById(rootView, id);
      if (payment == null) {
        break missingId;
      }

      id = R.id.textView16;
      TextView textView16 = ViewBindings.findChildViewById(rootView, id);
      if (textView16 == null) {
        break missingId;
      }

      id = R.id.textView2;
      TextView textView2 = ViewBindings.findChildViewById(rootView, id);
      if (textView2 == null) {
        break missingId;
      }

      return new DeliveryItemBinding((ConstraintLayout) rootView, customerName, orderStatus,
          payment, textView16, textView2);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}