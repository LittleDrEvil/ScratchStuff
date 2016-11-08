package KarnMenuTest.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class CharClass extends Sprite {
    int nPlayer;
    Vector2 vChar = new Vector2();
    Vector2 vFloor = new Vector2();
    Sprite sprChar;
    public Animation aniChar[] = new Animation[3];
    TextureAtlas[] artextureAtlas;
    Texture[] texture;
    int nDir = 0, nJum;
    float x, y = 60, fDy, fSY, fSX, fBX = 50, fBY = 50, fSx;
    double dSpeed, dGravity, dCharSpeed;
    Vector2[] avB;
    int nBlockSize = 10, nW, nH, nWhatSprite = 0, nTime = 0;
    Vector2 vBlo;
    Sprite[] arSpri;
    float fDist;
    Array<Sprite> arSprites;
    boolean bHit;
    HitTestClass hitTest = new HitTestClass();
    
    public void charMain(String sCharacter, int _nPlayer) {
        artextureAtlas  = new TextureAtlas[3];
        nPlayer = _nPlayer;
        vFloor.nor();
        vChar.add(x, y);
        /* Flips when directions are pressed, 
        0 is for StillRight,
        1 is for StillLeft,
        2 is for RunLeft,
        3 is for RunRight,
        4 is for JumpLeft,
        5 is for JumpRight,
        */
        artextureAtlas[0] = new TextureAtlas(Gdx.files.internal(sCharacter + "StillRight.pack"));
        aniChar[0] = new Animation(1 / 15f, artextureAtlas[0].getRegions());
        artextureAtlas[1] = new TextureAtlas(Gdx.files.internal(sCharacter + "Run2.atlas"));
        aniChar[1] = new Animation(1 / 30f, artextureAtlas[1].getRegions());
        artextureAtlas[2] = new TextureAtlas(Gdx.files.internal(sCharacter + "JumpRight.pack"));
        aniChar[2] = new Animation(1 / 15f, artextureAtlas[2].getRegions());
    }

    public void update() {
        nTime++;
        
        if(nDir == 0 || nDir == 1)
            arSprites = artextureAtlas[0].createSprites();
        if(nDir == 2 || nDir == 3)
            arSprites = artextureAtlas[1].createSprites();
        if(nDir == 4 || nDir == 5)
            arSprites = artextureAtlas[2].createSprites();
        
        if(fDy != 0){
            arSprites = artextureAtlas[2].createSprites();
        }
        
        sprChar = spro(arSprites);
        //        Gravity and Movement {
        fDist+=fSx;
        dCharSpeed = 0.2;
        fSY = vChar.y;
        fSX = vChar.x;
        dSpeed += dGravity;
        fDy += dSpeed;
        
        if (fSx < 0.1 && fSx > -0.1) {
            fSx = 0;
        }

        if (fSx >= 0) {
            fSx -= dCharSpeed/2;
        }
        if (fSx <= 0) {
            fSx += dCharSpeed/2;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                dGravity = -0.01;
            }
        if (nPlayer == 1) {
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                fSx += dCharSpeed;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                fSx -= dCharSpeed;
            }
            if (nJum == 0) {
                if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                    fDy = 4;
                    nJum = 1;
                    dGravity = -0.01;
                    System.out.println("up");
                }
            }
        }
        if (nPlayer == 2) {
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                fSx += dCharSpeed;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                fSx -= dCharSpeed;
            }
            if (nJum == 0) {
                if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                    fDy = 4;
                    nJum = 1;
                }
            }
        }
        // }
        
        

//        System.out.println(fDy);
        vChar.add(fSx, fDy);
        sprChar.setX(vChar.x);
        sprChar.setY(vChar.y);
    }

    Sprite spro(Array<Sprite> arSprites){
        Sprite spro = new Sprite();
        spro.setX(vChar.x);
        spro.setY(vChar.y);
        
        nWhatSprite = ArrayAt(nTime, nWhatSprite, arSprites.size);
        
        spro = (arSprites.get(nWhatSprite));
        
        
        if (touchDown()) {
            dSpeed = 0;
            fDy = 0;
            vChar.y = -Gdx.input.getY() + Gdx.graphics.getHeight() - spro.getHeight()/2;
            vChar.x = Gdx.input.getX() - spro.getWidth()/2;
            fDist = Gdx.input.getX();
        }   
        
        return spro;
    }
    
    public boolean touchDown () {
      if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
          return true;     
      }
      return false;
   }
    
    int ArrayAt(int elapsedTime, int prev, int nSize){
        if(elapsedTime % 3 == 0){
//            System.out.println(prev + "true");
            prev +=1;
        }
//        System.out.println(prev);
        
        if(prev>=nSize) prev = 0;
//        System.out.println(prev);
        return prev;
    }
    
    int Direction() {
        if (nDir == 2) {
            nDir = 1;
        }
        if (nDir == 3) {
            nDir = 0;
        }
        if (nPlayer == 1) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                nDir = 2;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                nDir = 3;
            }
        }
        if (nPlayer == 2) {
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                nDir = 2;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                nDir = 3;
            }
        }
        if (nJum == 0) {
            return nDir;
        }

        if (nJum == 1) {
            if (nDir == 2 || nDir == 1) {
                return 4;
            }
            if (nDir == 3 || nDir == 0) {
                return 5;
            }
        }
        return nDir;
    }

   
}
