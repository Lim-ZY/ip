package mark;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

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

    private Mark mark;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/emoji2.png"));
    private Image markImage = new Image(this.getClass().getResourceAsStream("/images/emoji1.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Mark instance */
    public void setMark(Mark m) {
        mark = m;
        dialogContainer.getChildren().addAll(DialogBox.getMarkDialog(mark.getGreetingMessage(), markImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = mark.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMarkDialog(response, markImage)
        );
        userInput.clear();

        if (mark.isExit(input)) {
            Stage stage = (Stage) dialogContainer.getScene().getWindow();
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(e -> { stage.close(); });
            pause.play();
        }
    }
}
