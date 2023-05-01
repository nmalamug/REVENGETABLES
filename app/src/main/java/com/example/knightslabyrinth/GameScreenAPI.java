package com.example.knightslabyrinth;

/**
 * The GameScreenAPI interface provides a contract for managing the game screen,
 * allowing the user to interact with the game and control various game elements.
 */
public interface GameScreenAPI {
    /**
     * Executes a game tick, which updates the game state, moves game elements, and checks for game events.
     */
    void gameTick();
}
