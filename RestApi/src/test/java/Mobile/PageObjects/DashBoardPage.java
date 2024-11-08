package Mobile.PageObjects;
import io.appium.java_client.android.AndroidDriver;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class DashBoardPage extends BasePage {

		public DashBoardPage(AndroidDriver _driver) {
			super(_driver);

			PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		}

		@AndroidFindBy(accessibility = "Settings")
		private WebElement settingsKebabMenu;

		@AndroidFindBy(id = "com.monefy.app.lite:id/expense_amount_text")
		private WebElement expenseAmountTextInChart;

		@AndroidFindBy(id = "income_amount_text")
		private WebElement incomeAmountTextInChart;

		@AndroidFindBy(id = "balance_amount")
		private WebElement balanceAmount;

		@AndroidFindBy(id = "com.monefy.app.lite:id/expense_button")
		private WebElement expenseButton;

		@AndroidFindBy(id = "income_button")
		private WebElement incomeButton;

		@AndroidFindBy(id = "transaction_type_button_layout")
		private WebElement snackbarMessageCancelButton;

		@AndroidFindBy(id = "categories_button")
		private WebElement rightSideBarCategoriesButton;

		@AndroidFindBy(id = "leftLinesImageView")
		private WebElement expandTransactions;

		@AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id='com.monefy.app.lite:id/piegraph']//android.widget.ImageView[2]")
		private WebElement carExpenseIcon;

		@AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id='com.monefy.app.lite:id/piegraph']//android.widget.TextView[@index='5']")
		private WebElement carExpensePercentage;

		@AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id='com.monefy.app.lite:id/piegraph']//android.widget.ImageView[5]")
		private WebElement houseExpenseIcon;

		@AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id='com.monefy.app.lite:id/piegraph']//android.widget.TextView[@index='5']")
		private WebElement houseExpensePercentage;

		@AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.TextView")
		private WebElement popUps;

		private WebElement getCategoryFromExpandedList(String category) {
		List<WebElement> categories = this.driver.findElements(By.xpath("//android.widget.TextView"));
		for (WebElement element : categories) {
			if (element.getText().equals(category)) {
				return element;
			}
		}
		throw new NoSuchElementException("Category not found: " + category);
	}

		private WebElement getExpense(String note) {
			return (WebElement) this.driver.findElement(By.xpath("//*[@resource-id='com.monefy.app.lite:id/textViewTransactionNote'][@text='" + note + "']"));
		}

		public AddExpensePage addExpense() {
			waitUntilVisible(snackbarMessageCancelButton);
			this.expenseButton.click();
			return new AddExpensePage((AndroidDriver) driver);
		}

		public AddExpensePage addIncome() {
			this.waitUntilClickable(this.incomeButton);
			this.incomeButton.click();
			return new AddExpensePage((AndroidDriver) driver);
		}

		public void tacklePopIssue() {
			for (int i = 0; i < 4; i++) {
				this.settingsKebabMenu.click();
				sleep(1000);
				this.settingsKebabMenu.click();
			}
			try {
				if (this.rightSideBarCategoriesButton.isDisplayed())
					this.settingsKebabMenu.click();
			} catch (Exception ignore) {

			}
		}

		public String getBalanceAmount() {
			return this.balanceAmount.getText().split(" ")[1].replace("$", "");
		}

		public String getTotalIncome() {
			return this.incomeAmountTextInChart.getText();
		}

		public String getTotalExpense() {
			return this.expenseAmountTextInChart.getText();
		}

		public void toggleListOfExpenses() {
			waitUntilClickable(this.expandTransactions);
			this.expandTransactions.click();
		}

		public void expandSpecificCategoryExpenses(String category) {
			this.getCategoryFromExpandedList(category).click();
		}

		public AddExpensePage modifyExpense(String note) {
			this.getExpense(note).click();
			return new AddExpensePage((AndroidDriver) driver);
		}
	}

