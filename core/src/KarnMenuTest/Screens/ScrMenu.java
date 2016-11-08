package KarnMenuTest.Screens;

import Buttons.TbsMenu;
import Buttons.TbMenu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

import java.awt.Font;

/**
 * Created by Luke on 2016-04-05.
 */

public class ScrMenu implements Screen, InputProcessor {
    GdxMenu gdxMenu;
    TbsMenu tbsMenu;
    TbMenu tbPlay, tbGameover;
    Stage stage;
    SpriteBatch batch;
    BitmapFont screenName;
    Texture imgBack;
    public ScrMenu(GdxMenu _gdxMenu) {  //Referencing the main class.
        gdxMenu = _gdxMenu;
    }

//    public ScrMenu(GdxMenu aThis) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    public void show() {
        stage = new Stage();
        tbsMenu = new TbsMenu();
        batch = new SpriteBatch();
        screenName = new BitmapFont();
        tbPlay = new TbMenu("PLAY", tbsMenu);
        tbGameover = new TbMenu("BACK", tbsMenu);
        imgBack = new Texture(Gdx.files.internal("SonicStart.png"));
        tbGameover.setY(0);
        tbGameover.setX(0);
        tbPlay.setY(30);
        tbPlay.setX(220);
        stage.addActor(tbPlay);
        stage.addActor(tbGameover);
        Gdx.input.setInputProcessor(stage);
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }
//        btnPlayListener();
//        btnGameoverListener();
    }

    public void render(float delta) {
        batch.begin();
        batch.draw(imgBack, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            gdxMenu.currentState = gdxMenu.gameState.PLAY;
            gdxMenu.updateState();
        }
        if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT)){
            gdxMenu.currentState = gdxMenu.gameState.OVER;
            gdxMenu.updateState();
        }
        
    }

//    public void btnPlayListener() {
//        tbPlay.addListener(new ChangeListener() {
//            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
//                gdxMenu.currentState = gdxMenu.gameState.PLAY;
//                gdxMenu.updateState();
//            }
//        });
//    }
//
//    public void btnGameoverListener() {
//        tbGameover.addListener(new ChangeListener() {
//            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
//                gdxMenu.currentState = gdxMenu.gameState.OVER;
//                gdxMenu.updateState();
//            }
//        });
//    }
    
    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
    
}