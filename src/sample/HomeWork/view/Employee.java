package sample.HomeWork.view;

import javafx.beans.property.*;

import java.util.Date;

public class Employee {

    private StringProperty nome, prenom, adr, sexe;
    private SimpleObjectProperty<Date> birth;
    private IntegerProperty tel;

    public Employee(){
        this.nome = new SimpleStringProperty();
        this.sexe = new SimpleStringProperty();
        this.prenom = new SimpleStringProperty();
        this.adr = new SimpleStringProperty();
        this.tel = new SimpleIntegerProperty();
        this.birth = new SimpleObjectProperty<>();
    }

    public StringProperty getNomeProperty(){
        return nome;
    }

    public void setNome(String nome){
        this.nome.set(nome);
    }

    public String getNome(){
        return nome.get();
    }

    public StringProperty getSexeProperty() {
        return sexe;
    }

    public void setSexe(String sexe){
        this.sexe.set(sexe);
    }

    public String getSexe(){
        return sexe.get();
    }

    public void setPrenom(String prenom){
        this.prenom.set(prenom);
    }
    public String getPrenom(){
        return prenom.get();
    }

    public void setAdr(String adr){
        this.adr.set(adr);
    }

    public String getAdr(){
        return adr.get();
    }

    public void setDate(Date birth){
        this.birth.set(birth);
    }
    public Date getdate(){
        return birth.get();
    }

    public void setTel(Integer tel){
        this.tel.set(tel);
    }

    public Integer geTel(){
        return tel.get();
    }
}
