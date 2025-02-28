package AndrewWebServices;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class AndrewWebServicesTest {
    //Database database;
    InMemoryDatabase fakeDatabase;
    //RecSys recommender;
    RecSys recSysStub; // We need a stub here
    //PromoService promoService;
    PromoService mockPromoService; // We need a mock here
    AndrewWebServices andrewWebService;

    @Before
    public void setUp() {
        // You need to use some mock objects here
        //database = new Database(); // We probably don't want to access our real database...
        // Instead, we can use a fake database
        fakeDatabase = new InMemoryDatabase();
        fakeDatabase.addUser("Scotty", 17214);

        //recommender = new RecSys();

        // We need a stub for RecSys
        recSysStub = mock(RecSys.class);
        when(recSysStub.getRecommendation(anyString()))
            .thenReturn("Animal House"); 

        //promoService = new PromoService();

        // We need a mock for PromoService
        mockPromoService = mock(PromoService.class);

        //andrewWebService = new AndrewWebServices(database, recommender, promoService);
        andrewWebService = new AndrewWebServices(fakeDatabase, recSysStub, mockPromoService);
    }

    @Test
    public void testLogIn() {
        // This is taking way too long to test
        assertTrue(andrewWebService.logIn("Scotty", 17214));
        assertFalse("Expected login to fail w/ incorrect password",
                    andrewWebService.logIn("Scotty", 99999));
        assertFalse("Expected login to fail for unknown user",
                    andrewWebService.logIn("NonUser", 12345));
    }

    @Test
    public void testGetRecommendation() {
        // This is taking way too long to test
        assertEquals("Animal House", andrewWebService.getRecommendation("Scotty"));
    }

    @Test
    public void testSendEmail() {
        // How should we test sendEmail() when it doesn't have a return value?
        // Hint: is there something from Mockito that seems useful here?

        // We'll call the method
        String testEmail = "bob@example.com";
        andrewWebService.sendPromoEmail(testEmail);

        // Verify that mockPromoService.mailTo(...) was called exactly once
        verify(mockPromoService).mailTo(testEmail);
    }

    @Test
    public void testNoSendEmail() {
        // How should we test that no email has been sent in certain situations (like right after logging in)?
        // Hint: is there something from Mockito that seems useful here?
        // 1. Perform some action that should NOT trigger an email
        andrewWebService.logIn("Scotty", 17214);
        // 2. Verify that mailTo(...) was never called
        verify(mockPromoService, never()).mailTo(anyString());
    }
}
