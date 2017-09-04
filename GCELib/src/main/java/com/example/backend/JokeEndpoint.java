package com.example.backend;

import com.example.JokeSupplier;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.example.com",
                ownerName = "backend.example.com",
                packagePath = ""
        )
)
public class JokeEndpoint {

    @ApiMethod(name = "getJoke")
    public JokeBean getJoke() {

        //Simulate remote backend
        try {
            Thread.sleep(1500); //1.5 seconds
        } catch (Exception e) {
            e.printStackTrace();
        }

        JokeBean response = new JokeBean();
        response.setData(new JokeSupplier().getAJoke());
        return response;
    }

}
