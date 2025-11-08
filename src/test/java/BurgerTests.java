import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import static praktikum.IngredientType.SAUCE;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTests {

    Burger burger = new Burger();

    @Mock
    Bun bun;

    @Mock
    Ingredient ingredient;

    @Mock
    Ingredient ingredientTwo;

    @Test
    public void setBunsTest() {
        burger.setBuns(bun);
        Assert.assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientTest() {
        burger.addIngredient(ingredient);
        Assert.assertEquals(false, burger.ingredients.isEmpty());
    }

    @Test
    public void removeIngredientTest() {
        burger.addIngredient(ingredient);
        int lengthBeforeRemove = burger.ingredients.size();
        burger.removeIngredient(0);
        Assert.assertEquals(lengthBeforeRemove, burger.ingredients.size() + 1);
    }

    @Test
    public void moveIngredientTest() {
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredientTwo);
        burger.moveIngredient(0, 1);
        Assert.assertEquals(ingredientTwo, burger.ingredients.get(0));
    }

    @Test
    public void getPriceTest() {
        burger.setBuns(bun);
        Mockito.when(bun.getPrice()).thenReturn(100.0f);
        burger.addIngredient(ingredient);
        Mockito.when(ingredient.getPrice()).thenReturn(200.0f);
        Assert.assertEquals(400.0f, burger.getPrice(), 0);
    }

    @Test
    public void getReceiptTest() {
        burger.setBuns(bun);
        Mockito.when(bun.getName()).thenReturn("black_bun");
        Mockito.when(bun.getPrice()).thenReturn(100.0f);
        burger.addIngredient(ingredient);
        Mockito.when(ingredient.getName()).thenReturn("hot sauce");
        Mockito.when(ingredient.getPrice()).thenReturn(100.0f);
        Mockito.when(ingredient.getType()).thenReturn(SAUCE);
        StringBuilder receiptTest = new StringBuilder(String.format("(==== black_bun ====)%n"));
        receiptTest.append(String.format("= sauce hot sauce =%n"));
        receiptTest.append(String.format("(==== black_bun ====)%n"));
        receiptTest.append(String.format("%nPrice: 300,000000%n"));
        Assert.assertEquals(burger.getReceipt(), receiptTest.toString());


    }
}
