package de.prttstft.materialmensa.extras;

import java.util.Random;

import de.prttstft.materialmensa.MyApplication;
import de.prttstft.materialmensa.R;
import de.prttstft.materialmensa.constants.RestaurantConstants;

public class RestaurantUtilites {

    private RestaurantUtilites() {

    }

    public static int getRandomEmoji() {
        Random random = new Random();
        int index = random.nextInt(6);

        switch (index) {
            case 0:
                return R.drawable.emoji_1f622;
            case 1:
                return R.drawable.emoji_1f625;
            case 2:
                return R.drawable.emoji_1f627;
            case 3:
                return R.drawable.emoji_1f62d;
            case 4:
                return R.drawable.emoji_1f630;
            case 5:
                return R.drawable.emoji_1f64a;
            default:
                return R.drawable.emoji_1f622;
        }
    }

    public static int getRestaurantHeader(int restaurant) {
        switch (restaurant) {
            case 0:
                return R.drawable.header_academica;
            case 1:
                return R.drawable.header_forum;
            case 2:
                return R.drawable.header_cafete;
            case 3:
                return R.drawable.header_mensula;
            case 4:
                return R.drawable.header_one_way_snack;
            case 5:
                return R.drawable.header_grill;
            default:
                return -1;
        }
    }

    public static int getRestaurantIcon(int restaurant) {
        switch (restaurant) {
            case 0:
                return R.drawable.ic_academica;
            case 1:
                return R.drawable.ic_forum;
            case 2:
                return R.drawable.ic_cafete;
            case 3:
                return R.drawable.ic_mensula;
            case 4:
                return R.drawable.ic_one_way_snack;
            case 5:
                return R.drawable.ic_grill_cafe;
            default:
                return -1;
        }
    }

    public static String getRestaurantName(int restaurantId) {
        switch (restaurantId) {
            case RestaurantConstants.RESTAURANT_ID_ACADEMICA:
                return MyApplication.getAppContext().getString(R.string.restaurant_mensa_academica_paderborn);
            case RestaurantConstants.RESTAURANT_ID_FORUM:
                return MyApplication.getAppContext().getString(R.string.restaurant_mensa_forum_paderborn);
            case RestaurantConstants.RESTAURANT_ID_CAFETE:
                return MyApplication.getAppContext().getString(R.string.restaurant_cafete);
            case RestaurantConstants.RESTAURANT_ID_MENSULA:
                return MyApplication.getAppContext().getString(R.string.restaurant_mensula);
            case RestaurantConstants.RESTAURANT_ID_ONE_WAY_SNACK:
                return MyApplication.getAppContext().getString(R.string.restaurant_one_way_snack);
            case RestaurantConstants.RESTAURANT_ID_GRILL_CAFE:
                return MyApplication.getAppContext().getString(R.string.restaurant_grill_cafe);
            default:
                return MyApplication.getAppContext().getString(R.string.restaurant_mensa_academica_paderborn);
        }
    }
}