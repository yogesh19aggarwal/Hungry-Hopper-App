// Generated by view binder compiler. Do not edit!
package com.example.hungryhopper.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.hungryhopper.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentHistoryBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final CardView cardView2;

  @NonNull
  public final TextView historyFood;

  @NonNull
  public final ImageView historyImage;

  @NonNull
  public final TextView historyPrice;

  @NonNull
  public final RecyclerView historyRecyclerView;

  @NonNull
  public final CardView orderStatus;

  @NonNull
  public final ConstraintLayout recentBuyItem;

  @NonNull
  public final AppCompatButton recievedButton;

  @NonNull
  public final TextView textView21;

  @NonNull
  public final TextView textView24;

  private FragmentHistoryBinding(@NonNull ConstraintLayout rootView, @NonNull CardView cardView2,
      @NonNull TextView historyFood, @NonNull ImageView historyImage,
      @NonNull TextView historyPrice, @NonNull RecyclerView historyRecyclerView,
      @NonNull CardView orderStatus, @NonNull ConstraintLayout recentBuyItem,
      @NonNull AppCompatButton recievedButton, @NonNull TextView textView21,
      @NonNull TextView textView24) {
    this.rootView = rootView;
    this.cardView2 = cardView2;
    this.historyFood = historyFood;
    this.historyImage = historyImage;
    this.historyPrice = historyPrice;
    this.historyRecyclerView = historyRecyclerView;
    this.orderStatus = orderStatus;
    this.recentBuyItem = recentBuyItem;
    this.recievedButton = recievedButton;
    this.textView21 = textView21;
    this.textView24 = textView24;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentHistoryBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentHistoryBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_history, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentHistoryBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.cardView2;
      CardView cardView2 = ViewBindings.findChildViewById(rootView, id);
      if (cardView2 == null) {
        break missingId;
      }

      id = R.id.historyFood;
      TextView historyFood = ViewBindings.findChildViewById(rootView, id);
      if (historyFood == null) {
        break missingId;
      }

      id = R.id.historyImage;
      ImageView historyImage = ViewBindings.findChildViewById(rootView, id);
      if (historyImage == null) {
        break missingId;
      }

      id = R.id.historyPrice;
      TextView historyPrice = ViewBindings.findChildViewById(rootView, id);
      if (historyPrice == null) {
        break missingId;
      }

      id = R.id.historyRecyclerView;
      RecyclerView historyRecyclerView = ViewBindings.findChildViewById(rootView, id);
      if (historyRecyclerView == null) {
        break missingId;
      }

      id = R.id.orderStatus;
      CardView orderStatus = ViewBindings.findChildViewById(rootView, id);
      if (orderStatus == null) {
        break missingId;
      }

      id = R.id.recentBuyItem;
      ConstraintLayout recentBuyItem = ViewBindings.findChildViewById(rootView, id);
      if (recentBuyItem == null) {
        break missingId;
      }

      id = R.id.recievedButton;
      AppCompatButton recievedButton = ViewBindings.findChildViewById(rootView, id);
      if (recievedButton == null) {
        break missingId;
      }

      id = R.id.textView21;
      TextView textView21 = ViewBindings.findChildViewById(rootView, id);
      if (textView21 == null) {
        break missingId;
      }

      id = R.id.textView24;
      TextView textView24 = ViewBindings.findChildViewById(rootView, id);
      if (textView24 == null) {
        break missingId;
      }

      return new FragmentHistoryBinding((ConstraintLayout) rootView, cardView2, historyFood,
          historyImage, historyPrice, historyRecyclerView, orderStatus, recentBuyItem,
          recievedButton, textView21, textView24);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
