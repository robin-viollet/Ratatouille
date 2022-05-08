package fr.polytech.ihm.td4menu.ratatouille.recipe.create;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import fr.polytech.ihm.td4menu.ratatouille.MVC.Model_Ratatouille;
import fr.polytech.ihm.td4menu.ratatouille.R;
import fr.polytech.ihm.td4menu.ratatouille.datas.Day;
import fr.polytech.ihm.td4menu.ratatouille.datas.Recipe;

public class MenuOfRecipeCreation extends AppCompatActivity {
    FragmentTransaction fragmentTransaction;
    RecipeCreationFragment menuOfRecipeCreationFragment1;
    RecipeCreationFragment menuOfRecipeCreationFragment2;
    RecipePresFragment recipePresFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_of_recipe_creation);

        Day day = getIntent().getParcelableExtra("DAY");
        day = new Day(0,null,new Recipe(1,"Ratatouille"));

        if (menuOfRecipeCreationFragment1 == null && day.getFirstRecipe() == null) {
            menuOfRecipeCreationFragment1 = new RecipeCreationFragment();
            fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.menuOfRecipeCreation1, menuOfRecipeCreationFragment1);
            fragmentTransaction.commit();
        }else{
            recipePresFragment = new RecipePresFragment();
            Bundle args = new Bundle();
            args.putString("recipe_pres_name", day.getFirstRecipe().getName());
            recipePresFragment.setArguments(args);
            fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.menuOfRecipeCreation1, recipePresFragment);
            fragmentTransaction.commit();
        }

        if (menuOfRecipeCreationFragment2 == null && day.getSecondRecipe() == null) {
            menuOfRecipeCreationFragment2 = new RecipeCreationFragment();
            fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.menuOfRecipeCreation2, menuOfRecipeCreationFragment2);
            fragmentTransaction.commit();
        }else{
            recipePresFragment = new RecipePresFragment();
            Bundle args = new Bundle();
            args.putString("recipe_pres_name", day.getSecondRecipe().getName());
            recipePresFragment.setArguments(args);
            fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.menuOfRecipeCreation2, recipePresFragment);
            fragmentTransaction.commit();
        }
    }
}