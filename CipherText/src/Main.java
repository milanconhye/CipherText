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
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.lang.reflect.Field;

public class Main extends Application {

    //Initialize TabPane
    TabPane tabPane = new TabPane();

    //Initialize KeyStrength ChoiceBox
    ChoiceBox<Integer> keyStrengthCB = new ChoiceBox<>();

    //Initialize AlgorithmType ChoiceBox
    ChoiceBox<String> encryptAlgorithmTypeCB = new ChoiceBox<>();
    ChoiceBox<String> decryptAlgorithmTypeCB = new ChoiceBox<>();

    //Initialize Text Area and Fields
    TextArea messageEncryptTA = new TextArea();
    TextArea messageEncryptedTA = new TextArea();
    TextField keyTF = new TextField();


    public static void main(String[] args) {

        //Enables Unlimited Encryption and Decryption Policy
        try {
            Field field = Class.forName("javax.crypto.JceSecurity").getDeclaredField("isRestricted");
            field.setAccessible(true);
            field.set(null, java.lang.Boolean.FALSE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //Launches arguments from start method.
        launch(args);
    }


    public void start(Stage primaryStage) throws Exception {
        //Set Title and Icons
        primaryStage.setTitle("CipherText");
        primaryStage.getIcons().addAll(new Image("/icons/padlock16x16.png"), new Image("/icons/padlock32x32.png"));

        //Initialize Group root for Main Node
        Group root = new Group();

        //Initialize Scene on group root with specific sizes
        Scene scene = new Scene(root, 450, 250);

        //Add Design Style Sheet
        scene.getStylesheets().add("Design.css");

        //Prevent from closing Tabs
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        //Adding Tabs to TabControl
        EncryptTabContent();
        DecryptTabContent();
        AboutTabContent();

        //Initialize BorderPane and Bind the layout with the scene size.
        BorderPane borderPane = new BorderPane();
        borderPane.prefHeightProperty().bind(scene.heightProperty());
        borderPane.prefWidthProperty().bind(scene.widthProperty());

        //Set the TabPane to be centered
        borderPane.setCenter(tabPane);

        //Adds Layout to Main Node
        root.getChildren().add(borderPane);

        //Set Scenes
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void EncryptTabContent() {

        //Initialize Tab
        Tab EncryptTab = new Tab("Encrypt Text");

        //Initialize Labels
        Label encryptMessageLabel = new Label("Encrypt Message: ");
        Label keyStrengthLabel = new Label("Key Strength (bits): ");
        Label algorithmTypeLabel = new Label("Algorithm Type: ");

        //Initialize Button
        Button encryptionStart = new Button("Cipher");

        //Initialize Layout and Set Main Properties
        GridPane gridLayout = new GridPane();
        gridLayout.setPadding(new Insets(20, 20, 20, 20));
        gridLayout.setVgap(8);
        gridLayout.setHgap(10);

        //Positions Layouts in appropriate places
        GridPane.setConstraints(algorithmTypeLabel, 0, 0);
        GridPane.setConstraints(encryptAlgorithmTypeCB, 1, 0);

        GridPane.setConstraints(keyStrengthLabel, 0, 1);
        GridPane.setConstraints(keyStrengthCB, 1, 1);

        GridPane.setConstraints(encryptMessageLabel, 0, 2);
        GridPane.setConstraints(messageEncryptTA, 1, 2);

        GridPane.setConstraints(encryptionStart, 1, 3);

        //Setting Width and Height of Text Area
        messageEncryptTA.setPrefWidth(250);
        messageEncryptTA.setPrefHeight(100);

        //Text Area Prompt Text
        messageEncryptTA.setPromptText("What message would you like to encrypt?");

        //encryptAlgorithmTypeCB Properties and Values
        encryptAlgorithmTypeCB.setTooltip(new Tooltip("Choose the type of encryption"));
        encryptAlgorithmTypeCB.setValue("AES");
        encryptAlgorithmTypeCB.getItems().addAll("AES", "BLOWFISH", "DES", "TRIPLEDES", "RC4", "DESEDE");

        //Default Algorithms

        /*
        * AES = 128, 192 or 256 exactly
        * BLOWFISH = between 32 - 448 in multiples of 8
        * DES = 56 exactly
        * TRIPLEDES = 112 or 168 exactly
        * RC2/RC4 = between 40 - 1024 exactly
        * DESEDE = 122 or 168 exactly
        */

        //DEFAULT Selection is AES
        keyStrengthCB.setTooltip(new Tooltip("Choose how strong you want the encryption"));
        keyStrengthCB.setValue(128);
        keyStrengthCB.getItems().addAll(128, 192, 256);

        //NOTE: Some Loops have been limited in there bitSizes. This is due the lack of support of low end processors

        //Other Selections vary is bitSize
        encryptAlgorithmTypeCB.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) -> {

            //Items need to be cleared for every Algorithm selection
            keyStrengthCB.getItems().clear();

            //AES Algorithm
            if (encryptAlgorithmTypeCB.getSelectionModel().getSelectedItem() == "AES") {
                keyStrengthCB.setValue(128);
                keyStrengthCB.getItems().addAll(128, 192, 256);
            }

            //BLOWFISH Algorithm
            if (encryptAlgorithmTypeCB.getSelectionModel().getSelectedItem() == "BLOWFISH") {
                keyStrengthCB.setValue(128);
                keyStrengthCB.getItems().addAll(128, 256, 336, 448);

            }

            //DES Algorithm
            if (encryptAlgorithmTypeCB.getSelectionModel().getSelectedItem() == "DES") {
                keyStrengthCB.setValue(56);
                keyStrengthCB.getItems().add(56);
            }

            //TRIPLEDES Algorithm
            if (encryptAlgorithmTypeCB.getSelectionModel().getSelectedItem() == "TRIPLEDES") {
                keyStrengthCB.setValue(112);
                keyStrengthCB.getItems().addAll(112, 168);
            }

            //RC4 Algorithm
            if (encryptAlgorithmTypeCB.getSelectionModel().getSelectedItem() == "RC4") {
                keyStrengthCB.setValue(128);
                keyStrengthCB.getItems().addAll(128, 256, 512, 1024);

            }

            //DESEDE Algorithm
            if (encryptAlgorithmTypeCB.getSelectionModel().getSelectedItem() == "DESEDE") {
                keyStrengthCB.setValue(122);
                keyStrengthCB.getItems().addAll(122, 168);
            }

        });

        //Action - When the Cipher Button is pressed
        encryptionStart.setOnAction(e -> executeEncryption());

        //Adding Components on screen
        gridLayout.getChildren().addAll(algorithmTypeLabel, encryptAlgorithmTypeCB, keyStrengthLabel,
                keyStrengthCB, encryptMessageLabel, messageEncryptTA, encryptionStart);

        //Setting content and adding tab to TabPane
        EncryptTab.setContent(gridLayout);
        tabPane.getTabs().add(EncryptTab);
    }

    //Global Variables to store temporary data
    String encryptMessageStr;
    private static String algorithmType;
    private static int keyStrength;

    private void executeEncryption() {

        //Parsing information from components to String or Int
        encryptMessageStr = messageEncryptTA.getText();
        algorithmType = encryptAlgorithmTypeCB.getSelectionModel().getSelectedItem();
        keyStrength = keyStrengthCB.getSelectionModel().getSelectedItem();

        //If Message field is not Null or Empty
        if (!messageEncryptTA.getText().isEmpty()) {

            try {

                //Gets a Generated Encryption Key and stores it within a SecretKey Property
                SecretKey secretKey = getSecretEncryptionKey();

                //Stores the Encrypted Text into a Byte Array - Parsing Information from Text Area and Generated Secret Key
                byte[] textCipher = encryptText(encryptMessageStr, secretKey);

                //Display Information on Dialog
                EncryptOutput.display("CipherText", encryptMessageStr, algorithmType, keyStrength, bytesToHex(secretKey.getEncoded()),
                        bytesToHex(textCipher));

                //Clear the Encrypt Message Area and Setting default field values
                messageEncryptTA.clear();
                encryptAlgorithmTypeCB.setValue("AES");
                keyStrengthCB.setValue(128);

            } catch (Exception ex) {
                MessageBox.display("Error!", "Could not encrypt key! Please try again later");
            }

        } else {
            MessageBox.display("Warning!", "Please make sure message field is not empty!");
        }
    }

    public static SecretKey getSecretEncryptionKey() throws Exception {
        //Generates Encryption depending on the Algorithm Type and stores it
        KeyGenerator generateKey = KeyGenerator.getInstance(algorithmType);
        //Takes into account the key strength to determine the bit size of the operation
        generateKey.init(keyStrength);
        //Stores the generatedKey in a SecretKey and returns it
        SecretKey secretKey = generateKey.generateKey();
        return secretKey;
    }

    public static byte[] encryptText(String encryptMessageStr, SecretKey secretKey) throws Exception {
        //Gets the instance of the Algorithm type and stores it within a Cipher Object
        Cipher cipherAES = Cipher.getInstance(algorithmType);
        //Takes into account that the process is in Encrypt Mode and Parses in the Generated Secret Key
        cipherAES.init(Cipher.ENCRYPT_MODE, secretKey);
        //Cipher the Encrypted Message string; convert it to bytes and then store it within a variable and return it
        byte[] cipherTextBytes = cipherAES.doFinal(encryptMessageStr.getBytes());
        return cipherTextBytes;
    }

    private void DecryptTabContent() {

        //Initialize Tab
        Tab DecryptTab = new Tab("Decrypt Text");

        //Initialize Labels
        Label algorithmTypeLbl = new Label("Algorithm Type: ");
        Label keyLbl = new Label("Decryption Key: ");
        Label encryptedMessageLabel = new Label("Encrypted Message: ");

        //Initialize Button
        Button decryptionStartButton = new Button("Decipher");

        //When the decipher button is pressed
        decryptionStartButton.setOnAction(e -> executeDecryption());

        //Sets the Grid Layout with Padding and Gaps
        GridPane gridLay = new GridPane();
        gridLay.setPadding(new Insets(20, 20, 20, 20));
        gridLay.setVgap(8);
        gridLay.setHgap(10);

        //Positions the Components on the GridPane
        GridPane.setConstraints(algorithmTypeLbl, 0, 0);
        GridPane.setConstraints(decryptAlgorithmTypeCB, 1, 0);

        GridPane.setConstraints(keyLbl, 0, 1);
        GridPane.setConstraints(keyTF, 1, 1);

        GridPane.setConstraints(encryptedMessageLabel, 0, 2);
        GridPane.setConstraints(messageEncryptedTA, 1, 2);

        GridPane.setConstraints(decryptionStartButton, 1, 3);

        //Setting Text Area and Field Properties
        keyTF.setPrefWidth(250);
        keyTF.setPromptText("Enter Key to Unlock");
        messageEncryptedTA.setPromptText("What would you like to decrypt?");
        messageEncryptedTA.setPrefWidth(250);
        messageEncryptedTA.setPrefHeight(100);

        //decryptAlgorithmTypeCB Properties and Values
        decryptAlgorithmTypeCB.setTooltip(new Tooltip("Choose the type of encryption you have used"));
        decryptAlgorithmTypeCB.setValue("AES");
        decryptAlgorithmTypeCB.getItems().addAll("AES", "BLOWFISH", "DES", "TRIPLEDES", "RC4", "DESEDE");

        //Adds Components to Layout
        gridLay.getChildren().addAll(algorithmTypeLbl, decryptAlgorithmTypeCB, keyLbl, keyTF, encryptedMessageLabel,
                messageEncryptedTA, decryptionStartButton);

        //Sets content to Tab and adds to Tab Pane
        DecryptTab.setContent(gridLay);
        tabPane.getTabs().add(DecryptTab);

    }

    //Initialize Temp Variables
    String encryptedMessageStr;
    String keyDecrypt;

    private void executeDecryption() {

        //Parsing information from components to String or Int.
        encryptedMessageStr = messageEncryptedTA.getText();
        algorithmType = decryptAlgorithmTypeCB.getSelectionModel().getSelectedItem();
        keyDecrypt = keyTF.getText();

        //If Message field is not Null or Empty
        if (!messageEncryptedTA.getText().isEmpty()) {

            try {
                //Request Focus from Text Area
                keyTF.requestFocus();
                //Convert the Hexed Decryption Key to Array and store it into a Byte Array
                byte[] keyDecryptBytes = toByteArray(keyDecrypt);
                //Place the KeyDecrypt into an original key for the decryption process - taking into account length, and Algorithm type
                SecretKey originalKey = new SecretKeySpec(keyDecryptBytes, 0, keyDecryptBytes.length, algorithmType);
                //Store the decrypted text into a string ready for output
                String textDecipher = decryptText(toByteArray(encryptedMessageStr), originalKey);

                //Sets Default values - ready for more decryption
                decryptAlgorithmTypeCB.setValue("AES");
                keyTF.clear();

                //Sets the output of the decryption to be stored in the Message Encrypted Text area.
                messageEncryptedTA.setText(textDecipher);

            } catch (Exception ex) {
                MessageBox.display("Error!", "Could not Decrypt Message. Please re-evaluate all fields!");
            }

        } else {
            MessageBox.display("Warning!", "Please make sure message field is not empty!");
        }
    }

    public static String decryptText(byte[] encryptedMessageStr, SecretKey secretKey) throws Exception {
        //Gets the instance of the Algorithm type and stores it within a Cipher Object
        Cipher cipherAll = Cipher.getInstance(algorithmType);
        //Takes into account that the process is in Decrypt Mode and Parses in the Custom Secret Key
        cipherAll.init(Cipher.DECRYPT_MODE, secretKey);
        //Cipher the Encrypted Message string; convert it to bytes and then store it within a variable and return it
        byte[] cipherTextBytes = cipherAll.doFinal(encryptedMessageStr);
        return new String (cipherTextBytes);
    }

    private void AboutTabContent() {
        //Initialize Tab
        Tab EncryptTab = new Tab("About");

        //Initialize Labels and sets fonts
        Label appNameLabel = new Label("CipherText (v2.4) - Encrypt and Decrypt Text");
        appNameLabel.setStyle("-fx-font-size: 18px;");
        Label creatorLbl = new Label("By Milan Conhye");
        creatorLbl.setStyle("-fx-font-size: 15px;");

        //Create HyperLinks and when clicked, force them to open document URL
        Hyperlink gitHubLink = new Hyperlink("Visit GitHub Page");
        Hyperlink websiteLink = new Hyperlink("Visit Website");
        gitHubLink.setOnAction(e -> getHostServices().showDocument("https://www.github.com/milanconhye"));
        websiteLink.setOnAction(e -> getHostServices().showDocument("https://www.linkedin.com/in/milanconhye"));

        //Set Layout and Properties
        VBox layout = new VBox();
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setSpacing(20);
        layout.setAlignment(Pos.CENTER);

        //Adding Components on screen
        layout.getChildren().addAll(appNameLabel, creatorLbl, gitHubLink, websiteLink);

        //Sets content to Tab and adds to Tab Pane
        EncryptTab.setContent(layout);
        tabPane.getTabs().add(EncryptTab);
    }
    

    //Converts Bytes to Hex
    private static String bytesToHex(byte[] hash) {
        return DatatypeConverter.printHexBinary(hash);
    }

    //Converts Hex to Bytes
    public static byte[] toByteArray(String s) {
        return DatatypeConverter.parseHexBinary(s);
    }


}
