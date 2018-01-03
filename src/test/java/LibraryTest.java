
import com.experitest.client.*;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class LibraryTest {

    protected Client client = null;
    protected GridClient gridClient = null;
    public String accessKey, url;
    
    
    public void getEnvFromJenikns() {
    	accessKey = System.getenv("accessKey");
    	url = System.getenv("url");
    }
    
    @BeforeMethod
    public void setUp() {
    	getEnvFromJenikns();
    	gridClient = new GridClient("eyJ4cC51Ijo4OCwieHAucCI6MiwieHAubSI6Ik1BIiwiYWxnIjoiSFMyNTYifQ.eyJleHAiOjE4MzAzNDUxMjUsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.0fpTTFM2cwhqoHKe__J47aTqp9qU6KgBI6-3ZT_WAcg", "https://sales.experitest.com");
        client = gridClient.lockDeviceForExecution("Jenkins iOS", "@os='ios' and @version!='11.1.2'", 120, TimeUnit.MINUTES.toMillis(2));
        client.setReporter("xml", "reports" , "EriBank");
    }

    @Test
    public void testUntitled() {
    	client.install("cloud:com.experitest.ExperiBank", true, false);
		client.deviceAction("Unlock");
		client.launch("cloud:com.experitest.ExperiBank", true, true);
		client.elementSendText("NATIVE", "accessibilityLabel=Username", 0, "company");
		client.elementSendText("NATIVE", "accessibilityLabel=Password", 0, "company");
		client.click("NATIVE", "accessibilityLabel=Login", 0, 1);
		client.sleep(6000);
		client.click("NATIVE", "accessibilityLabel=Make Payment", 0, 1);
		client.elementSendText("NATIVE", "accessibilityLabel=Phone", 0, "5552424");
		client.elementSendText("NATIVE", "accessibilityLabel=Name", 0, "Eyal");
		client.elementSendText("NATIVE", "accessibilityLabel=Amount", 0, "25");
		client.elementSendText("NATIVE", "xpath=//*[@accessibilityLabel='Country']", 0, "United Kingdom");
//		client.click("NATIVE", "accessibilityLabel=Select", 0, 1);
//		if(client.swipeWhileNotFound("Down", 100, 2000, "NATIVE", "xpath=//*[@accessibilityLabel='United Kingdom']", 0, 1000, 5, true)){
//			client.click("NATIVE", "xpath=//*[@accessibilityLabel='United Kingdom']", 0, 1);
//		}
		client.click("NATIVE", "accessibilityLabel=Send Payment", 0, 1);
		client.click("NATIVE", "accessibilityLabel=Yes", 0, 1);
    }

    @AfterMethod
    public void tearDown() {
        client.generateReport(false);
        client.releaseClient();
    }
}