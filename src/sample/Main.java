package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

import static javafx.application.Platform.exit;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load( Objects.requireNonNull( getClass().getResource( "sample.fxml" ) ) );
        primaryStage.setTitle( "Elevator" );
        primaryStage.setScene( new Scene( root, 640, 500 ) );
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch( args );
        Elevator elevA = new Elevator( "A", 0, 0, 6 );
        Elevator elevB = new Elevator( "B", 6, 0, 6 );
        System.out.println( elevA.toString() );
        System.out.println( elevB.toString() );
        System.out.println();


    }
}
