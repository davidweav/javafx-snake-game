package cs1302.game;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

/**
 * An {@code HBox} containing the player's score and a {@code Button}
 * to allow the player to start a new {@code SnakeGame}.
 */
public class EndGame extends HBox {

    Button startAgain; // button to allow the user to reinitialize the SnakeGame
    Label score; // current score of the player

    /**
     * Constructs a new {@code EndGame} HBox containing a {@code VBox}.
     * The {@code VBox} contains a {@code Button} allowing the player to
     * start a new {@code SnakeGame} and displays the player's score when
     * they lost.
     */
    public EndGame() {
        VBox box = new VBox(50);
        // set alignment of HBox and VBox
        this.setMinWidth(1280);
        this.setMinHeight(720);
        box.setMinWidth(1280);
        box.setMinHeight(720);
        box.setAlignment(Pos.CENTER);
        this.setAlignment(Pos.CENTER);
        // set background color
        this.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"),
                CornerRadii.EMPTY, Insets.EMPTY)));
        // create button & label and set alignment
        startAgain = new Button("Start again");
        startAgain.setMaxSize(500, 300);
        startAgain.setFont(Font.font("Lucida Sans Unicode", FontWeight.BOLD, 20));
        startAgain.setBackground(new Background(new BackgroundFill(Paint.valueOf("orange"),
                CornerRadii.EMPTY, Insets.EMPTY)));
        startAgain.setEffect(new DropShadow());
        score = new Label("Score: " + 0);
        score.setTextAlignment(TextAlignment.CENTER);
        score.setFont(Font.font("Lucida Sans Unicode", FontWeight.BOLD, 50));
        score.setMaxSize(270, 200);
        // add button and label to the VBox
        box.getChildren().addAll(startAgain, score);
        // place VBox in this EndGame HBox
        this.getChildren().add(box);
    } // EndGame

    /**
     * Updates the score that will be displayed when the player loses.
     *
     * @param score the specified value which the score will be updated to
     */
    public void update(int score) {
        this.score.setText("Score: " + score);
    } // update

    /**
     * Used to reinitialize the {@code SnakeGame} when the {@code startAgain}
     * Button is clicked by the player.
     *
     * @param run the specified lambda to be run
     */
    public void onStartAgain(EventHandler run) {
        startAgain.setOnMouseClicked(run);
    } // onStartAgain

} // EndGame