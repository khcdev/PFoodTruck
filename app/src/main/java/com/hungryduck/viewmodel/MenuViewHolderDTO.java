package com.hungryduck.viewmodel;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by kalin on 2017-05-28.
 */

public class MenuViewHolderDTO {
    private ImageView foodImage;
    private TextView foodName;
    private TextView foodPrice;
    private TextView foodOrigin;


    public ImageView getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(ImageView foodImage) {
        this.foodImage = foodImage;
    }

    public TextView getFoodName() {
        return foodName;
    }

    public void setFoodName(TextView foodName) {
        this.foodName = foodName;
    }

    public TextView getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(TextView foodPrice) {
        this.foodPrice = foodPrice;
    }

    public TextView getFoodOrigin() {
        return foodOrigin;
    }

    public void setFoodOrigin(TextView foodOrigin) {
        this.foodOrigin = foodOrigin;
    }
}
