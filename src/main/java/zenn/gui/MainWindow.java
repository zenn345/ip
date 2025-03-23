package zenn.gui;

import javafx.application.Platform;
import zenn.Zenn;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Zenn zenn;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/NewUser.png"));
    private Image zennImage = new Image(this.getClass().getResourceAsStream("/images/System.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Zenn Bot instance */
    public void setZenn(Zenn z) {
        zenn = z;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Zenn's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = zenn.getResponse(input);
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getZennDialog(response, zennImage)
        );
        userInput.clear();
        if (input.equals("bye")) {
            Platform.exit();
        }
    }

    /**
     * Displays a message in the welcome label.
     * @param message The message to display.
     */
    public void showMessage(String message) {
        dialogContainer.getChildren().addAll(DialogBox.getZennDialog(message, zennImage));
    }
}
