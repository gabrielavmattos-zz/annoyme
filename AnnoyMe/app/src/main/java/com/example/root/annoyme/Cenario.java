package com.example.root.annoyme;

import java.util.ArrayList;

/**
 * Created by Gabriela.
 */
public class Cenario {


    private ArrayList<String> geral;
    private ArrayList<String> naoPreocupa;
    private  ArrayList<String> normal;
    private  ArrayList<String> mePreocupo;
    private Dados dados;
    private String cenario;
    private int id;


    public Cenario()
    {
        naoPreocupa = new ArrayList<String>();
        normal = new ArrayList<String>();
        mePreocupo = new ArrayList<String>();

        Dados dados = new Dados();

        geral = dados.lerCenarios();
        String line;
        for (int i = 1; i<geral.size(); i++)
        {
            line = geral.get(i);
            System.out.println(line);
            id = Integer.parseInt(line.substring(0,1));
            System.out.println(id);
            cenario = line.substring(line.indexOf(',')+1);
            System.out.println(cenario);


            if(id == 2)
                setMePreocupo(cenario);
            else if (id == 1)
                setNormal(cenario);
            else if(id == 0)
                setNaoPreocupa(cenario);
            else
                System.out.println("erro");

        }

    }
    public void setNaoPreocupa(String cenario)
    {
        naoPreocupa.add(cenario);
    }
    public void setNormal(String cenario)
    {
        normal.add(cenario);
    }
    public void setMePreocupo(String cenario)
    {
        mePreocupo.add(cenario);
    }

    public String getCenario(int i, int pos) {


        if(id == 2)
            return mePreocupo.get(pos);
        else if (id == 1)
            return normal.get(pos);
        else if(id == 0)
            return naoPreocupa.get(pos);
        else
            return null;
    }
}
