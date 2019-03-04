package com.victor.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.sun.org.apache.xerces.internal.impl.dv.xs.AnyURIDV;

public class Personaje {

    public Vector2 posicionBlue;
    public Animation animationRun;
    public Animation animationJump;
    public Animation animationShot;

    public TextureRegion textureRegion;
    public Texture shot;
    public Texture municion;
    public Rectangle municionRec;
    public Rectangle shotRec;
    public Rectangle personaje;
    public Texture blueFire;
    public Rectangle blueFireRec;

    public Vector2 posicion;
    public Vector2 posicionMun;

    public float tiro = 0;
    public float stateTime;
    public boolean disparo;
    public int velocidadSalto = 5;
    public int vidas;
    public int accion = 0;
    public int ammo;
    public int velocidadAmmo;
    public int habilidad;
    public int velocidadBlue = 5;
    public boolean blue;
    public boolean aura;

    public Personaje(float x, float y){

        blue = false;
        ammo = 0;
        habilidad = 0;
        tiro = -30;
        vidas = 3;

        posicion = new Vector2(x,y);
        posicionMun = new Vector2(Gdx.graphics.getWidth(),0);
        posicionBlue = new Vector2(Gdx.graphics.getWidth(),100);

        shot = new Texture("disparoFinal.png");
        municion = new Texture("disparoFinal.png");
        blueFire = new Texture("bluefire.png");

        municionRec = new Rectangle(posicionMun.x,posicion.y,40,40);
        shotRec = new Rectangle(tiro,13,64,64);
        personaje = new Rectangle(x,y,34,64);
        blueFireRec = new Rectangle(posicionBlue.x,posicionBlue.y,40,40);

        velocidadAmmo = 5;
        crearAnimacion();
    }

    public void render(SpriteBatch batch,boolean habilidad){

        batch.draw(textureRegion,posicion.x,posicion.y);
        batch.draw(municion,posicionMun.x,posicionMun.y);
        if(habilidad)
            batch.draw(blueFire,posicionBlue.x,posicionBlue.y);
    }

    public void jump(){
        posicion.y = posicion.y + velocidadSalto;
        personaje.y = posicion.y;

        if(posicion.y > 80) {
            velocidadSalto = -4;
        }
        if(posicion.y < 0){
            accion = 0;
            velocidadSalto = 4;
            posicion.y = 0;
        }
    }

    public void moveShot(SpriteBatch batch) {
        tiro = tiro + 7;
        shotRec.x = tiro;
        batch.draw(shot,(posicion.x + tiro + 64),13);
        if((posicion.x + tiro) > Gdx.graphics.getWidth()){
            disparo = false;
            tiro = -30;
        }
    }

    public void moveMunicion(){
        posicionMun.x = posicionMun.x - velocidadAmmo;
        municionRec.x = posicionMun.x;
        if(posicionMun.x < -municion.getWidth()){
            posicionMun.x = Gdx.graphics.getWidth();
            municionRec.x = posicionMun.x;
        }
    }

    public void moveBlue(){
        posicionBlue.x = posicionBlue.x - velocidadBlue;
        blueFireRec.x = posicionBlue.x;
        if(posicionBlue.x < -blueFire.getWidth()){
            posicionBlue.x = Gdx.graphics.getWidth();
            blueFireRec.x = posicionBlue.x;
            blue = false;
        }
    }

    public void update(float dt) {

        // Calcula el tiempo para cargar el frame que proceda de la animación
        stateTime += dt;

        switch(accion){
            case 0:
                textureRegion = (TextureRegion) animationRun.getKeyFrame(stateTime, true);
                break;
            case 1:
                textureRegion = (TextureRegion) animationJump.getKeyFrame(stateTime, true);
                break;
            case 2:
                textureRegion = (TextureRegion) animationShot.getKeyFrame(stateTime, true);
                break;
        }
    }

    private void crearAnimacion() {

        animationRun = new Animation(0.08f,new TextureRegion[]{
                new Sprite(new Texture("run1.PNG")),
                new Sprite(new Texture("run2.PNG")),
                new Sprite(new Texture("run3.PNG")),
                new Sprite(new Texture("run4.PNG")),
                new Sprite(new Texture("run5.PNG")),
                new Sprite(new Texture("run6.PNG")),
                new Sprite(new Texture("run7.PNG")),
                new Sprite(new Texture("run8.PNG")),
                new Sprite(new Texture("run9.PNG")),
                new Sprite(new Texture("run10.PNG")),
        });

        animationJump = new Animation(0.15f,new TextureRegion[]{
                new Sprite(new Texture("jump1.PNG")),
                new Sprite(new Texture("jump2.PNG")),
                new Sprite(new Texture("jump3.PNG")),
                new Sprite(new Texture("jump4.PNG")),
                new Sprite(new Texture("jump5.PNG")),
                new Sprite(new Texture("jump6.PNG")),
                new Sprite(new Texture("jump7.PNG")),
                new Sprite(new Texture("jump8.PNG")),
                new Sprite(new Texture("jump9.PNG")),
        });

        animationShot = new Animation(0.08f,new TextureRegion[]{
                new Sprite(new Texture("runshot1.PNG")),
                new Sprite(new Texture("runshot2.PNG")),
                new Sprite(new Texture("runshot3.PNG")),
                new Sprite(new Texture("runshot4.PNG")),
                new Sprite(new Texture("runshot5.PNG")),
                new Sprite(new Texture("runshot6.PNG")),
                new Sprite(new Texture("runshot7.PNG")),
                new Sprite(new Texture("runshot8.PNG")),
                new Sprite(new Texture("runshot9.PNG")),
                new Sprite(new Texture("runshot10.PNG")),
        });
    }
}
