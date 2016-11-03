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
    int nBlockSize = 10, nW, nH;
    Vector2 vBlo;
    Sprite[] arSpri;
    float fDist;
    
    public void charMain(String sCharacter, int _nPlayer) {
        arSpri = new Sprite[10];
        for (int i = 0; i < 10; i++) {
            arSpri[i] = new Sprite();
        }
        
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
        vBlo = new Vector2();
        avB = new Vector2[nBlockSize];

        for (int i = 0; i < nBlockSize; i++) {
            avB[i] = new Vector2();
            vBlo.add(70 * i, 40 * (i + 1));
            avB[i].add(vBlo.x, vBlo.y);
            vBlo.add(-vBlo.x, -vBlo.y);
        }
        nW = 30; nH = 27;
    }

    public void update() {
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
            fSx -= 0.1;
        }
        if (fSx <= 0) {
            fSx += 0.1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                dGravity = -0.01;
            }
        if (nPlayer == 1) {
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                fSx += 0.2;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                fSx -= 0.2;
            }
            if (nJum == 0) {
                if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
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
                if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
                    fDy = 4;
                    nJum = 1;
                }
            }
        }
        // }
        
        //Stopping character at dead zones {
        
        vChar.add(fSx, fDy);
        // }
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

    boolean isHit(float nX1, float nY1, float nW1, float nH1, float nX2, float nY2, float nW2, float nH2) {

        if ((((nX1 <= nX2 + 5) && (nX1 + nW1 + 5 >= nX2))
                || ((nX1 >= nX2) && (nX1 <= nX2 + nW2)))
                && (((nY1 <= nY2) && (nY1 + nH1 >= nY2))
                || ((nY1 >= nY2) && (nY1 <= nY2 + nH2)))) {
            return true;
        } else {
            return (false);
        }
    }

    boolean isHitV(Vector2 v1, float nW1, float nH1, Vector2 v2, float nW2, float nH2) {

        if ((((v1.x <= v2.x) && (v1.x + nW1 >= v2.x))
                || ((v1.x >= v2.x) && (v1.x <= v2.x + nW2)))
                && (((v1.y <= v2.y) && (v1.y + nH1 >= v2.y))
                || ((v1.y >= v2.y) && (v1.y <= v2.y + nH2)))) {
            return true;
        } else {
            return (false);
        }
    }

    boolean isHitBlockLR(float nX1, float nY1, float nS1, float nX2, float nY2, float nS2) {

        if ((((nX1 <= nX2) && (nX1 + nS1 >= nX2))
                || ((nX1 >= nX2) && (nX1 <= nX2 + nS2)))
                && (((nY1 <= nY2 - 3) && (nY1 + nS1 >= nY2 - 3))
                || ((nY1 >= nY2 - 3) && (nY1 <= nY2 + nS2 - 3)))) {
            return true;
        }
        return false;



    }

    boolean isHitBlockT(float nX1, float nY1, float nS1, float nX2, float nY2, float nS2) {

        if ((((nX1 <= nX2 + 1) && (nX1 + nS1 >= nX2 - 1))
                || ((nX1 >= nX2 - 1) && (nX1 <= nX2 + nS2 + 1)))
                && ((nY1 >= nY2 + 5) && (nY1 <= nY2 + 5 + nS2))) {
            return true;
        }
        return false;
    }
}
