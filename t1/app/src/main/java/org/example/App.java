package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.control.cell.PropertyValueFactory;

public class App extends Application {
    boolean flagEdit;

    TableView<Buku> tableView;
    TableColumn<Buku, Integer> idBuku;
    TableColumn<Buku, String> judul;
    TableColumn<Buku, String> penerbit;
    TableColumn<Buku, String> penulis;
    TableColumn<Buku, Integer> tahun_terbit;

    TableView<Pustakawan> tableViewPustakawan;
    TableColumn<Pustakawan, Integer> idPustakawan;
    TableColumn<Pustakawan, String> namaPustakawan;
    TableColumn<Pustakawan, String> alamatPustakawan;

    TableView<Anggota> tableViewAnggota;
    TableColumn<Anggota, Integer> idAnggota;
    TableColumn<Anggota, String> namaAnggota;
    TableColumn<Anggota, String> alamatAnggota;

    TextField tfIdBuku;
    TextField tfJudul;
    TextField tfPenerbit;
    TextField tfPenulis;
    TextField tfTahunTerbit;

    TextField tfIdPustakawan;
    TextField tfNamaPustakawan;
    TextField tfAlamatPustakawan;

    TextField tfIdAnggota;
    TextField tfNamaAnggota;
    TextField tfAlamatAnggota;

    Button bUpdate;
    Button bCancel;
    Button bAdd;
    Button bEdit;
    Button bDel;

    BorderPane border;

    @Override
    public void start(Stage stage) throws Exception {
        border = new BorderPane();
        border.setTop(addHBox()); // Setup header
        border.setLeft(addVBox()); // Setup side bar
        border.setCenter(addVBoxHome()); // setup content

        Scene scene = new Scene(border);
        stage.setScene(scene);
        stage.setTitle("Sistem Perpustakaan");
        stage.setTitle("Aplikasi Sistem Perpustakaan");
        stage.show();
    }

    // Header
    private HBox addHBox() {
        Text tjudul = new Text("Sistem Perpustakaan");
        tjudul.setFont(Font.font("Verdana", 20));
        tjudul.setFill(Color.WHITESMOKE);

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().add(tjudul);

        return hbox;
    }

    // Side Bar
    private VBox addVBox() {
        Text title = new Text("Perpustakaan");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);
        vbox.getChildren().add(title);

        // Menu Side Bar
        Hyperlink options[] = new Hyperlink[] {
                new Hyperlink("Home"),
                new Hyperlink("Buku"),
                new Hyperlink("Pustakawan"),
                new Hyperlink("Anggota"),
                new Hyperlink("Peminjaman"),
                new Hyperlink("Koleksi Buku"),
                new Hyperlink("Selesai") };
        for (int i = 0; i < 7; i++) {
            VBox.setMargin(options[i], new Insets(0, 0, 0, 8));
            vbox.getChildren().add(options[i]);
        }

        // Action Side Bar
        options[0].setOnAction(e -> {
            border.setCenter(addVBoxHome());
        });
        options[1].setOnAction(e -> {
            border.setCenter(addVBoxBuku());
        });
        options[2].setOnAction(e -> {
            border.setCenter(addVBoxPustakawan());
        });
        options[3].setOnAction(e -> {
            border.setCenter(addVBoxAnggota());
        });
        options[6].setOnAction(e -> {
            System.exit(0);
        });

        return vbox;
    }

    // Unused Method
    // private FlowPane AddFlowPaneHome() {
    // FlowPane fp = new FlowPane();
    // Text tjudul = new Text("Home");
    // tjudul.setFont(Font.font("Arial", 18));
    // fp.setAlignment(Pos.CENTER);
    // FileInputStream input = null;
    // try {
    // File file = new File("src/main/resources/dolphin.jpg");
    // input = new FileInputStream(file);
    // } catch (FileNotFoundException e) {
    // throw new RuntimeException(e);
    // }
    // Image image = new Image(input);
    // ImageView imageview = new ImageView(image);
    // fp.getChildren().add(tjudul);
    // fp.getChildren().add(imageview);
    // return fp;
    // }

    private VBox addVBoxHome() {
        Text tjudul = new Text("Home");
        tjudul.setFont(Font.font("Arial", 18));

        // Home image
        FileInputStream input = null;
        try {
            File file = new File("src/main/resources/dolphin.jpg");
            input = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Image image = new Image(input);
        ImageView imageview = new ImageView(image);
        imageview.setFitHeight(500);
        imageview.setPreserveRatio(true);

        VBox vb = new VBox();
        vb.setFillWidth(true);
        vb.setAlignment(Pos.CENTER);
        vb.getChildren().add(tjudul);
        vb.getChildren().add(imageview);
        return vb;
    }

    // ===========================================================
    // Content ===================================================
    // ===========================================================

    // Buku ======================================================
    // Table Buku
    @SuppressWarnings("unchecked")
    StackPane spTableBuku() {
        tableView = new TableView<Buku>();
        idBuku = new TableColumn<>("idBuku");
        judul = new TableColumn<>("judul");
        penerbit = new TableColumn<>("penerbit");
        penulis = new TableColumn<>("penulis");
        tahun_terbit = new TableColumn<Buku, Integer>("tahun_terbit");
        // for hot reload
        tableView.getColumns().addAll(idBuku, judul, penerbit, penulis, tahun_terbit);

        idBuku.setCellValueFactory(new PropertyValueFactory<Buku, Integer>("idBuku"));
        judul.setCellValueFactory(new PropertyValueFactory<Buku, String>("judul"));
        penerbit.setCellValueFactory(new PropertyValueFactory<Buku, String>("penerbit"));
        penulis.setCellValueFactory(new PropertyValueFactory<Buku, String>("penulis"));
        tahun_terbit.setCellValueFactory(new PropertyValueFactory<Buku, Integer>("tahun_terbit"));

        StackPane sp = new StackPane();
        sp.getChildren().add(tableView);
        return sp;
    }

    // Form Buku
    GridPane gpFormBuku() {
        flagEdit = false;

        GridPane gp = new GridPane();
        gp.setPrefHeight(500);
        gp.setAlignment(Pos.CENTER);
        gp.setVgap(10);
        gp.setHgap(10);
        gp.setPadding(new Insets(10, 10, 10, 10));

        Label lbJudulForm = new Label("Form Data Buku");
        Label lbIdBuku = new Label("Id Buku");
        Label lbJudul = new Label("Judul");
        Label lbPenerbit = new Label("Penerbit");
        Label lbPenulis = new Label("Penulis");
        Label lbTahunTerbit = new Label("Tahun Terbit");

        tfIdBuku = new TextField();
        tfJudul = new TextField();
        tfPenerbit = new TextField();
        tfPenulis = new TextField();
        tfTahunTerbit = new TextField();

        bUpdate = new Button("Update");
        bCancel = new Button("Cancel");
        bUpdate.setPrefWidth(100);
        bCancel.setPrefWidth(100);

        bAdd = new Button("Add");
        bEdit = new Button("Edit");
        bDel = new Button("Del");
        bAdd.setPrefWidth(100);
        bEdit.setPrefWidth(100);
        bDel.setPrefWidth(100);

        bAdd.setOnAction(e -> {
            flagEdit = false;

            clearTextFields(tfIdBuku, tfJudul, tfPenerbit, tfPenulis, tfTahunTerbit);
            teksAktif(true, tfIdBuku, tfJudul, tfPenerbit, tfPenulis, tfTahunTerbit);
            buttonAktif(true);
        });

        bUpdate.setOnAction(e -> {
            int idBuku, tahunTerbit;
            String judul, penulis, penerbit;

            idBuku = Integer.parseInt(tfIdBuku.getText());
            judul = tfJudul.getText();
            penulis = tfPenulis.getText();
            penerbit = tfPenerbit.getText();
            tahunTerbit = Integer.parseInt(tfTahunTerbit.getText());

            Buku b = new Buku(idBuku, judul, penerbit, penulis, tahunTerbit);
            if (flagEdit == false) {
                tableView.getItems().add(b);
            } else {
                int idx = tableView.getSelectionModel().getSelectedIndex();
                tableView.getItems().set(idx, b);
            }

            teksAktif(false, tfIdBuku, tfJudul, tfPenerbit, tfPenulis, tfTahunTerbit);
            buttonAktif(false);
            clearTextFields(tfIdBuku, tfJudul, tfPenerbit, tfPenulis, tfTahunTerbit);
            flagEdit = true;
        });

        bEdit.setOnAction(e -> {
            buttonAktif(true);
            teksAktif(true, tfIdBuku, tfJudul, tfPenerbit, tfPenulis, tfTahunTerbit);
            flagEdit = true;
            int idx = tableView.getSelectionModel().getSelectedIndex();
            tfIdBuku.setText(String.valueOf(tableView.getItems().get(idx).getIdBuku()));
            tfJudul.setText(tableView.getItems().get(idx).getJudul());
            tfPenerbit.setText(tableView.getItems().get(idx).getPenulis());
            tfPenulis.setText(tableView.getItems().get(idx).getPenulis());
            tfTahunTerbit.setText(String.valueOf(tableView.getItems().get(idx).getTahun_terbit()));
        });

        bDel.setOnAction(e -> {
            int idx = tableView.getSelectionModel().getSelectedIndex();
            tableView.getItems().remove(idx);
            clearTextFields(tfIdBuku, tfJudul, tfPenerbit, tfPenulis, tfTahunTerbit);
        });

        bCancel.setOnAction(e -> {
            teksAktif(false, tfIdBuku, tfJudul, tfPenerbit, tfPenulis, tfTahunTerbit);
            buttonAktif(false);
        });

        TilePane tp1 = new TilePane();
        tp1.getChildren().addAll(bAdd, bEdit, bDel, bUpdate, bCancel);
        gp.addRow(0, new Label(""), lbJudulForm);
        gp.addRow(1, lbIdBuku, tfIdBuku);
        gp.addRow(2, lbJudul, tfJudul);
        gp.addRow(3, lbPenerbit, tfPenerbit);
        gp.addRow(4, lbPenulis, tfPenulis);
        gp.addRow(5, lbTahunTerbit, tfTahunTerbit);
        gp.addRow(6, new Label(""), tp1);
        teksAktif(false, tfIdBuku, tfJudul, tfPenerbit, tfPenulis, tfTahunTerbit);
        buttonAktif(false);
        return gp;
    }

    // Vbox Buku
    private VBox addVBoxBuku() {
        Text tjudul = new Text("Form Data Buku");
        tjudul.setFont(Font.font("Arial", 18));

        VBox vb = new VBox();
        vb.getChildren().add(tjudul);
        vb.setAlignment(Pos.CENTER);
        vb.getChildren().add(spTableBuku());
        vb.getChildren().add(gpFormBuku());
        return vb;
    }

    // Pustakawan ======================================================
    // Table Pustakawan
    @SuppressWarnings("unchecked")
    StackPane spTablePustakawan() {
        tableViewPustakawan = new TableView<Pustakawan>();
        idPustakawan = new TableColumn<>("ID");
        namaPustakawan = new TableColumn<>("Nama");
        alamatPustakawan = new TableColumn<>("Alamat");

        // for hot reload
        tableViewPustakawan.getColumns().addAll(idPustakawan, namaPustakawan, alamatPustakawan);

        idPustakawan.setCellValueFactory(new PropertyValueFactory<Pustakawan, Integer>("idPustakawan"));
        namaPustakawan.setCellValueFactory(new PropertyValueFactory<Pustakawan, String>("namaPustakawan"));
        alamatPustakawan.setCellValueFactory(new PropertyValueFactory<Pustakawan, String>("alamatPustakawan"));

        StackPane sp = new StackPane();
        sp.getChildren().add(tableViewPustakawan);
        return sp;
    }

    // Form Pustakawan
    GridPane gpFormPustakawan() {
        flagEdit = false;

        GridPane gp = new GridPane();
        gp.setPrefHeight(500);
        gp.setAlignment(Pos.CENTER);
        gp.setVgap(10);
        gp.setHgap(10);
        gp.setPadding(new Insets(10, 10, 10, 10));

        Label lbJudulForm = new Label("Form Data Pustakawan");
        Label lbIdPustakawan = new Label("ID");
        Label lbNamaPustakawan = new Label("Nama");
        Label lbAlamatPustakawan = new Label("Alamat");

        tfIdPustakawan = new TextField();
        tfNamaPustakawan = new TextField();
        tfAlamatPustakawan = new TextField();

        bUpdate = new Button("Update");
        bCancel = new Button("Cancel");
        bUpdate.setPrefWidth(100);
        bCancel.setPrefWidth(100);

        bAdd = new Button("Add");
        bEdit = new Button("Edit");
        bDel = new Button("Del");
        bAdd.setPrefWidth(100);
        bEdit.setPrefWidth(100);
        bDel.setPrefWidth(100);

        bAdd.setOnAction(e -> {
            flagEdit = false;

            clearTextFields(tfIdPustakawan, tfNamaPustakawan, tfAlamatPustakawan);
            teksAktif(true, tfIdPustakawan, tfNamaPustakawan, tfAlamatPustakawan);
            buttonAktif(true);
        });

        bUpdate.setOnAction(e -> {
            int idPustakawan;
            String namaPustakawan, alamatPustakawan;

            idPustakawan = Integer.parseInt(tfIdPustakawan.getText());
            namaPustakawan = tfNamaPustakawan.getText();
            alamatPustakawan = tfAlamatPustakawan.getText();

            Pustakawan p = new Pustakawan(idPustakawan, namaPustakawan, alamatPustakawan);
            if (flagEdit == false) {
                tableViewPustakawan.getItems().add(p);
            } else {
                int idx = tableViewPustakawan.getSelectionModel().getSelectedIndex();
                tableViewPustakawan.getItems().set(idx, p);
            }

            teksAktif(false, tfIdPustakawan, tfNamaPustakawan, tfAlamatPustakawan);
            buttonAktif(false);
            clearTextFields(tfIdPustakawan, tfNamaPustakawan, tfAlamatPustakawan);
            flagEdit = true;
        });

        bEdit.setOnAction(e -> {
            buttonAktif(true);
            teksAktif(true, tfIdPustakawan, tfNamaPustakawan, tfAlamatPustakawan);
            flagEdit = true;
            int idx = tableViewPustakawan.getSelectionModel().getSelectedIndex();
            tfIdPustakawan.setText(String.valueOf(tableViewPustakawan.getItems().get(idx).getIdPustakawan()));
            tfNamaPustakawan.setText(tableViewPustakawan.getItems().get(idx).getNamaPustakawan());
            tfAlamatPustakawan.setText(tableViewPustakawan.getItems().get(idx).getAlamatPustakawan());
        });

        bDel.setOnAction(e -> {
            int idx = tableViewPustakawan.getSelectionModel().getSelectedIndex();
            tableViewPustakawan.getItems().remove(idx);
            clearTextFields(tfIdPustakawan, tfNamaPustakawan, tfAlamatPustakawan);
        });

        bCancel.setOnAction(e -> {
            teksAktif(false, tfIdPustakawan, tfNamaPustakawan, tfAlamatPustakawan);
            buttonAktif(false);
        });

        TilePane tp1 = new TilePane();
        tp1.getChildren().addAll(bAdd, bEdit, bDel, bUpdate, bCancel);
        gp.addRow(0, new Label(""), lbJudulForm);
        gp.addRow(1, lbIdPustakawan, tfIdPustakawan);
        gp.addRow(2, lbNamaPustakawan, tfNamaPustakawan);
        gp.addRow(3, lbAlamatPustakawan, tfAlamatPustakawan);
        gp.addRow(6, new Label(""), tp1);
        teksAktif(false, tfIdPustakawan, tfNamaPustakawan, tfAlamatPustakawan);
        buttonAktif(false);
        return gp;
    }

    // Vbox Pustakawan
    private VBox addVBoxPustakawan() {
        Text tjudul = new Text("Form Data Pustakawan");
        tjudul.setFont(Font.font("Arial", 18));

        VBox vb = new VBox();
        vb.getChildren().add(tjudul);
        vb.setAlignment(Pos.CENTER);
        vb.getChildren().add(spTablePustakawan());
        vb.getChildren().add(gpFormPustakawan());
        return vb;
    }

    // Anggota ======================================================
    // Table Anggota
    @SuppressWarnings("unchecked")
    StackPane spTableAnggota() {
        tableViewAnggota = new TableView<Anggota>();
        idAnggota = new TableColumn<>("ID");
        namaAnggota = new TableColumn<>("Nama");
        alamatAnggota = new TableColumn<>("Alamat");

        // for hot reload
        tableViewAnggota.getColumns().addAll(idAnggota, namaAnggota, alamatAnggota);

        idAnggota.setCellValueFactory(new PropertyValueFactory<Anggota, Integer>("idAnggota"));
        namaAnggota.setCellValueFactory(new PropertyValueFactory<Anggota, String>("namaAnggota"));
        alamatAnggota.setCellValueFactory(new PropertyValueFactory<Anggota, String>("alamatAnggota"));

        StackPane sp = new StackPane();
        sp.getChildren().add(tableViewAnggota);
        return sp;
    }

    // Form Anggota
    GridPane gpFormAnggota() {
        flagEdit = false;

        GridPane gp = new GridPane();
        gp.setPrefHeight(500);
        gp.setAlignment(Pos.CENTER);
        gp.setVgap(10);
        gp.setHgap(10);
        gp.setPadding(new Insets(10, 10, 10, 10));

        Label lbJudulForm = new Label("Form Data Anggota");
        Label lbIdAnggota = new Label("ID");
        Label lbNamaAnggota = new Label("Nama");
        Label lbAlamatAnggota = new Label("Alamat");

        tfIdAnggota = new TextField();
        tfNamaAnggota = new TextField();
        tfAlamatAnggota = new TextField();

        bUpdate = new Button("Update");
        bCancel = new Button("Cancel");
        bUpdate.setPrefWidth(100);
        bCancel.setPrefWidth(100);

        bAdd = new Button("Add");
        bEdit = new Button("Edit");
        bDel = new Button("Del");
        bAdd.setPrefWidth(100);
        bEdit.setPrefWidth(100);
        bDel.setPrefWidth(100);

        bAdd.setOnAction(e -> {
            flagEdit = false;

            clearTextFields(tfIdAnggota, tfNamaAnggota, tfAlamatAnggota);
            teksAktif(true, tfIdAnggota, tfNamaAnggota, tfAlamatAnggota);
            buttonAktif(true);
        });

        bUpdate.setOnAction(e -> {
            int idAnggota;
            String namaAnggota, alamatAnggota;

            idAnggota = Integer.parseInt(tfIdAnggota.getText());
            namaAnggota = tfNamaAnggota.getText();
            alamatAnggota = tfAlamatAnggota.getText();

            Anggota p = new Anggota(idAnggota, namaAnggota, alamatAnggota);
            if (flagEdit == false) {
                tableViewAnggota.getItems().add(p);
            } else {
                int idx = tableViewAnggota.getSelectionModel().getSelectedIndex();
                tableViewAnggota.getItems().set(idx, p);
            }

            teksAktif(false, tfIdAnggota, tfNamaAnggota, tfAlamatAnggota);
            buttonAktif(false);
            clearTextFields(tfIdAnggota, tfNamaAnggota, tfAlamatAnggota);
            flagEdit = true;
        });

        bEdit.setOnAction(e -> {
            buttonAktif(true);
            teksAktif(true, tfIdAnggota, tfNamaAnggota, tfAlamatAnggota);
            flagEdit = true;
            int idx = tableViewAnggota.getSelectionModel().getSelectedIndex();
            tfIdAnggota.setText(String.valueOf(tableViewAnggota.getItems().get(idx).getIdAnggota()));
            tfNamaAnggota.setText(tableViewAnggota.getItems().get(idx).getNamaAnggota());
            tfAlamatAnggota.setText(tableViewAnggota.getItems().get(idx).getAlamatAnggota());
        });

        bDel.setOnAction(e -> {
            int idx = tableViewAnggota.getSelectionModel().getSelectedIndex();
            tableViewAnggota.getItems().remove(idx);
            clearTextFields(tfIdAnggota, tfNamaAnggota, tfAlamatAnggota);
        });

        bCancel.setOnAction(e -> {
            teksAktif(false, tfIdAnggota, tfNamaAnggota, tfAlamatAnggota);
            buttonAktif(false);
        });

        TilePane tp1 = new TilePane();
        tp1.getChildren().addAll(bAdd, bEdit, bDel, bUpdate, bCancel);
        gp.addRow(0, new Label(""), lbJudulForm);
        gp.addRow(1, lbIdAnggota, tfIdAnggota);
        gp.addRow(2, lbNamaAnggota, tfNamaAnggota);
        gp.addRow(3, lbAlamatAnggota, tfAlamatAnggota);
        gp.addRow(6, new Label(""), tp1);
        teksAktif(false, tfIdAnggota, tfNamaAnggota, tfAlamatAnggota);
        buttonAktif(false);
        return gp;
    }

    // Vbox Anggota
    private VBox addVBoxAnggota() {
        Text tjudul = new Text("Form Data Anggota");
        tjudul.setFont(Font.font("Arial", 18));

        VBox vb = new VBox();
        vb.getChildren().add(tjudul);
        vb.setAlignment(Pos.CENTER);
        vb.getChildren().add(spTableAnggota());
        vb.getChildren().add(gpFormAnggota());
        return vb;
    }

    // ===========================================================
    // End Content ===============================================
    // ===========================================================

    // Action
    public void buttonAktif(boolean nonAktif) {
        bAdd.setDisable(nonAktif);
        bEdit.setDisable(nonAktif);
        bDel.setDisable(nonAktif);
        bUpdate.setDisable(!nonAktif);
        bCancel.setDisable(!nonAktif);
    }

    public void teksAktif(boolean aktif, TextField... textFields) {
        for (TextField textField : textFields) {
            textField.setEditable(aktif);
        }
    }

    public static void clearTextFields(TextField... textFields) {
        for (TextField textField : textFields) {
            textField.clear();
        }
    }
}