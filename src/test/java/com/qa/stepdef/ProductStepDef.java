package com.qa.stepdef;

import com.qa.pages.LoginPageTest;
import com.qa.pages.ProductDetailsPageTest;
import com.qa.pages.ProductsPageTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import com.microsoft.appcenter.appium.Factory;
import com.microsoft.appcenter.appium.EnhancedAndroidDriver;
import org.junit.rules.TestWatcher;
import org.junit.Rule;

public class ProductStepDef {

    @Rule
    public TestWatcher watcher = Factory.createWatcher();


    @Given("^I'm logged in$")
    public void iMLoggedIn() throws InterruptedException {
        new LoginPageTest().login("standard_user", "secret_sauce");
    }

    @Then("^the product is listed with title \"([^\"]*)\" and price \"([^\"]*)\"$")
    public void theProductIsListedWithTitleAndPrice(String title, String price) throws Exception {
        Boolean titleCheck = new ProductsPageTest().getProductTitle(title).equalsIgnoreCase(title);
        Boolean priceCheck = new ProductsPageTest().getProductPrice(title, price).equalsIgnoreCase(price);
        Assert.assertTrue("titleCheck = " + titleCheck + ", priceCheck = " + priceCheck,
                titleCheck & priceCheck);
    }

    @When("^I click product title \"([^\"]*)\"$")
    public void iClickProductTitle(String title) throws Exception {
        new ProductsPageTest().pressProductTitle(title);
    }

    @Then("^I should be on product details page with title \"([^\"]*)\", price \"([^\"]*)\" and description \"([^\"]*)\"$")
    public void iShouldBeOnProductDetailsPageWithTitlePriceAndDescription(String title, String price, String description) throws Exception {
        ProductDetailsPageTest productDetailsPageTest = new ProductDetailsPageTest();
        boolean titleCheck = productDetailsPageTest.getTitle().equalsIgnoreCase(title);
        boolean descCheck = productDetailsPageTest.getDesc().equalsIgnoreCase(description);
        boolean priceCheck = productDetailsPageTest.getPrice().equalsIgnoreCase(price);
        Assert.assertTrue("titleCheck = " + titleCheck + ", descCheck = " + descCheck + ", priceCheck = " + priceCheck,
                titleCheck & descCheck & priceCheck);
    }
}
