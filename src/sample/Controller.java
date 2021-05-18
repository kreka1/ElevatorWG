package sample;

import javafx.animation.FillTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    private Elevator A, B;
    private ArrayList<Circle> arrowsA, arrowsB;
    int level = -1;
    @FXML
    private Rectangle elevA, elevB; // Elevators
    @FXML
    private Button btUPfive, btUPfour, btUPthree, btUPtwo, btUPone, btUPzero; // Buttons up on levels
    @FXML
    private Button btDWsix, btDWfive, btDWfour, btDWthree, btDWtwo, btDWone, btnGo; // Buttons down on levels
    @FXML
    private Circle arUPAfive, arUPAfour, arUPAthree, arUPAtwo, arUPAone, arUPAzero, arDWAsix, arDWAfive, arDWAfour, arDWAthree, arDWAtwo, arDWAone; //arrows for elevA on each level
    @FXML
    private Circle arUPBfive, arUPBfour, arUPBthree, arUPBtwo, arUPBone, arUPBzero, arDWBsix, arDWBfive, arDWBfour, arDWBthree, arDWBtwo, arDWBone; //arrows for elevB on each level
    @FXML
    private ComboBox levelCombo;

    public void initialize() {
        A = new Elevator( "A", 0, 0, 6 );
        B = new Elevator( "B", 6, 0, 6 );
        arrowsA = new ArrayList<>();
        arrowsA.add( arUPAzero );
        arrowsA.add( arDWAone );
        arrowsA.add( arUPAone );
        arrowsA.add( arDWAtwo );
        arrowsA.add( arUPAtwo );
        arrowsA.add( arDWAthree );
        arrowsA.add( arUPAthree );
        arrowsA.add( arDWAfour );
        arrowsA.add( arUPAfour );
        arrowsA.add( arDWAfive );
        arrowsA.add( arUPAfive );
        arrowsA.add( arDWAsix );
        arrowsB = new ArrayList<>();
        arrowsB.add( arUPBzero );
        arrowsB.add( arDWBone );
        arrowsB.add( arUPBone );
        arrowsB.add( arDWBtwo );
        arrowsB.add( arUPBtwo );
        arrowsB.add( arDWBthree );
        arrowsB.add( arUPBthree );
        arrowsB.add( arDWBfour );
        arrowsB.add( arUPBfour );
        arrowsB.add( arDWBfive );
        arrowsB.add( arUPBfive );
        arrowsB.add( arDWBsix );

     /*   EventHandler<MouseEvent> goBtn = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                btnGo.setDisable( true );
                level = Integer.parseInt( (String) levelCombo.getValue() );
                choosingElevator( A, B, level );
            }
        };
        btnGo.addEventFilter( MouseEvent.MOUSE_CLICKED, goBtn );
*/
        EventHandler<MouseEvent> eventsix = e -> choosingElevator( A, B, 6 );
        btDWsix.addEventFilter( MouseEvent.MOUSE_CLICKED, eventsix );

        EventHandler<MouseEvent> eventfive = e -> choosingElevator( A, B, 5 );
        btDWfive.addEventFilter( MouseEvent.MOUSE_CLICKED, eventfive );
        btUPfive.addEventFilter( MouseEvent.MOUSE_CLICKED, eventfive );

        EventHandler<MouseEvent> eventfour = e -> choosingElevator( A, B, 4 );
        btDWfour.addEventFilter( MouseEvent.MOUSE_CLICKED, eventfour );
        btUPfour.addEventFilter( MouseEvent.MOUSE_CLICKED, eventfour );

        EventHandler<MouseEvent> eventthree = e -> choosingElevator( A, B, 3 );
        btDWthree.addEventFilter( MouseEvent.MOUSE_CLICKED, eventthree );
        btUPthree.addEventFilter( MouseEvent.MOUSE_CLICKED, eventthree );

        EventHandler<MouseEvent> eventtwo = e -> choosingElevator( A, B, 2 );
        btDWtwo.addEventFilter( MouseEvent.MOUSE_CLICKED, eventtwo );
        btUPtwo.addEventFilter( MouseEvent.MOUSE_CLICKED, eventtwo );

        EventHandler<MouseEvent> eventone = e -> choosingElevator( A, B, 1 );
        btDWone.addEventFilter( MouseEvent.MOUSE_CLICKED, eventone );
        btUPone.addEventFilter( MouseEvent.MOUSE_CLICKED, eventone );

        EventHandler<MouseEvent> eventzero = e -> choosingElevator( A, B, 0 );
        btUPzero.addEventFilter( MouseEvent.MOUSE_CLICKED, eventzero );
    }


    public void choosingElevator(Elevator A, Elevator B, int callingLevel) {
        int distA, distB, levelA, levelB;
        if ( callingLevel < A.getMinLevel() || callingLevel > A.getMaxLevel() || callingLevel < B.getMinLevel() || callingLevel > B.getMaxLevel() ) {
            System.out.println( "Wrong elevator call. Inexistent level: " + callingLevel );
            System.out.println();
            return;
        }
        levelA = A.getCurrentLevel();
        levelB = B.getCurrentLevel();

        if ( levelA == callingLevel ) {
            B.printAvailable();
            move( A, callingLevel, elevA ); //move with A, A at same level
        } else if ( levelB == callingLevel ) {
            A.printAvailable();
            move( B, callingLevel, elevB ); //move with B, B at same level
        } else {
            distA = Math.abs( callingLevel - levelA );
            distB = Math.abs( callingLevel - levelB );
            if ( distA == distB ) {
                if ( levelA < levelB ) {
                    B.printAvailable();
                    move( A, callingLevel, elevA );//same distance, A in lower level, A moves
                } else {
                    A.printAvailable();
                    move( B, callingLevel, elevB ); //same distance, B in lower level, B moves
                }
            } else if ( distA > distB ) {
                A.printAvailable();
                move( B, callingLevel, elevB ); // B closest, B moves
            } else {
                B.printAvailable();
                move( A, callingLevel, elevA ); // A closest, A moves
            }
        }
    }

    // Moving methods
    public void upToCall(Elevator el, int callingLevel, Rectangle rect) {
        int i, j;
        //Creating the translation transformation
        Translate translate = new Translate();
        FillTransition ft = new FillTransition();
        //Setting the Y coordinate to apply the translation
        translate.setY( -50 );
        for (i = el.getCurrentLevel(); i < callingLevel; i++) {
            System.out.println( "Level " + i + ": elevator " + el.getName() + " ↑ " );
            rect.getTransforms().addAll( translate );
            j = 2 * i;
            if ( el.getName().equals( "A" ) ) {
                ft.setShape( arrowsA.get( j ) );
            } else {
                ft.setShape( arrowsB.get( j ) );
            }
            ft.setToValue( Color.GOLD );
            ft.play();
            try {
                Thread.sleep( 1000 ); // Wait for 1 sec
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if ( i == callingLevel ) { // The elevator is on calling level
            if ( el.getName().equals( "A" ) ) {
                for (int f = 0; f < arrowsA.size(); f += 2) {
                    ft.setShape( arrowsA.get( f ) );
                    ft.setToValue( Color.WHITE );
                    ft.play();
                }
            } else {
                for (int f = 0; f < arrowsB.size(); f += 2) {
                    ft.setShape( arrowsA.get( f ) );
                    ft.setToValue( Color.WHITE );
                    ft.play();
                }
            }
            if ( el.getCurrentLevel() != callingLevel ) {
                System.out.println( el.getName() + " stopped at level " + i );
                el.setCurrentLevel( callingLevel );
            }
        }
    }

    public void downToCall(Elevator el, int callingLevel, Rectangle rect) {
        int i, j;
        //Creating the translation transformation
        Translate translate = new Translate();
        FillTransition ft = new FillTransition();
        //Setting the Y coordinate to apply the translation
        translate.setY( 50 );
        for (i = el.getCurrentLevel(); i > callingLevel; i--) {
            System.out.println( "Level " + i + ": elevator " + el.getName() + " ↓ " );
            rect.getTransforms().addAll( translate );
            j = 2 * i - 1;
            if ( el.getName().equals( "A" ) ) {
                ft.setShape( arrowsA.get( j ) );
            } else {
                ft.setShape( arrowsB.get( j ) );
            }
            ft.setToValue( Color.GOLD );
            ft.play();
            try {
                Thread.sleep( 500 ); // Wait for 1/2 sec
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if ( i == callingLevel ) { // The elevator is on calling level
            if ( el.getName().equals( "A" ) ) {
                for (int f = 1; f < arrowsA.size(); f += 2) {
                    ft.setShape( arrowsA.get( f ) );
                    ft.setToValue( Color.GREY );
                    ft.play();
                }
            } else {
                for (int f = 1; f < arrowsB.size(); f += 2) {
                    ft.setShape( arrowsB.get( f ) );
                    ft.setToValue( Color.GREY );
                    ft.play();
                }
            }
            if ( el.getCurrentLevel() != callingLevel ) {
                System.out.println( el.getName() + " stopped at level " + i );
                el.setCurrentLevel( callingLevel );
            }
        }
    }

    public void move(Elevator el, int callingLevel, Rectangle rect) {
        Scanner in = new Scanner( System.in );

        el.setAvailable( false ); // The elevator is in motion
        paintAvailable( el, rect );
        System.out.println( el.getName() + " in motion from level " + el.getCurrentLevel() );
        el.printAvailable();
        // Elevator going to calling level
        if ( el.getCurrentLevel() < callingLevel ) {
            upToCall( el, callingLevel, rect );
        } else {
            downToCall( el, callingLevel, rect );
        }


        System.out.println( "Please choose the level console and hit enter " );


        btnGo.setDisable( false );
        boolean isnotInt = false;

        System.out.println( "Please choose the level by entering a number between " + el.getMinLevel() + " and " + el.getMaxLevel() );
        try {
            String levell = in.nextLine(); // Getting the destination level
            level = Integer.parseInt( levell );
        } catch (Exception e) {
            System.out.println( "Inadequate input." );
            level = callingLevel;
            isnotInt = true;
        }

        while (isnotInt || level > el.getMaxLevel() || level < el.getMinLevel()) { // Levels between min and max accepted
            try {
                System.out.println( "Please choose a level between " + el.getMinLevel() +
                        " and " + el.getMaxLevel() );
                level = in.nextInt();
                isnotInt = false;
            } catch (Exception e) {
                System.out.println( "Inadequate input." );
                level = callingLevel;
                isnotInt = true;
                in.next();
            }

        }
        System.out.println( el.getName() + " in motion" );

        if ( level < callingLevel ) {// Elevator going down
            downToCall( el, level, rect );
        } else { // Elevator going up
            upToCall( el, level, rect );
        }

        System.out.println( el.getName() + " reached level " + level );
        el.setAvailable( true );
        el.printAvailable();
        System.out.println( el.getName() + " available" );
        System.out.println();


    }

    void paintAvailable(Elevator el, Rectangle rect) {
        if ( el.isAvailable() ) {
            rect.setFill( Color.DODGERBLUE );
        } else {
            rect.setFill( Color.BLACK );
        }
        rect.getScene().getWindow().setWidth( rect.getScene().getWidth() + 0.001 );
    }

}
