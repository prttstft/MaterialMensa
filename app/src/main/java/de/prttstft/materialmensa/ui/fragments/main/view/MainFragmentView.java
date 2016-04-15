package de.prttstft.materialmensa.ui.fragments.main.view;

import de.prttstft.materialmensa.model.Meal;

public interface MainFragmentView {
    void addMeal(Meal meal);

    void showProgress();

    void hideProgress();
}
