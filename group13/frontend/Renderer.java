package group13.frontend;

import group13.backend.Field;
import group13.backend.Snake;
import group13.backend.Tile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.FileInputStream;
import java.util.List;

public class Renderer {
    public static Image mouseImage;
    public static void render(Field field, GraphicsContext gc) throws Exception {
        // First render the field, paint the background black
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0, field.getWidth(), field.getHeight());

        // Render the border
        gc.setFill(Color.CORNSILK);
        field.getBorder().forEach(tile -> renderBorderTile(tile, gc));

        // Render bottom bar
        gc.setFill(Color.BLACK);
        gc.fillRect(0, field.getHeight(), field.getWidth(), 50);

       /* // render the food
        gc.setFill(Color.PINK);
        paintTile(field.getMouseTile(), gc);*/

        //setting the image for the mouse
        renderMouseImageTile(field, field.getMouseTile(), gc);

        //render the snake
        Snake snake = field.getSnake();
        gc.setFill(Color.WHITE);
        snake.getSnakeBody().forEach(tile -> renderTile(tile, gc));

        gc.fillText("Score: " + field.getTotalScore(), 9, field.getHeight() + 35);
        gc.setFont(Font.font("Impact", FontWeight.BOLD, 25));
    }

    private static void renderTile(Tile tile, GraphicsContext gc) {
        gc.fillRoundRect(tile.getX(), tile.getY(), 25, 25, 15, 25);
       // gc.fillRect(tile.getX(), tile.getY(), 25, 25);
    }

    private static void renderBorderTile(Tile tile, GraphicsContext gc) {
        gc.fillRect(tile.getX(), tile.getY(), 25, 25);
    }
    private static void renderMouseImageTile(Field field, Tile tile, GraphicsContext gc) throws Exception {
        // First checks if the image for the mouse has been loaded or not
        if (mouseImage == null) {
            // If not, loads the image
            FileInputStream inputstream = new FileInputStream("group13/frontend/assets/mouse.png");
            mouseImage = new Image(inputstream, 30, 30, false, false);
            inputstream.close();
        }
        // Renders the image
        gc.drawImage(mouseImage, field.getMouseTile().getX(), field.getMouseTile().getY());
    }
}