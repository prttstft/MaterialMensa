package de.prttstft.materialmensa.extras

import de.prttstft.materialmensa.model.Meal


open class UtilitiesKt() {

    companion object {

        @JvmStatic fun sortMeals(meals: List<Meal>): List<Meal> {
            var newMeals = meals.sorted()

            return newMeals
        }

    }
}