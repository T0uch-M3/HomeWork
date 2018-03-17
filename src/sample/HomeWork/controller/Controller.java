package sample.HomeWork.controller;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import sample.HomeWork.view.Employee;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private JFXTextField np, tel, adr;
    @FXML
    private DatePicker birth;
    @FXML
    private JFXRadioButton mRb, fRb;
    @FXML
    private TableView<Employee> tabEmp;
    @FXML
    private TableColumn<Employee, String> c1;
    @FXML
    private TableColumn<Employee, String> c2;
    private ObservableList<Employee> emplList = FXCollections.observableArrayList();
    private ToggleGroup tg = new ToggleGroup();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

//        c1.setCellValueFactory(new PropertyValueFactory<>("nome"));
//        c2.setCellValueFactory((new PropertyValueFactory<>("sexe")));
        c1.setCellValueFactory(cellData -> cellData.getValue().getNomeProperty());
        c2.setCellValueFactory(cellData -> cellData.getValue().getSexeProperty());
        tabEmp.setItems(emplList);
    }

    @FXML
    public void addHandle(ActionEvent event){
        String s = np.getText();
        String s1 = " ", s2 = " ";
        Employee emp = new Employee();
        for (int i = 0; i<s.length();i++){
            if(s.charAt(i)== ' ') {
                s1 = s.substring(0, i);
                s2 = s.substring(i + 1, s.length());

            }
        }
        emp.setNome(s1);
        emp.setPrenom(s2);

        if (mRb.isSelected())
            emp.setSexe("Masculin");
        if (fRb.isSelected())
            emp.setSexe("Feminin");
        emplList.add(emp);


        System.out.println(s1+"+"+s2);
    }
    @FXML
    public void removeHandle(ActionEvent event) {
        Employee obj = tabEmp.getSelectionModel().getSelectedItem();
        emplList.remove(obj);
    }
    @FXML
    public void displayHandle(ActionEvent event){
        Employee obj = tabEmp.getSelectionModel().getSelectedItem();
        np.setText(obj.getNome()+" "+obj.getPrenom());
        if(obj.getSexe().equals("Feminin"))
            fRb.setSelected(true);
        if(obj.getSexe().equals("Masculin"))
            mRb.setSelected(true);
    }

    @FXML
    public void printHandle(ActionEvent event)throws Exception{
        Employee obj = tabEmp.getSelectionModel().getSelectedItem();
        Document doc = new Document();
        FileOutputStream fos = new FileOutputStream("x:/test.pdf");
        PdfWriter.getInstance(doc, fos);

        doc.open();

        Paragraph p = new Paragraph("Nome "+obj.getNome());
        Paragraph p2 = new Paragraph("Sexe "+obj.getSexe());

        doc.add(p);
        doc.add(p2);

        doc.close();
        Desktop.getDesktop().open(new File("x:/test.pdf"));
    }

}
