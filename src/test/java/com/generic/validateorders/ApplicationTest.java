package com.generic.validateorders;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.generic.exceptions.WaitException;
import com.generic.pages.ConfirmationPage;
import com.generic.pages.HomePage;
import com.generic.testbase.TestBase;
import com.generic.utilities.Reporter;
import com.generic.utilities.Utilities;

public class ApplicationTest extends TestBase {

	private HomePage homePage;
	private ConfirmationPage confirmationPage;

	@BeforeClass
	public void beforeClass(ITestContext context) throws WaitException {
		homePage = new HomePage(getWebDriverInstance(context));
	}

	@Test(dataProvider = "ReadExcel")
	public void enterAndValidateUniversityData(String lastName,
			String firstName, String address1, String address2, String city,
			String state, String pincode, String underGradProgOfInterest,
			String underGradCertOfInterest, String gradProgOfInterest,
			String gradCertOfInterest, String areaCode,
			String firstThreeDigits, String lastFourDigits, String emailId,
			String verifyEmail) throws Exception {
		try {
			confirmationPage = homePage.enterFormDetails(lastName, firstName,
					address1, address2, city, state, pincode,
					underGradProgOfInterest, underGradCertOfInterest,
					gradProgOfInterest, gradCertOfInterest, areaCode,
					firstThreeDigits, lastFourDigits, emailId, verifyEmail);
			Assert.assertFalse(confirmationPage
					.validateConfirmationMessage("test class"));
			Reporter.sendStatusToReport("UniversityForm", "134",
					Utilities.getCurrentThreadId()
							+ "Validate confirmation message", "Pass", "NA");
		} catch (Exception excetion) {
			logErrorMessage(excetion);
			Reporter.sendStatusToReport("UniversityForm", "134",
					Utilities.getCurrentThreadId()
							+ "Validate confirmation message", "Fail",
					excetion.getLocalizedMessage());
			throw excetion;
		}
	}
}
