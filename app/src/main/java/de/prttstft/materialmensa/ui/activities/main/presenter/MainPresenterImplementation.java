package de.prttstft.materialmensa.ui.activities.main.presenter;

import de.prttstft.materialmensa.ui.activities.main.interactor.MainInteractor;
import de.prttstft.materialmensa.ui.activities.main.interactor.MainInteractorImplementation;
import de.prttstft.materialmensa.ui.activities.main.listener.MainListener;
import de.prttstft.materialmensa.ui.activities.main.view.MainView;

public class MainPresenterImplementation implements MainPresenter, MainListener {
    private MainInteractor interactor;
    private MainView view;

    public MainPresenterImplementation(MainView view) {
        this.view = view;
        interactor = new MainInteractorImplementation();
    }


}
