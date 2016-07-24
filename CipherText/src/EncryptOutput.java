/*
-------------------------------------------------
|   Created by Milan Conhye                     |
|   University of Greenwich                     |
|                                               |
|   Website: www.milanconhye.com                |
|   GitHub: https://github.com/milanconhye      |
|                                               |
-------------------------------------------------
*/

//Required Imports
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class EncryptOutput {

    //Initialize variables to be exported
    private static String algorithmTypeExp, keyCryptExp, encryptedTextExp;

    //Display Requested Information
    public static void display(String title, String origText, String algorithmType, int keyStrength, String keyCrypt, String encryptedText) {
        //Set Stage
        Stage window = new Stage();

        //Block interaction with another windows, until this window is taken care of
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("CipherText: " + title);
        window.setMinWidth(250);

        //Initialize Labels and Place in text
        Label origTextLbl = new Label("Original Message: " + origText);
        Label algorithmTypeLbl = new Label("Algorithm Type: " + algorithmType + " - " + keyStrength + " bits");
        Label aesKeyLbl = new Label("Decryption Key: " + keyCrypt);
        Label encryptedTextLbl = new Label("Encrypted Text: " + encryptedText);

        //Export Details which are printed on OutPut Box
        algorithmTypeExp = algorithmType;
        keyCryptExp = keyCrypt;
        encryptedTextExp = encryptedText;

        //Initialize Buttons
        Button exportInfoButton = new Button("Export Details");
        Button closeButton = new Button("Close");

        //When Export Info and close button is pressed
        exportInfoButton.setOnAction(e -> exportInfo());
        closeButton.setOnAction(e -> window.close());

        //Sets Layouts and Properties
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setVgap(8);
        layout.setHgap(10);

        //Positions Components within the GridPane
        GridPane.setConstraints(origTextLbl, 0, 1);
        GridPane.setConstraints(algorithmTypeLbl, 0, 2);
        GridPane.setConstraints(aesKeyLbl, 0, 3);
        GridPane.setConstraints(encryptedTextLbl, 0, 4);
        GridPane.setConstraints(exportInfoButton, 0, 5);
        GridPane.setConstraints(closeButton, 1, 5);

        //Add all components and place them on the screen
        layout.getChildren().addAll(origTextLbl, algorithmTypeLbl, aesKeyLbl, encryptedTextLbl, exportInfoButton
                ,closeButton);

        //Sets the Alignment to be centered
        layout.setAlignment(Pos.CENTER);

        //Initialize Scene and Add Layout
        Scene scene = new Scene(layout);

        //Add Design Style Sheet
        scene.getStylesheets().add("Design.css");

        //Sets Scene and Properties
        window.setScene(scene);
        window.setResizable(false);

        //Display the window, before it returns, it needs to be closed
        window.showAndWait();

    }

    private static void exportInfo() {

        //Initialize Arrays as List and Store it within a String Object - Append Text
        List<String> encryptedTextAppend = Arrays.asList("Encrypted Text: " + encryptedTextExp);
        List<String> encryptedKeyAppend = Arrays.asList("Algorithm Type: " + algorithmTypeExp + System.lineSeparator()
                + "Decryption Key: " + keyCryptExp);

        //Initialize Paths to be set on Desktop
        Path fileEncryptedText = Paths.get(System.getProperty("user.home") + "/Desktop/EncryptedText.txt");
        Path fileEncryptedKey = Paths.get(System.getProperty("user.home") + "/Desktop/DecryptionKey.txt");

        //Attempt to Append the string object within the files, create the he files and display message box
        try {
            MessageBox.display("Warning!", "Two Files: EncryptedText.txt and DecryptionKey.txt will be saved on to your desktop");
            Files.write(fileEncryptedText, encryptedTextAppend, Charset.forName("UTF-8"));
            Files.write(fileEncryptedKey, encryptedKeyAppend, Charset.forName("UTF-8"));

        //If files cannot be written.
        } catch (Exception ex) {
            MessageBox.display("Error!", "Could not write to Files, please try again later");
        }

    }

}