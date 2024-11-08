package Mobile.Test;


import Mobile.PageObjects.AddExpensePage;
import Mobile.PageObjects.DashBoardPage;
import Mobile.Utils.DriverManager;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class AddExpenseTest {
	static AndroidDriver driver;
	private static DashBoardPage dashboard;


	@BeforeTest
	public void setup() {
		DriverManager.setUp();
		driver = (AndroidDriver) DriverManager.getDriver();
		dashboard = new DashBoardPage(driver);
	}

	@AfterTest
	public static void tearDown() {
		DriverManager.quitDriver();
	}

	@Test
	public void shouldBeAbleToAddExpenses() {
		dashboard.addExpense()
			.fillDetails("Car", "Cash", 15000, "CarEMI")
			.addExpense()
			.fillDetails("House", "Cash", 20000, "House Rent")
			.addExpense()
			.fillDetails("Bills", "Card", 1000, "Broadband bill");
		     assertEquals(dashboard.getTotalExpense(), "$36,000.00");
	}

	@Test
	public void shouldBeAbleToEditExpenses() {
		dashboard.addExpense().fillDetails("Bills", "Card", 800, "Electricity bill");
		dashboard.toggleListOfExpenses();
		dashboard.expandSpecificCategoryExpenses("Bills");
		AddExpensePage addExpensePage = dashboard.modifyExpense("Electricity bill");
		addExpensePage.clearAmount();
		addExpensePage.fillDetails("Bills", "Card", 1200, "Updated Electricity bill");
		dashboard.toggleListOfExpenses();
		assertEquals(dashboard.getTotalExpense(), "$1,200.00");
	}

	@Test
	public void shouldBeAbleToAddIncome() {
		dashboard.addIncome()
			.fillDetails("Salary", "Cash", 120000, "Salary Nov-24");
		assertEquals(dashboard.getTotalIncome(), "$120,000.00");
	}
}