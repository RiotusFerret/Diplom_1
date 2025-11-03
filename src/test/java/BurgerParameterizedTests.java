import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Database;
import praktikum.Ingredient;

import java.util.ArrayList;
import java.util.List;

@RunWith(Parameterized.class)
public class BurgerParameterizedTests {
    Burger burger = new Burger();
    Database data = new Database();

    int bunIndex;
    List<Integer> ingredientIndexes;
    float priceExp;
    List<String> receiptExp;

    public BurgerParameterizedTests(int bunIndex, List<Integer> ingredientIndexes, float priceExp, List<String> receiptExp) {
    this.bunIndex = bunIndex;
    this.ingredientIndexes = ingredientIndexes;
    this.priceExp = priceExp;
    this.receiptExp = receiptExp;
    }

    @Parameterized.Parameters
    public static Object[][] getListData() {
        return new Object[][] {
                {0, List.of(0), 300.0f, List.of("(==== black bun ====)%n", "= sauce hot sauce =%n", "(==== black bun ====)%n", "%nPrice: 300,000000%n")},
                {1, List.of(2, 3, 4), 1000.0f, List.of("(==== white bun ====)%n", "= sauce chili sauce =%n", "= filling cutlet =%n", "= filling dinosaur =%n", "(==== white bun ====)%n", "%nPrice: 1000,000000%n")},
                {2, List.of(0, 1, 2, 3, 4), 1500.0f, List.of("(==== red bun ====)%n", "= sauce hot sauce =%n", "= sauce sour cream =%n", "= sauce chili sauce =%n", "= filling cutlet =%n", "= filling dinosaur =%n", "(==== red bun ====)%n", "%nPrice: 1500,000000%n")},
        };
    }

    @Test
    public void getPriceParameterizedTest() {
        Bun bun = data.availableBuns().get(bunIndex);
        List<Ingredient> ingredients = new ArrayList<>();
        for (Integer ingredientIndex : ingredientIndexes) {
            ingredients.add(data.availableIngredients().get(ingredientIndex));
        }
        burger.setBuns(bun);
        for (Ingredient ingredient : ingredients) {
            burger.addIngredient(ingredient);
        }
        Assert.assertEquals(priceExp, burger.getPrice(), 0);
    }
    @Test
    public void getReceiptParameterizedTest() {
        Bun bun = data.availableBuns().get(bunIndex);
        List<Ingredient> ingredients = new ArrayList<>();
        for (Integer ingredientIndex : ingredientIndexes) {
            ingredients.add(data.availableIngredients().get(ingredientIndex));
        }
        burger.setBuns(bun);
        for (Ingredient ingredient : ingredients) {
            burger.addIngredient(ingredient);
        }
        StringBuilder receiptFull = new StringBuilder();
        for (String receiptPiece : receiptExp) {
            receiptFull.append(String.format(receiptPiece));
        }
        Assert.assertEquals(receiptFull.toString(), burger.getReceipt());
    }
}
