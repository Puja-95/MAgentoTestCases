package stepdefinations;

import PageObjects.SignUpPage;
import io.cucumber.java.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;

public class SignUpStepDefinitions {

    WebDriver driver = new ChromeDriver();

    public SignUpPage signUpPage;
    public SignUpStepDefinitions() {

        signUpPage=new SignUpPage(driver);// Initializing the page object
    }
    @Given("the user is on the Signup page")
    public void the_user_is_on_the_signup_page() {
        // Navigate to the signup page
        signUpPage.navigateToSignUp();
    }

    @When("the user enters their details")
    public void the_user_enters_their_details(io.cucumber.datatable.DataTable dataTable) {
        // Get the details from the DataTable and enter them into the form
        for (java.util.Map<String, String> row : dataTable.asMaps(String.class, String.class)) {
            String username = row.get("Username");
            String email = row.get("Email");
            String password = row.get("Password");

            // Find input fields and enter data
            WebElement signUp = driver.findElement(By.xpath("//div[@class='panel header']/ul/li[2]/a[1]"));
           /* WebElement emailField = driver.findElement(By.name("email"));
            WebElement passwordField = driver.findElement(By.name("password"));*/

            signUp.click();
            /*emailField.sendKeys(email);
            passwordField.sendKeys(password);*/
        }
    }

    @When("the user submits the form")
    public void the_user_submits_the_form() {
        // Find the submit button and click it
        WebElement submitButton = driver.findElement(By.name("submit"));
        submitButton.click();
    }

    @Then("a confirmation message should be displayed")
    public void a_confirmation_message_should_be_displayed() {
        // Wait for the confirmation message and verify it
        WebElement confirmationMessage = driver.findElement(By.id("confirmationMessage"));
        Assert.assertTrue("Confirmation message is not displayed", confirmationMessage.isDisplayed());

        // Optional: You can add more assertion to check the text of the confirmation message
        String expectedMessage = "Signup successful!";
        String actualMessage = confirmationMessage.getText();
        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @After
    public void tearDown() {
        // Close the browser after each scenario
        if (driver != null) {
            driver.quit();
        }
    }
}
