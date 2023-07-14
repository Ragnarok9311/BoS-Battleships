package battleships.core;

import battleships.display.Display;
import battleships.logging.Logger;
import battleships.player.Player;
import battleships.player.ships.Ship;
import battleships.util.Position;
import jserver.XSendAdapterEN;

import javax.swing.*;

public class Game {

    private static final Logger LOGGER = Logger.getInstance();
    private static final String TAG = Game.class.getSimpleName();

    private final Player player;
    private final Player AI;
    private Position hitPositionByAi;
    private boolean isRunning;
    private boolean isPlayerTurn;

    public Game(BattleshipsBoard battleshipsBoard) {
        LOGGER.log("Initialize game", TAG);
        this.isRunning = true;
        this.isPlayerTurn = true;
        this.player = new Player("Player");
        this.AI = new Player();
        new Display(this, new XSendAdapterEN());
    }

    public void update(Position clickedPosition) {
        if (this.isPlayerTurn) this.checkShipIsHit(clickedPosition, this.AI);
        if (!this.isPlayerTurn) this.executeAiTurn();
        this.checkGameOver();
    }

    private void checkShipIsHit(Position clickedPosition, Player player) {
        Ship selectedShip = null;
        Position selectedPosition = null;
        label:
        for (Ship ship : player.getShips()) {
            for (Position shipPosition : ship.getPositions()) {
                if (clickedPosition.equals(shipPosition)) {
                    selectedShip = ship;
                    selectedPosition = shipPosition;
                    System.out.println(player.getName() + " ship hit!");
                    break label;
                }
            }
        }

        if (selectedShip != null) {
            selectedShip.removePosition(selectedPosition);
            if (selectedShip.isDestroyed()) {
                player.removeShip(selectedShip);
            }
            return;
        }
        this.isPlayerTurn = false;
    }

    private void executeAiTurn() {
        int index = (int) (Math.random() * BattleshipsBoard.AI_BOARD.size());
        this.hitPositionByAi = BattleshipsBoard.AI_BOARD.get(index);
        BattleshipsBoard.AI_BOARD.remove(this.hitPositionByAi);
        this.checkShipIsHit(this.hitPositionByAi, this.player);
        this.isPlayerTurn = true;
    }

    private void checkGameOver() {
        if (this.player.hasLost()) {
            JOptionPane.showMessageDialog(null, "AI wins", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            this.isRunning = false;
        } else if (this.AI.hasLost()) {
            JOptionPane.showMessageDialog(null, "Player wins", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            this.isRunning = false;
        }
    }

    public Player getPlayer() {
        return player;
    }

    public Player getAI() {
        return AI;
    }

    public Position getHitPositionByAi() {
        return hitPositionByAi;
    }

    public boolean isRunning() {
        return isRunning;
    }
}