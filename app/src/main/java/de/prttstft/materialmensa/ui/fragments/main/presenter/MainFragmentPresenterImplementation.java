package de.prttstft.materialmensa.ui.fragments.main.presenter;

import de.prttstft.materialmensa.model.Meal;
import de.prttstft.materialmensa.ui.fragments.main.interactor.MainFragmentInteractor;
import de.prttstft.materialmensa.ui.fragments.main.interactor.MainFragmentInteractorImplementation;
import de.prttstft.materialmensa.ui.fragments.main.listener.MainFragmentListener;
import de.prttstft.materialmensa.ui.fragments.main.view.MainFragmentView;

public class MainFragmentPresenterImplementation implements MainFragmentPresenter, MainFragmentListener {
    private MainFragmentInteractor interactor;
    private MainFragmentView view;

    public MainFragmentPresenterImplementation(MainFragmentView view) {
        this.view = view;
        interactor = new MainFragmentInteractorImplementation();
    }

    @Override
    public void addMeal(Meal meal) {
        if (view != null) {
            view.addMeal(meal);
        }
    }

    @Override
    public void connectionError() {
        if (view != null) {
            view.showConnectionError();
        }
    }

    @Override
    public void filteredMeal() {
        if (view != null) {
            view.showFiltered();
        }
    }

    @Override
    public void downvoteMeal(Meal meal) {
        interactor.downvoteMeal(meal);
    }

    @Override
    public void getMeals(int day, int restaurant) {
        if (view != null) {
            view.showProgress();
        }

        interactor.getMeals(this, day, restaurant);
    }

    @Override
    public void upvoteMeal(Meal meal) {
        interactor.upvoteMeal(meal);
    }

    @Override
    public void onCompleted() {
        if (view != null) {
            view.onCompleted();
        }
    }

    @Override
    public void sendSocialData(Meal meal) {
        if (view != null) {
            view.sendSocialData(meal);
        }
    }
}