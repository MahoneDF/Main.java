package model;

import javafx.animation.Transition;
import javafx.util.Duration;

import java.util.ArrayList;

public class TwoPlayerRotation extends Transition {
    private MapGameTwoPlayers mapGame;
    private ArrayList<LittleCircle> balls;

    private double speed;

    public TwoPlayerRotation(MapGameTwoPlayers mapGame, ArrayList<LittleCircle> balls, double speed) {
        this.mapGame = mapGame;
        this.balls = balls;
        this.speed = speed;
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(1000));
    }
    @Override
    protected void interpolate(double frac) {
        for (LittleCircle ball : balls) {
            double theta;
            if(mapGame.getMainCircle().getCenterY() < ball.getCenterY()) {
                theta = (Math.atan((mapGame.getMainCircle().getCenterX() - ball.getCenterX())
                        / (ball.getCenterY() - mapGame.getMainCircle().getCenterY())));
            }
            else {
                theta = (Math.atan((mapGame.getMainCircle().getCenterX() - ball.getCenterX())
                        / (ball.getCenterY() - mapGame.getMainCircle().getCenterY()))) + 3.14159;
            }
            theta += Math.toRadians(mapGame.getAngularSpeed());
            ball.setCenterX(mapGame.getMainCircle().getCenterX() - Math.sin(theta) * (mapGame.getInvisibleCircle().getRadius()));
            ball.setCenterY(mapGame.getMainCircle().getCenterY() + Math.cos(theta) * (mapGame.getInvisibleCircle().getRadius()));
            ball.getLine().setEndX(ball.getCenterX());
            ball.getLine().setEndY(ball.getCenterY());
            ball.getNumber().setX(ball.getCenterX() - ball.getNumber().getBoundsInLocal().getWidth() / 2);
            ball.getNumber().setY(ball.getCenterY() + ball.getNumber().getBoundsInLocal().getWidth() / 2);
        }
    }
}
