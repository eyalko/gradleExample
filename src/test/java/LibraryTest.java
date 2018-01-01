// Insert your package here
import com.experitest.client.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class LibraryTest {

    protected Client client = null;
    protected GridClient gridClient = null;

    @BeforeMethod
    public void setUp() {
   		// In case your user is assigned to a single project leave projectName as empty string, otherwise please specify the project name
        gridClient = new GridClient("eyal.kopelevich", "Experitest2012", "Default", "https://sales.experitest.com:443");
        client = gridClient.lockDeviceForExecution("Untitled", "@os='ios'", 120, TimeUnit.MINUTES.toMillis(2));
        client.setReporter("xml", "reports" , "Untitled");
    }

    @Test
    public void testUntitled() {
    	client.install("cloud:com.experitest.ExperiBank", true, false);
    	client.launch("cloud:com.experitest.ExperiBank", true, true);
        client.elementSendText("NATIVE", "accessibilityLabel=Username", 0, "company");
        client.elementSendText("NATIVE", "accessibilityLabel=Password", 0, "company");
        client.click("NATIVE", "accessibilityLabel=Login", 0, 1);
        client.sleep(6000);
        client.click("NATIVE", "accessibilityLabel=Make Payment", 0, 1);
        client.elementSendText("NATIVE", "accessibilityLabel=Phone", 0, "5552424");
        client.elementSendText("NATIVE", "accessibilityLabel=Name", 0, "Eyal");
        client.elementSendText("NATIVE", "accessibilityLabel=Amount", 0, "25");
        client.click("NATIVE", "accessibilityLabel=Select", 0, 1);
        if(client.swipeWhileNotFound("Down", 100, 2000, "NATIVE", "xpath=//*[@accessibilityLabel='United Kingdom']", 0, 1000, 5, true)){
            client.click("NATIVE", "xpath=//*[@accessibilityLabel='United Kingdom']", 0, 1);
        }
        client.click("NATIVE", "accessibilityLabel=Send Payment", 0, 1);
        client.click("NATIVE", "accessibilityLabel=Yes", 0, 1);
    }

    @AfterMethod
    public void tearDown() {
        client.generateReport(false);
        client.releaseClient();
    }
}