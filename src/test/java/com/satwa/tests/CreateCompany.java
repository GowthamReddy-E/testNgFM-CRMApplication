package com.satwa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.satwa.pages.CompaniesPage;
import com.satwa.pages.Configuration;
import com.satwa.pages.HomePage;
import com.satwa.pages.LoginPage;
import com.satwa.pages.NewCompanyPage;
import com.satwa.utils.GlobalVariables;
import com.satwa.utils.ReporterUtil;
import com.satwa.utils.WaitUtils;

public class CreateCompany extends Configuration {

	@Test
	public void CreateCompany01_createNewCompanyWithPublicStatus() {		

		if (LoginPage.get().isLoginPageDisplayed()) {
			ReporterUtil.get().writeLog(Status.PASS	,"Login page is displayed on launching application");
		} else {
			ReporterUtil.get().writeLog(Status.FAIL	,"Login page is NOT displayed on launching application","Launch Application");
			Assert.fail("Login page is not displayed on launching the application");
		}

		LoginPage.get().login(GlobalVariables.envData.get("username"), GlobalVariables.envData.get("password"));
		WaitUtils.constantWait(4000);
		if (HomePage.get().isHomePageDisplayed()) {
			ReporterUtil.get().writeLog(Status.PASS	,"Home page is displayed after login.", "AfterLogin");
		} else {
			ReporterUtil.get().writeLog(Status.FAIL	,"Login page is NOT displayed on launching application", "AfterLogin");
			Assert.fail("Home page is NOT displayed after login.");
		}
		WaitUtils.constantWait(4000);
		if (CompaniesPage.get().navigateToCompaniesPage()) {
			ReporterUtil.get().writeLog(Status.PASS	,"Application is navigated to Companies Page.", "CompaniesPage");
		} else {
			ReporterUtil.get().writeLog(Status.FAIL	,"Application is NOT navigated to Companies Page.", "CompaniesPage");
			Assert.fail("Companies page is NOT displayed after clicking on Create Button in companuies menu.");
		}
		WaitUtils.constantWait(4000);
		if (NewCompanyPage.get().navigateToCreateCompanyPage()) {
			ReporterUtil.get().writeLog(Status.PASS	,"Application is navigated to Create new Company  Page.", "CreateNewCompanyPage");
		} else {
			ReporterUtil.get().writeLog(Status.FAIL	,"Application is NOT navigated to Create new Company  Page.", "CreateNewCompanyPage");
			Assert.fail("Create new Company page is NOT displayed after clicking on Create Button in companuies page.");
		}
		WaitUtils.constantWait(4000);
//		NewCompanyPage.get().enterCompanyData(envData);
		NewCompanyPage.get().enterCompanyData(GlobalVariables.get().tcData);


	}



}
