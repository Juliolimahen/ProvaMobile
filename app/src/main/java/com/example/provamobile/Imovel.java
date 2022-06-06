package com.example.provamobile;

public class Imovel {

    private int idImovel;
    private String apelido;
    private String local;
    private String area;
    private String aluguel;
    private String comprar;
    private String queroAlugar;
    private String queroComprar;

    public Imovel(){}

    public Imovel(String apelido, String local, String area, String aluguel, String comprar, String queroAlugar, String queroComprar) {
        this.apelido = apelido;
        this.local = local;
        this.area = area;
        this.aluguel = aluguel;
        this.comprar = comprar;
        this.queroAlugar = queroAlugar;
        this.queroComprar = queroComprar;
    }

    public int getIdImovel() {
        return idImovel;
    }

    public void setIdImovel(int idImovel) {
        this.idImovel = idImovel;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAluguel() {
        return aluguel;
    }

    public void setAluguel(String aluguel) {
        this.aluguel = aluguel;
    }

    public String getComprar() {
        return comprar;
    }

    public void setComprar(String comprar) {
        this.comprar = comprar;
    }

    public String getQueroAlugar() {
        return queroAlugar;
    }

    public void setQueroAlugar(String queroAlugar) {
        this.queroAlugar = queroAlugar;
    }

    public String getQueroComprar() {
        return queroComprar;
    }

    public void setQueroComprar(String queroComprar) {
        this.queroComprar = queroComprar;
    }

    @Override
    public String toString(){
        return "Apelido: "+apelido+"\nLocal: "+ local+" - Area: "+ area+"M²"+"\nAluguel R$:"+ aluguel+" - Comprar R$:" + comprar +
                "\nQuero Alugar: "+ queroAlugar+" - Quero Comprar: "+ queroComprar;
    }

}
