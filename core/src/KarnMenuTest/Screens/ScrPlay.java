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
import KarnMenuTest.Screens.BlockClass;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import java.util.ArrayList;

/**
 * Created by Luke on 2016-04-05.
 */
public class ScrPlay implements Screen, InputProcessor {
    HitTestClass hitTest = new HitTestClass();
    Vector2 vBlo;
    Vector2[] avB;
    GdxMenu gdxMenu;
    TbsMenu tbsMenu;
    TbMenu tbMenu, tbGameover;
    Stage stage;
    SpriteBatch batch;
    BitmapFont screenName;
    CharClass charSonic = new CharClass();
    CharClass charSon = new CharClass();
    Animation[] aniChar;
    private float elapsedTime = 0;
    Texture imgFloor, imgBack, imgBlock;
    int nJum, nDir = 0, nAniCurr;
    float fSY, fSX, fBX=50, fBY=50, fX, fY, fBackX, fDist;
    double dGravity, dSpeed;
    Vector2 vSonic;
    boolean bPass=false;
    int nBlockSize=10;
    Array<Sprite> arSprites;
    BlockClass bBlocks[];
    ArrayList<BlockClass> alBlocks;
    public ScrPlay(GdxMenu _gdxMenu) { 
//Referencing the main class.
        gdxMenu = _gdxMenu;
    }

    public void show() {
        bBlocks = new BlockClass[nBlockSize];
        avB = new Vector2[nBlockSize];
        batch = new SpriteBatch();
        imgBack = new Texture(Gdx.files.internal("background.png"));
        imgFloor = new Texture(Gdx.files.internal("background1.png"));
        imgBlock = new Texture(Gdx.files.internal("block.png"));
        charSonic.charMain("Sonic", 1);
        charSon.charMain("Sonic", 2);
        stage = new Stage();
        tbsMenu = new TbsMenu();
        tbMenu = new TbMenu("BACK", tbsMenu);
        tbGameover = new TbMenu("GAMEOVER", tbsMenu);
        tbMenu.setY(0);
        tbMenu.setX(0);
        tbGameover.setY(0);
        tbGameover.setX(440);
        stage.addActor(tbMenu);
        stage.addActor(tbGameover);
        Gdx.input.setInputProcessor(stage);
        btnMenuListener();
        btnGameoverListener();
        vBlo = new Vector2();
        
        
//        for (int i = 0; i < nBlockSize/4; i++) {
//            
//            avB[i] = new Vector2();
//            bBlock[i] = new BlockClass();
//            
//            vBlo.add(30*(i), 50);
//            avB[i].add(vBlo.x, vBlo.y);
//            bBlock[i].BlockClass();
//            vBlo.add(-vBlo.x, -vBlo.y);
//            alBlocks.add(bBlock[i]);
//        }
        for (int i = 0; i < nBlockSize; i++) {
            bBlocks[i] = new BlockClass();
            avB[i] = new Vector2();
            vBlo.add(100*i, 50);
            avB[i].add(vBlo.x, vBlo.y);
            bBlocks[i].BlockClass(avB[i]);
            vBlo.add(-vBlo.x, -vBlo.y);
        }
//        for (int i = nBlockSize/2; i < nBlockSize; i++) {
//            avB[i] = new Vector2();
//            
//            vBlo.add(30*(i-nBlockSize/2), 200);
//            
//            avB[i].add(vBlo.x, vBlo.y);
//            vBlo.add(-vBlo.x, -vBlo.y);
//        }
    }

    @Override
    public void render(float delta) {
        arSprites = charSonic.artextureAtlas[nDir].createSprites();
        batch.begin();
        elapsedTime += Gdx.graphics.getDeltaTime();
        
        if((fBackX < -Gdx.graphics.getWidth() || fBackX > Gdx.graphics.getWidth())){
            fBackX=0;
        }
        batch.disableBlending();
        
        batch.draw(imgBack, fBackX, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(imgBack, fBackX-Gdx.graphics.getWidth(), 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(imgBack, fBackX+Gdx.graphics.getWidth(), 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(imgFloor, fBackX, 0, Gdx.graphics.getWidth(), 40);
        batch.draw(imgFloor, fBackX-Gdx.graphics.getWidth(), 0, Gdx.graphics.getWidth(), 40);
        batch.draw(imgFloor, fBackX+Gdx.graphics.getWidth(), 0, Gdx.graphics.getWidth(), 40);
        
        
        
        
        nDir = charSonic.Direction();charSonic.dGravity = -0.01;
        charSonic.update();
        
        for (int i = 0; i < nBlockSize; i++) {
            charSonic = hitTest.HitTest(charSonic, bBlocks[i].vBlock, charSonic.fDist);
        }
        batch.draw(charSonic.aniChar[nDir].getKeyFrame
                (elapsedTime, true), charSonic.vChar.x, charSonic.vChar.y);
        if(charSonic.fDist > 0) {
            fBackX -= charSonic.fSx;
        } else if (charSonic.fDist<0) {
            charSonic.fSx = 0;
            charSonic.fDist = 0;
            fBackX = 0;
        }
        System.out.println(charSonic.vChar.y);
        
        for (int i = 0; i < nBlockSize; i++) {
            batch.draw(imgBlock, bBlocks[i].vBlock.x - charSonic.fDist, bBlocks[i].vBlock.y, 30, 30);
        }
        batch.end();
        
            
        
    }

    public void btnGameoverListener() {
        tbGameover.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                gdxMenu.currentState = gdxMenu.gameState.OVER;
                gdxMenu.updateState();
            }
        });
    }

    public void btnMenuListener() {
        tbMenu.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                gdxMenu.currentState = gdxMenu.gameState.MENU;
                gdxMenu.updateState();
            }
        });
    }

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