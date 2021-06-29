package com.example.lightsout.models;

import com.example.lightsout.R;
import com.example.lightsout.enums.LightState;

public class Light {

    private LightState lightState;
    private int whichLight;

    public Light(boolean isItOn)
    {
        if(isItOn)
        {
            lightState = LightState.On;
            whichLight = (R.raw.lighton);
        }

        else
        {
            lightState = LightState.Off;
            whichLight = (R.raw.lightoff);
        }
    }

    public int getImageSource()
    {
        return whichLight;
    }

    public boolean IsItOn()
    {
        if(lightState == LightState.Off)
            return false;
        return true;
    }

    public void Switch()
    {
        if(lightState == LightState.On)
        {
            lightState = LightState.Off;
            whichLight = (R.raw.lightoff);
        }
        else
        {
            lightState = LightState.On;
            whichLight = (R.raw.lighton);
        }
    }
}