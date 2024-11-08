package Mobile.PageObjects;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AddExpensePage extends BasePage {
	enum KeyboardLayout {MINUS, PLUS, MULTIPLY, DIVIDE, EQUALS, DOT}

	public AddExpensePage(AndroidDriver _driver) {
		super(_driver);
		PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
	}

	@AndroidFindBy(id = "amount_text")
	private WebElement amountText;

	@AndroidFindBy(id = "textViewNote")
	private WebElement textViewNote;

	@AndroidFindBy(id = "keyboard_action_button")
	private WebElement chooseCategoryButton;

	@AndroidFindBy(id = "account_spinner")
	private WebElement paymentMode;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Cash']")
	private WebElement paymentModeCash;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Payment card']")
	private WebElement paymentModeCard;

	@AndroidFindBy(id = "buttonKeyboardClear")
	private WebElement amountClearButton;

	private WebElement calculatorNumberButton(char number) {
		String id = "buttonKeyboard" + number;
		return (WebElement) this.driver.findElement(By.id(id));
	}

	private WebElement calculatorActionButton(String action) {
		String id = "buttonKeyboard" + action;
		return (WebElement) this.driver.findElement(AppiumBy.accessibilityId(id));
	}

	private WebElement getCategoryButton(String category) {
		return (WebElement) this.driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"" +
			category +
			"\")"));
	}

	public DashBoardPage fillDetails(String category, String paymentMode, int amount, String note) {
		this.setPaymentMode(paymentMode);
		for (char number : Integer.toString(amount).toCharArray()) {
			this.calculatorNumberButton(number).click();
		}
		this.textViewNote.sendKeys(note);
		this.chooseCategoryButton.click(); //new UiSelector().text("CHOOSE CATEGORY")
		this.getCategoryButton(category).click();
		return new DashBoardPage((AndroidDriver) driver);
	}

	public void clearAmount() {
		int charLength = this.amountText.getText().toCharArray().length;
		for (int i = 0; i <= charLength; i++) {
			this.amountClearButton.click();
		}
	}

	private void setPaymentMode(String mode) {
		this.paymentMode.click();
		if (mode.equalsIgnoreCase("card"))
			this.paymentModeCard.click();
		else
			this.paymentModeCash.click();
	}
}