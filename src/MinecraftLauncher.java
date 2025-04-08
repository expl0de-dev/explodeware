import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.awt.Desktop;
import java.net.URI;

public class MinecraftLauncher extends Application {
    private Font minecraftFont;

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        root.setPrefSize(600, 400);

        // Линейный градиент по диагонали с уменьшенной на 10% долей черного
        root.setStyle(
                "-fx-background-color: linear-gradient(" +
                        "from 0% 0% to 100% 100%, " + // Направление от левого верхнего угла к правому нижнему
                        "black 0%, " +  // Начало - черный
                        "black 50%, " + // Черный доминирует до 50% (60% - 10%)
                        "red 100%); " + // Конец - красный
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 4, 0, 4, 4);" // Пиксельная тень для фона
        );

        // Инициализация шрифта
        try {
            minecraftFont = Font.loadFont(getClass().getResourceAsStream("/minecraft.ttf"), 36);
            if (minecraftFont == null) {
                minecraftFont = Font.font("Arial", 36);
            }
        } catch (Exception e) {
            minecraftFont = Font.font("Arial", 36);
        }

        // Заголовок с усиленным пиксельным эффектом
        Label title = new Label("Expl0de");
        title.setFont(minecraftFont);
        title.setTextFill(Color.WHITE);
        title.setStyle(
                "-fx-effect: dropshadow(gaussian, black, 4, 0.0, 4, 4);" +
                        "-fx-padding: 2;"
        );
        title.setLayoutX(230);
        title.setLayoutY(80);
        root.getChildren().add(title);

        // Кнопка "Играть" с усиленным пиксельным стилем
        Button playButton = new Button("Играть");
        playButton.setFont(Font.font(minecraftFont.getFamily(), 24));
        playButton.setStyle(
                "-fx-background-color: #4CAF50; " +
                        "-fx-text-fill: white; " +
                        "-fx-padding: 15 30 15 30; " +
                        "-fx-border-color: #3D8C40; " +
                        "-fx-border-width: 4; " +
                        "-fx-effect: dropshadow(gaussian, black, 4, 0.0, 4, 4);"
        );
        playButton.setPrefSize(150, 60);
        playButton.setLayoutX(225);
        playButton.setLayoutY(200);
        playButton.setOnAction(e -> launchGame());
        root.getChildren().add(playButton);

        // Копирайт с пиксельным стилем
        Label copyright = new Label("All copyrights reserved ⓒ");
        copyright.setFont(Font.font(minecraftFont.getFamily(), 10));
        copyright.setTextFill(Color.WHITE);
        copyright.setStyle(
                "-fx-effect: dropshadow(gaussian, black, 2, 0.0, 2, 2);"
        );
        copyright.setLayoutX((600 - 150) / 2 + 10); // Центрирование с небольшим смещением вправо
        copyright.setLayoutY(340);
        root.getChildren().add(copyright);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Expl0de Launcher");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void launchGame() {
        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(
                        "https://login.live.com/oauth20_authorize.srf?" +
                                "client_id=00000000402b5328" +
                                "&response_type=code" +
                                "&scope=XboxLive.signin%20offline_access" +
                                "&redirect_uri=https://login.live.com/oauth20_desktop.srf"
                ));
            }

            ProcessBuilder pb = new ProcessBuilder(
                    "java",
                    "-jar",
                    "minecraft_launcher.jar",
                    "--version",
                    "1.21"
            );
            pb.inheritIO();
            pb.start();

        } catch (Exception e) {
            System.err.println("Ошибка при запуске игры: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}