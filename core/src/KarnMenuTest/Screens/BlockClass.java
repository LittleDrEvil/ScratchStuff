/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package KarnMenuTest.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class BlockClass {
    Vector2 vBlock;
    int nWidth=30;
       void BlockClass(Vector2 vBlock_, int nWidth_){
           vBlock = vBlock_;
           nWidth = nWidth_;
       }
       boolean SideCheck(float fX, float fDist){
           if(fX < fDist || fX + 10 > fDist) return true;
        return false;
       }
}
