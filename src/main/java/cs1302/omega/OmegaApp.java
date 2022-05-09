package cs1302.omega;

import cs1302.game.Snake;
import cs1302.game.SnakeGame;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * REPLACE WITH NON-SHOUTING DESCRIPTION OF YOUR APP.
 */
public class OmegaApp extends Application {

    Button startButton;

    /**
     * Constructs an {@code OmegaApp} object. This default (i.e., no argument)
     * constructor is executed in Step 2 of the JavaFX Application Life-Cycle.
     */
    public OmegaApp() {

    }

    /** {@inheritDoc} */
    @Override
    public void start(Stage stage) {
        // some labels to display information
        this.startButton = new Button("Start Game!");
        Label title = new Label("Snake");
        Label name = new Label("Created by David Weaver");
        Font font = new Font(50);
        title.setFont(font);
        name.setFont(font);
        startButton.setFont(font);
        // setup start screen
        VBox root = new VBox(title, name, startButton);
        root.setMinSize(1280, 720);
        root.setAlignment(Pos.CENTER);
        root.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"),
                CornerRadii.EMPTY, Insets.EMPTY)));
        startButton.setBackground(new Background(new BackgroundFill(Paint.valueOf("orange"),
                CornerRadii.EMPTY, Insets.EMPTY)));
        startButton.setEffect(new DropShadow());
        Scene startScreen = new Scene(root);
        // setup stage
        stage.setTitle("Snake");
        stage.setScene(startScreen);
        stage.setOnCloseRequest(event -> Platform.exit());
        stage.sizeToScene();
        stage.show();

        startButton.setOnAction(e -> {
            SnakeGame game = new SnakeGame(1280, 720, 60);
            // setup game screen
            VBox view = new VBox(game);
            Scene gameScreen = new Scene(view);

            stage.setScene(gameScreen);
        });

    } // start

} // OmegaApp
