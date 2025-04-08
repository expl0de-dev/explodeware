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
    // Перемещаем переменную шрифта на уровень класса
    private Font minecraftFont;

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        root.setPrefSize(600, 400);

        // Установка градиентного фона через CSS
        root.setStyle("-fx-background-color: linear-gradient(to right, red, black);");

        // Инициализация шрифта один раз
        try {
            minecraftFont = Font.loadFont(getClass().getResourceAsStream("/minecraft.ttf"), 36);
            if (minecraftFont == null) {
                minecraftFont = Font.font("Arial", 36);
            }
        } catch (Exception e) {
            minecraftFont = Font.font("Arial", 36);
        }

        // Заголовок
        Label title = new Label("Expl0de");
        title.setFont(minecraftFont);
        title.setTextFill(Color.WHITE);
        title.setLayoutX(230); // Более точное центрирование
        title.setLayoutY(80);
        root.getChildren().add(title);

        // Кнопка "Играть"
        Button playButton = new Button("Играть");
        playButton.setFont(Font.font(minecraftFont.getFamily(), 16));
        playButton.setStyle(
                "-fx-background-color: #4CAF50; " +
                        "-fx-text-fill: white; " +
                        "-fx-padding: 10 20 10 20;"
        );
        playButton.setLayoutX(275); // Центрирование
        playButton.setLayoutY(180);
        playButton.setOnAction(e -> launchGame());
        root.getChildren().add(playButton);

        // Копирайт
        Label copyright = new Label("All copyrights reserved ⓒ");
        copyright.setFont(Font.font(minecraftFont.getFamily(), 10));
        copyright.setTextFill(Color.WHITE);
        copyright.setLayoutX(230);
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
            // Открытие авторизации Microsoft
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(
                        "https://login.live.com/oauth20_authorize.srf?" +
                                "client_id=00000000402b5328" +
                                "&response_type=code" +
                                "&scope=XboxLive.signin%20offline_access" +
                                "&redirect_uri=https://login.live.com/oauth20_desktop.srf"
                ));
            }

            // Примечание: Реальный запуск Minecraft требует установленного официального лаунчера
            // и корректных путей к файлам
            ProcessBuilder pb = new ProcessBuilder(
                    "java",
                    "-jar",
                    "minecraft_launcher.jar",
                    "--version",
                    "1.21"
            );
            pb.inheritIO(); // Для отображения вывода
            pb.start();

        } catch (Exception e) {
            // Добавим базовую обработку ошибок
            System.err.println("Ошибка при запуске игры: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}