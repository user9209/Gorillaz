package de.tu_darmstadt.gdi1.gorillas.ui.states;

import de.matthiasmann.twl.Button;
import de.matthiasmann.twl.slick.BasicTWLGameState;
import de.matthiasmann.twl.slick.RootPane;
import de.tu_darmstadt.gdi1.gorillas.main.*;
import de.tu_darmstadt.gdi1.gorillas.main.Game;
import de.tu_darmstadt.gdi1.gorillas.utils.KeyMap;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;


/**
 * TODO: PauseState should actually be a Panel
 * Try to set gc.pause(); onShow and unset on hide
 */
public class InGamePause extends BasicTWLGameState {

    private Color color = new Color(50,50,50,150);
    private Button btnNewGame;
    private Button btnExit;
    private Button btnMute;
    private Button btnMainMenu;
    private RootPane rp;
    private StateBasedGame game;

    @Override
    public int getID() {
        return Game.INGAMEPAUSE;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame game) throws SlickException {
        this.game = game;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
        if (Game.getInstance().isTestMode()) { return; } // Don't draw anything in testmode
        game.getState(Game.GAMEPLAYSTATE).render(gc, game, g);
        g.setColor(color);
        g.fillRect(0, 0, Gorillas.FRAME_WIDTH, Gorillas.FRAME_HEIGHT);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int i) throws SlickException {
        KeyMap.globalKeyPressedActions(container.getInput(), game);
    }

    @Override
    protected void layoutRootPane() {
        int paneHeight = this.getRootPane().getHeight();
        int paneWidth = this.getRootPane().getWidth();

        btnNewGame.setSize(128, 32);
        btnNewGame.setPosition((Gorillas.FRAME_WIDTH - btnNewGame.getWidth()) / 2, 150);

        btnExit.setSize(128, 32);
        btnExit.setPosition((Gorillas.FRAME_WIDTH - btnExit.getWidth()) / 2, 225);

        btnMute.setSize(64, 64);
        btnMute.setPosition(0, paneHeight - btnMute.getHeight());

        btnMainMenu.setSize(128,32);
        btnMainMenu.setPosition((Gorillas.FRAME_WIDTH - btnMainMenu.getWidth()) / 2, 300);
    }

    @Override
    protected RootPane createRootPane() {
        rp = super.createRootPane();

        btnNewGame = new Button("New Game");
        btnNewGame.addCallback(() -> game.enterState(Game.GAMESETUPSTATE) );

        btnExit = new Button("Exit Game");
        btnExit.addCallback(() -> Game.getInstance().exitGame() );

        btnMute = new Button("Mute");
        btnMute.addCallback(() -> Game.getInstance().toggleMute() );

        btnMainMenu = new Button("Main Menu");
        btnMainMenu.addCallback(() -> game.enterState(Game.MAINMENUSTATE) );

        rp.add(btnNewGame);
        rp.add(btnExit);
        rp.add(btnMute);
        rp.add(btnMainMenu);

        return rp;
    }

}
