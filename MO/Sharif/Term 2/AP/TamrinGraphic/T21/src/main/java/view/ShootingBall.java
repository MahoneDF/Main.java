package model;

import controller.GameController;
import javafx.animation.Transition;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class ShootingBall extends Transition {
    private Pane pane;

    private LittleCircle littleCircle;

    private ProgressBar freeze;

    private MapGame mapGame;

    public ShootingBall(Pane pane, LittleCircle littleCircle, MapGame mapGame, ProgressBar freeze) {
        this.pane = pane;
        this.littleCircle = littleCircle;
        this.mapGame = mapGame;
        this.freeze = freeze;
        this.setCycleDuration(Duration.millis(2000));
        this.setCycleCount(-1);
    }
    @Override
    protected void interpolate(double v) {
        boolean isLoser = false;
        double y = littleCircle.getCenterY() - 10;
        for(int i = 0; i < mapGame.getOnMainCircle().size(); i++) {
            if(littleCircle.getBoundsInParent().intersects(mapGame.getOnMainCircle().get(i).getBoundsInParent())){
                this.stop();
                isLoser = true;
                GameController.lostGame(pane, mapGame);
            }
        }




        if(littleCircle.getBoundsInParent().intersects(mapGame.getInvisibleCircle().getBoundsInParent()) && !isLoser){
            this.stop();
            mapGame.getOnMainCircle().add(littleCircle);
            if(UserDatabase.getCurrentUser()!=null)
                mapGame.addScore(mapGame.getPlayer().getGameDatabase().getDifficulty()*(mapGame.getOnMainCircle().size()));
            else
                mapGame.addScore(2*(mapGame.getOnMainCircle().size()));
            littleCircle.getLine().setEndX(littleCircle.getCenterX());
            littleCircle.getLine().setEndY(littleCircle.getCenterY());
            littleCircle.getLine().setStartX(mapGame.getMainCircle().getCenterX());
            littleCircle.getLine().setStartY(mapGame.getMainCircle().getCenterY());
            if((mapGame.getOnMainCircle().size() > 0)) {
                littleCircle.setRadius(mapGame.getOnMainCircle().get(0).getRadius());
                littleCircle.setFill(mapGame.getOnMainCircle().get(0).getFill());
                littleCircle.getLine().setStroke(mapGame.getOnMainCircle().get(0).getLine().getStroke());
            }else {
                littleCircle.getLine().setStroke(Color.GREEN);
            }
            freeze.setProgress(freeze.getProgress() + 0.2);
            pane.getChildren().add(littleCircle.getLine());
            Media media = new Media(GameController.class.getResource("/hit2.mp3").toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
//            System.out.println(mapGame.getFaze() + " " + mapGame.getOnMainCircle().size() + " " + mapGame.getTotalBall());
            if((mapGame.getOnMainCircle().size()-mapGame.getGameDatabase().getMapNumber()-4) == (mapGame.getTotalBall())){
                mapGame.stopRotation();
                GameController.wonGame(pane, mapGame);
            }


        }


        if(y < 20){
            pane.getChildren().remove(littleCircle);
            pane.getChildren().remove(littleCircle.getNumber());
            this.stop();
        }
        littleCircle.setCenterY(y);
        littleCircle.getNumber().setY(y);
    }
}
