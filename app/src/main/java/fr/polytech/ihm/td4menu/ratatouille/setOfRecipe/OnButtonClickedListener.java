package fr.polytech.ihm.td4menu.ratatouille.setOfRecipe;

import fr.polytech.ihm.td4menu.ratatouille.mvc.Model_Ratatouille;
import fr.polytech.ihm.td4menu.ratatouille.datas.Day;

public interface OnButtonClickedListener {
    void onButtonClicked(Model_Ratatouille model_ratatouille, Day day, int position);
    void onButtonClicked2(Model_Ratatouille model_ratatouille, int position);
}
