package com.qa.stepdef;

import com.qa.pages.LoginPageTest;
import com.qa.pages.ProductsPageTest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import com.microsoft.appcenter.appium.Factory;
import com.microsoft.appcenter.appium.EnhancedAndroidDriver;
import org.junit.rules.TestWatcher;
import org.junit.Rule;

public class LoginStepDef {

    @Rule
    public TestWatcher watcher = Factory.createWatcher();


    @When("^I enter username as \"([^\"]*)\"$")
    public void iEnterUsernameAs(String username) throws InterruptedException {
        new LoginPageTest().enterUserName(username);
    }

    @When("^I enter password as \"([^\"]*)\"$")
    public void iEnterPasswordAs(String password) {
        new LoginPageTest().enterPassword(password);
    }

    @When("^I login$")
    public void iLogin() {
        new LoginPageTest().pressLoginBtn();
    }

    @Then("^login should fail with an error \"([^\"]*)\"$")
    public void loginShouldFailWithAnError(String err) {
        Assert.assertEquals(new LoginPageTest().getErrTxt(), err);
    }

    @Then("^I should see Products page with title \"([^\"]*)\"$")
    public void iShouldSeeProductsPageWithTitle(String title) {
        Assert.assertEquals(new ProductsPageTest().getTitle(), title);
    }
}
