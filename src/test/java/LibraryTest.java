
import com.experitest.client.*;
import com.experitest.client.log.ILogger;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class LibraryTest {

    protected Client client = null;
    protected GridClient gridClient = null;

    @BeforeMethod
    public void setUp() {
   		// In case your user is assigned to a single project leave projectName as empty string, otherwise please specify the project name
        gridClient = new GridClient("eyal.kopelevich", "Experitest2012", "Default", "https://sales.experitest.com:443");
        client = gridClient.lockDeviceForExecution("Jenkins Android", "@os='android'", 120, TimeUnit.MINUTES.toMillis(2));
        client.setReporter("xml", "reports" , "EriBank");
    }

    @Test
    public void testUntitled() {
    	client.install("cloud:com.experitest.ExperiBank/.LoginActivity", true, false);
		client.deviceAction("Unlock");
        client.launch("cloud:com.experitest.ExperiBank/.LoginActivity", true, false);
        client.elementSendText("NATIVE", "id=usernameTextField", 0, "company");
        client.elementSendText("NATIVE", "id=passwordTextField", 0, "company");
        client.click("N/ATIVE", "id=loginButton", 0, 1);
        
        client.sleep(6000);
        client.click("NATIVE", "id=makePaymentButton", 0, 1);
        client.elementSendText("NATIVE", "id=phoneTextField", 0, "5552424");
        client.elementSendText("NATIVE", "id=nameTextField", 0, "Eyal");
        client.elementSendText("NATIVE", "id=amountTextField", 0, "30");
        client.elementSendText("NATIVE", "id=countryTextField", 0, "Russia");
        client.click("NATIVE", "id=sendPaymentButton", 0, 1);
        client.click("NATIVE", "id=button1", 0, 1);
    }

    @AfterMethod
    public void tearDown() {
        client.generateReport(false);
        client.releaseClient();
    }
}