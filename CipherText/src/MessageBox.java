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
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MessageBox {

    //Display Requested Information
    public static void display(String title, String message) {

        //Initialize Stage
        Stage window = new Stage();

        //Block interaction with another windows, until this window is taken care of.
        window.initModality(Modality.APPLICATION_MODAL);

        //Set Title and Minimum Width
        window.setTitle("CipherText: " + title);
        window.setMinWidth(250);

        //Initialize Label
        Label label1 = new Label(message);

        //Initialize Button and Action
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());

        //Initialize Layout with Padding
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));

        //Adding Components on to screen and Position them to be centered
        layout.getChildren().addAll(label1, closeButton);
        layout.setAlignment(Pos.CENTER);

        //Initialize Scene with Layout
        Scene scene = new Scene(layout);

        //Add Layout to Scene
        scene.getStylesheets().add("Design.css");

        //Set Scene and Properties
        window.setScene(scene);
        window.setResizable(false);

        //Display the window, before it returns, it needs to be closed.
        window.showAndWait();

    }

}