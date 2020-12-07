package com.example.umorili;

import com.example.umorili.api.App;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import retrofit2.Response;

public class ApiTest {


    @Test
    public void testBush() throws IOException{

        Response response = App.getRequestApi().getData("bash", 50).execute();

        Assert.assertTrue(response.isSuccessful());

        Assert.assertNotNull(response.body());



    }
}
