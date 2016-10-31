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
import java.util.logging.Level;
import java.util.logging.Logger;

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
    boolean bLeft;
    int nBlockSize=10;
    Array<Sprite> arSprites;
    BlockClass bBlocks[];
    ArrayList<BlockClass> alBlocks;
    int nAd=0, nAdd=40;
    
    
    
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
        
        for (int i = 0; i < nBlockSize; i++) {
            bBlocks[i] = new BlockClass();
            avB[i] = new Vector2();
            vBlo.add(30*i, 40);
            avB[i].add(vBlo.x, vBlo.y);
            bBlocks[i].vBlock = avB[i];
            vBlo.add(-vBlo.x, -vBlo.y);
        }

    }

    @Override
    public void render(float delta) {
        arSprites = charSonic.artextureAtlas[nDir].createSprites();
        charSonic.x = charSonic.vChar.x;
        charSonic.y = charSonic.vChar.y;

        batch.begin();
        
        elapsedTime += Gdx.graphics.getDeltaTime();
        
        if((fBackX < -Gdx.graphics.getWidth() || fBackX > Gdx.graphics.getWidth())){
            fBackX=0;
        }

        
        batch.draw(imgBack, fBackX, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(imgBack, fBackX-Gdx.graphics.getWidth(), 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(imgBack, fBackX+Gdx.graphics.getWidth(), 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        
        
//        batch.draw(imgFloor, fBackX, 0, Gdx.graphics.getWidth(), 40);
//        batch.draw(imgFloor, fBackX-Gdx.graphics.getWidth(), 0, Gdx.graphics.getWidth(), 40);
//        batch.draw(imgFloor, fBackX+Gdx.graphics.getWidth(), 0, Gdx.graphics.getWidth(), 40);
//        batch.disableBlending();

        
        nDir = charSonic.Direction();
        
        charSonic.update();
        
//        batch.draw(charSonic.aniChar[nDir].getKeyFrame(elapsedTime, true), charSonic.x, charSonic.y);
        if(charSonic.fDist > 0) {
            fBackX -= charSonic.fSx;
        } else if (charSonic.fDist<0) {
            charSonic.fSx = 0;
            charSonic.fDist = 0;
            fBackX = 0;
        }
        
        for (int i = 0; i < nBlockSize; i++) {
            if(bBlocks[i].SideCheck(bBlocks[i].vBlock.x, fDist)){
            batch.draw(imgBlock, bBlocks[i].vBlock.x - charSonic.fDist, bBlocks[i].vBlock.y, 30, 30);
//            charSonic = hitTest.HitTest(charSonic, bBlocks[i].vBlock, charSonic.fDist);
            }
        }
        
        System.out.println(charSonic.y);
        System.out.println(charSonic.fDy);
        Sprite spr[] = new Sprite[arSprites.size];
        for (int j = 0; j < arSprites.size; j++) {
            
            spr[j] = (arSprites.get(j));
            
//            spr[j].setV(charSonic.vChar);
                for (int i = 0; i < nBlockSize; i++) {
                    if(hitTest.isHitSB(spr[j], bBlocks[i], charSonic.fDist)){
                        charSonic.dSpeed = 0;
                        charSonic.nJum = 0;
                        charSonic.dGravity = 0;
                        charSonic.vChar.y = bBlocks[i].vBlock.y + 32;
                        charSonic.fDy = 0;
                    }// else charSonic.dGravity = -0.01;
                    
                    if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
                        charSonic.fDy = 0;
                        charSonic.vChar.y = -Gdx.input.getY() + Gdx.graphics.getHeight();
                        charSonic.vChar.x = Gdx.input.getX();
                    }
                spr[j].setX(charSonic.vChar.x);
                spr[j].setY(charSonic.vChar.y);
                
                spr[j].draw(batch);
//                charSonic.dGravity = -0.01;
                System.out.println(Gdx.input.getY());
            }
//            System.out.println(arSprites.size);
//            spr.setX(charSonic.vChar.x);
//            spr.setY(charSonic.vChar.y);
//            if(nDir == 1 || nDir == 2 || nDir == 4){
//            spr.flip(false,true);
//            }
//            Rectangle rect = spr.getBoundingRectangle();
//            if(hitTest.isHit(bBlocks[i].vBlock.x, bBlocks[i].vBlock.y, 30,30 , charSonic.vChar.x, charSonic.vChar.y, rect.width, rect.height)){ 
//            }
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