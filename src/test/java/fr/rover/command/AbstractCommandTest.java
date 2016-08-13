package fr.rover.command;

import fr.rover.Cardinality;
import fr.rover.Rover;
import org.junit.Test;

import java.util.Random;

import static fr.rover.Cardinality.*;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


/**
 * Created by fmaury on 07/08/16.
 */
public abstract class AbstractCommandTest {

    protected Random random = new Random();
    protected Cardinality initialDirection = randomDirection();
    protected int initialX = randomPosition();
    protected int initialY = randomPosition();
    protected RoverCommand command;
    protected Rover newRoverState;


    protected void then_are_move_to(int expectedX, int expectedY) {
        assertThat(newRoverState.getX()).as("Bad value for X").isEqualTo(expectedX);
        assertThat(newRoverState.getY()).as("Bad value for Y").isEqualTo(expectedY);
    }

    protected void then_have_just_move_a_distance_of(int expectedDistance) {
        assertThat(elapsedDistance()).isEqualTo(expectedDistance);
    }

    protected int elapsedDistance() {
        return Math.abs(initialX-newRoverState.getX())+Math.abs(initialY-newRoverState.getY());
    }

    protected void then_is_on_the_same_direction() {
        assertThat(newRoverState.getDirection()).as("direction cannot change when forward").isEqualTo(initialDirection);
    }

    protected void given_a_random_rover() {
        command = getRoverCommand(initialDirection);
    }

    protected void given_a_rover(Cardinality direction) {
        command = getRoverCommand(direction);
    }

    abstract RoverCommand getRoverCommand(Cardinality direction);

    protected int randomPosition() {
        return random.nextInt(10);
    }

    protected Cardinality randomDirection() {
        return Cardinality.values()[random.nextInt(4)];
    }

}