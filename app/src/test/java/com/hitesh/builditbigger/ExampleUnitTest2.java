package com.hitesh.builditbigger;

import android.app.Application;
import android.test.AndroidTestCase;
import android.test.ApplicationTestCase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest2 extends ApplicationTestCase<Application> {

    private CountDownLatch mSignal =null;
    String result="dsfdsf";
    /*@Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 +2);
    }*/

    public ExampleUnitTest2() {
        super(Application.class);
    }


    @Override
    protected void setUp() throws Exception {
        mSignal = new CountDownLatch(1);
    }

    @Override
    protected void tearDown() throws Exception {
        mSignal.countDown();
    }
    public void testJoke()  {
        try {
            JokeFetchTask endpointsAsyncTask = new JokeFetchTask(null,new TestJokeListener());
            endpointsAsyncTask.execute();
            boolean success = mSignal.await(5, TimeUnit.SECONDS);
            if (!success) {
                fail("Test timed out, not able to reach Server");
            }

        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

    }

    private class TestJokeListener implements JokeFetchTask.OnJokeRetrievedListener {

        @Override
        public void onJokeRetrieved(String joke) throws InterruptedException {
            assertTrue(joke != null && joke.length() > 0);
            mSignal.await();
        }
    }


}