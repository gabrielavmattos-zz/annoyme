package com.example.root.annoyme;

import java.util.ArrayList;

/**
 * Created by Gabriela.
 */
public class Cenario {


    private ArrayList<String> naoPreocupa;
    private  ArrayList<String> normal;
    private  ArrayList<String> mePreocupo;

    public Cenario()
    {
        naoPreocupa = new ArrayList<String>();
        normal = new ArrayList<String>();
        mePreocupo = new ArrayList<String>();
    }
    public void setNaoPreocupa(String cenario)
    {
        naoPreocupa.add(cenario);
    }
    public void setNormal(String cenario)
    {
        naoPreocupa.add(cenario);
    }
    public void setMePreocupo(String cenario)
    {
        naoPreocupa.add(cenario);
    }

}
