package com.hitesh.builditbigger;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.test.AndroidTestCase;
import android.test.InstrumentationTestCase;
import android.test.suitebuilder.annotation.MediumTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;

/**
 * Created by 880443 on 6/24/2016.
 */

public class EmptyTest extends InstrumentationTestCase {



    @Test
    public void CheckNull() throws Exception {
        String result = null;

        JokeFetchTask endpointsAsyncTask = new JokeFetchTask(getInstrumentation().getContext(), null);
        endpointsAsyncTask.execute();
        try {
            result = endpointsAsyncTask.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(result);
    }
}
