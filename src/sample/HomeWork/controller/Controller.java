package sample.HomeWork.controller;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import com.jfoenix.transitions.hamburger.HamburgerNextArrowBasicTransition;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import sample.HomeWork.view.Employee;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private JFXTextField np, tel, adr;
    @FXML
    private JFXHamburger ham;
    @FXML
    private DatePicker birth;
    @FXML
    private JFXRadioButton mRb, fRb;
    @FXML
    private TableView<Employee> tabEmp;
    @FXML
    private TableColumn<Employee, String> c1;
    @FXML
    private ImageView img1,img2,img3,img4,img5;
    @FXML
    JFXDrawer drawer;
    @FXML
    private TableColumn<Employee, String> c2;
    private ObservableList<Employee> emplList = FXCollections.observableArrayList();
    private ToggleGroup tg = new ToggleGroup();
    private Boolean nomeBool=false, ddnBool=false, adrBool=false, telBool=false, genderBool=false;
    private Date s4;
    private String s1=" ",s2=" ", s3=" ";
    int state = 0;
    private HamburgerBackArrowBasicTransition backHam;
    private HamburgerBasicCloseTransition closeHam;
    private HamburgerNextArrowBasicTransition nextHam;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            VBox box = FXMLLoader.load(getClass().getResource("/sample/HomeWork/view/drawerPane.fxml"));
            drawer.setSidePane(box);
        } catch (IOException e) {
            e.printStackTrace();
        }


        state = 0;//initialize the ham state to default
        mRb.setToggleGroup(tg);
        fRb.setToggleGroup(tg);
        Employee emp = new Employee();
        emp.setNome("Emp1")  ;
        emp.setSexe("Feminin");

        Employee emp2 = new Employee();
        emp2.setNome("Emp2");
        emp2.setSexe("Masculin");
        System.out.println(emp.getNome()+" "+emp.getSexe());
        System.out.println(emp2.getNome()+" "+emp2.getSexe());
        emplList.addAll(emp, emp2);

        c1.setCellValueFactory(cellData -> cellData.getValue().getNomeProperty());
        c2.setCellValueFactory(cellData -> cellData.getValue().getSexeProperty());
        tabEmp.setItems(emplList);

        backHam = new HamburgerBackArrowBasicTransition(ham);
        backHam.setRate(-1);
        nextHam = new HamburgerNextArrowBasicTransition(ham);
        nextHam.setRate(-1);
        closeHam = new HamburgerBasicCloseTransition(ham);
        closeHam.setRate(-1);


    }

    @FXML
    private void hamReaction(MouseEvent event){
        switch (state){
            case 0 :{
                if (drawer.isHidden()){
                    drawer.open();
                    drawer.toFront();
                }

                else {
                    drawer.close();
                    drawer.toBack();
                }

                nextHam.setRate(nextHam.getRate()*-1);
                nextHam.play();
                System.out.println("next");
                break;
            }
            case 1 :{
                backHam.setRate(backHam.getRate()*-1);
                backHam.play();
                System.out.println("back");
                break;
            }
        }

    }
    @FXML
    public void addHandle(ActionEvent event){
        s1 = np.getText();
        s2 = adr.getText();
        s3 = tel.getText();
        if(birth.getValue()!=null)
            s4 = Date.valueOf(birth.getValue());
        String nome = "", prenom = "";
        Employee emp = new Employee();
//want to bring the nullpointer exception out of these for catching and using it
        if(s1.trim().equals("")||s1.equals(null)){
            img1.setVisible(true);
            Timeline t = new Timeline(new KeyFrame(Duration.millis(1500), ae -> img1.setVisible(false)));
            t.play();
        }
        else {
            for (int i = 0; i < s1.length(); i++) {
                if (s1.charAt(i) == ' ') {
                    nome = s1.substring(0, i);
                    prenom = s1.substring(i + 1, s1.length());
                    nomeBool = true;
                }if(i==s1.length()-1&&nomeBool==false){
                    nome = s1;
                    nomeBool = true;
                }
            }
            emp.setNome(nome.trim());
            emp.setPrenom(prenom.trim());
//            System.out.println(nome);
//            System.out.println(prenom);
        }
//*****************************************************************
        if(s4==null) {
            img2.setVisible(true);
            Timeline t = new Timeline(new KeyFrame(Duration.millis(1500), ae -> img2.setVisible(false)));
            t.play();
        }
        else{
            emp.setDate(s4);
            ddnBool=true;
        }
//**************************Adress**************************************
        if(s2.trim().equals("")||s2.equals(null)) {
            img3.setVisible(true);
            Timeline t = new Timeline(new KeyFrame(Duration.millis(1500), ae -> img3.setVisible(false)));
            t.play();
        }
        else{
            emp.setAdr(s2.trim());
            adrBool=true;
        }
//**********************Tel****************************************
        try {
            if (s3.trim().equals("") || s3.equals(null)) {
                img4.setVisible(true);
                Timeline t = new Timeline(new KeyFrame(Duration.millis(1500), ae -> img4.setVisible(false)));
                t.play();
            } else {
                Integer n = Integer.parseInt(s3);
                emp.setTel(n);
                adrBool = true;
            }
        }catch(NumberFormatException e){
            img4.setVisible(true);
            Timeline t = new Timeline(new KeyFrame(Duration.millis(1500), ae -> img4.setVisible(false)));
            t.play();
        }
//***********************Gender****************************************
        if (mRb.isSelected()){
            emp.setSexe("Masculin");
            genderBool=true;
        }
        else
            if (fRb.isSelected()){
            emp.setSexe("Feminin");
            genderBool = true;
        }
        else{
                img5.setVisible(true);
                Timeline t = new Timeline(new KeyFrame(Duration.millis(1500), ae -> img5.setVisible(false)));
                t.play();
            }
//*******************************************************************
        if(nomeBool==true&&genderBool==true)
        emplList.add(emp);


    }
    @FXML
    public void removeHandle(ActionEvent event) {
        try {
            Employee obj = tabEmp.getSelectionModel().getSelectedItem();
            emplList.remove(obj);
            String s = obj.getNome();//this just to cause N.P.E
        }catch(NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Select item to delete");
            Optional<ButtonType> result = alert.showAndWait();
        }
    }
    @FXML
    public void displayHandle(ActionEvent event){
        try {
            Employee obj = tabEmp.getSelectionModel().getSelectedItem();
            if(obj.getPrenom()!=null)
                np.setText(obj.getNome() + " " + obj.getPrenom());
            else
                np.setText(obj.getNome());

            if (obj.getSexe().equals("Feminin"))
                fRb.setSelected(true);
            if (obj.getSexe().equals("Masculin"))
                mRb.setSelected(true);
        }catch(NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Select item to modify");
            Optional<ButtonType> result = alert.showAndWait();
        }
    }

    @FXML
    public void printHandle(ActionEvent event)throws Exception{
        try {
            Employee obj = tabEmp.getSelectionModel().getSelectedItem();
            Document doc = new Document();
            FileOutputStream fos = new FileOutputStream("x:/test.pdf");
            PdfWriter.getInstance(doc, fos);

            doc.open();

            Paragraph p = new Paragraph("Nome " + obj.getNome());
            Paragraph p2 = new Paragraph("Sexe " + obj.getSexe());

            doc.add(p);
            doc.add(p2);

            doc.close();
            Desktop.getDesktop().open(new File("x:/test.pdf"));
            String s = obj.getNome();//this just to cause N.P.E

    }catch(NullPointerException e){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Select item to print");
        Optional<ButtonType> result = alert.showAndWait();
    }
    }

}
