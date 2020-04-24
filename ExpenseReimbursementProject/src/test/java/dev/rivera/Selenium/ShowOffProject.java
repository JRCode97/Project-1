package dev.rivera.Selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class ShowOffProject {
	public static void main(String[] args) throws InterruptedException {
		WebDriver window = new FirefoxDriver();
		window.get("http://localhost:8080/ExpenseReimbursementProject/");
		TimeUnit.SECONDS.sleep(2);
		window.findElement(By.id("userNameIn")).sendKeys("MrManager");
		TimeUnit.SECONDS.sleep(2);
		window.findElement(By.id("passWordIn")).sendKeys("mpword");
		TimeUnit.SECONDS.sleep(2);
		window.findElement(By.id("signInBtn")).click();
		TimeUnit.SECONDS.sleep(30);
		window.findElement(By.id("statisticsBtn")).click();

		
	}
}
