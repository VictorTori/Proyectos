package com.victor.app;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;

public class PantallaJuego implements Screen {

    SpriteBatch batch;
    Personaje personaje;
    Enemigos enemigo;
    BitmapFont font;
    Texture pantalla;
    Texture barra;
    Texture aura;
    Sound disparo;
    Sound yunque;
    Sound cancion;
    Sound toque;
    boolean volumen;
    boolean habilidad;
    int fase;
    long tiempoFire;
    long tiempoBlue;
    long ritmoBlue;
    private long ritmoFuego;

    PantallaJuego(boolean volumen, boolean habilidad){
        this.volumen = volumen;
        this.habilidad = habilidad;
    }

    @Override
    public void show() {

        batch = new SpriteBatch();
        personaje = new Personaje(10,0);
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        enemigo = new Enemigos();
        pantalla = new Texture("fondo.jpg");
        barra = new Texture("barra4.png");
        disparo = Gdx.audio.newSound(Gdx.files.internal("disparo.mp3"));
        toque = Gdx.audio.newSound(Gdx.files.internal("toque.mp3"));
        yunque = Gdx.audio.newSound(Gdx.files.internal("yunque.mp3"));
        cancion = Gdx.audio.newSound(Gdx.files.internal("cancion.mp3"));
        aura = new Texture("aura.png");

        if(volumen)
        cancion.loop();

        fase = 1;
        ritmoFuego = 6000;
        ritmoBlue = 8000;
        tiempoFire = TimeUtils.millis();
        tiempoBlue = TimeUtils.millis();
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        personaje.update(Gdx.graphics.getDeltaTime());
        saltoPersonaje();
        armaPersonaje();

        generarFuego();
        if(enemigo.fuego){
            enemigo.moveFire();
        }

        generarBlue();
        if(personaje.blue){
            personaje.moveBlue();
        }
        if(enemigo.puntuacion > 1000 && enemigo.puntuacion < 2000 && fase == 1){
            enemigo.velocidadFuego = 10;
            enemigo.velocidadRino = 4;
            personaje.velocidadAmmo = 7;
            ritmoFuego = 2000;
            personaje.vidas++;
            fase = 2;
        }

        if(enemigo.puntuacion > 2000 && fase == 2){
            enemigo.velocidadFuego = 10;
            enemigo.velocidadRino = 6;
            personaje.velocidadAmmo = 10;
            ritmoFuego = 3000;
            personaje.vidas++;
            fase = 3;
        }

        colisiones();

        batch.begin();
        if(personaje.vidas > 0) {
            batch.draw(pantalla,0,0);
            barraHabilidad(personaje.habilidad);
            generarAura();
            personaje.render(batch,habilidad);
            enemigo.render(batch);
            enemigo.moveRino();
            font.draw(batch, "PUNTUACION: " + enemigo.puntuacion, 10, 460);
            font.draw(batch, "VIDAS:" + personaje.vidas, 10, 440);
            font.draw(batch, "MUNICION:" + personaje.ammo, 10, 420);
            font.draw(batch, "FASE " + fase, 300, 460);
            personaje.moveMunicion();
            if (personaje.disparo)
                personaje.moveShot(batch);
        } else {
            cancion.stop();
            ((Game) Gdx.app.getApplicationListener()).setScreen(new PantallaFinal(enemigo.puntuacion,volumen,habilidad)
            );
            dispose();
        }
        batch.end();
    }

    private void generarAura() {
        if(personaje.aura == true) {
            batch.draw(aura, personaje.posicion.x - 10, personaje.posicion.y);
        }
    }

    private void barraHabilidad(int habilidad) {

        switch(habilidad){
            case 0:
                break;
            case 1:
                barra = new Texture("barra1.png");
                batch.draw(barra,250,190);
                break;
            case 2:
                barra = new Texture("barra2.png");
                batch.draw(barra,250,190);
                break;
            case 3:
                barra = new Texture("barra3.png");
                batch.draw(barra,250,190);
                break;
            case 4:
                barra = new Texture("barra4.png");
                batch.draw(barra,250,190);
                personaje.aura = true;
                break;
            default:
                batch.draw(barra,250,190);
        }

    }

    private void colisiones() {
        if(personaje.shotRec.overlaps(enemigo.rinoRec)){
            enemigo.posicionRino.x = Gdx.graphics.getWidth();
            personaje.disparo = false;
            personaje.tiro = -30;
            personaje.shotRec.x = personaje.tiro;
            enemigo.puntuacion = enemigo.puntuacion + 100;
            yunque.play();
        }

        if(personaje.personaje.overlaps(personaje.municionRec)){
            personaje.posicionMun.x = Gdx.graphics.getWidth();
            personaje.municionRec.x = personaje.posicionMun.x;
            personaje.ammo++;
        }

        if(personaje.personaje.overlaps(enemigo.rinoRec)){
            enemigo.posicionRino.x = Gdx.graphics.getWidth();
            personaje.shotRec.x = personaje.tiro;
            if(personaje.aura == false) {
                personaje.vidas--;
                toque.play();
            } else {
                personaje.aura = false;
                personaje.habilidad = 0;
            }
        }

        if(personaje.personaje.overlaps(enemigo.fireRec)){
            enemigo.posicionFire.x = Gdx.graphics.getWidth();
            enemigo.fireRec.x = enemigo.posicionFire.x;
            if(personaje.aura == false) {
                personaje.vidas--;
                toque.play();
            } else {
                personaje.aura = false;
                personaje.habilidad = 0;
            }
        }

        if(personaje.personaje.overlaps(personaje.blueFireRec)){
            personaje.posicionBlue.x = Gdx.graphics.getWidth();
            personaje.blueFireRec.x = personaje.posicionBlue.x;
            personaje.habilidad++;
            personaje.blue = false;
        }
    }

    private void armaPersonaje() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER) && personaje.posicion.y == 0 && personaje.ammo > 0) {
            if(personaje.tiro < -29) {
                personaje.accion = 2;
                personaje.disparo = true;
                personaje.ammo--;
                disparo.play();
            }
        }
    }

    private void saltoPersonaje() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
            personaje.accion = 1;

        if(personaje.accion == 1)
            personaje.jump();
    }

    private void generarFuego(){
        if((TimeUtils.timeSinceMillis(tiempoFire)) > ritmoFuego){
            enemigo.fuego = true;
            tiempoFire = TimeUtils.millis();
        }
    }



    private void generarBlue(){
        if((TimeUtils.timeSinceMillis(tiempoBlue)) > ritmoBlue){
            personaje.blue = true;
            tiempoBlue = TimeUtils.millis();
        }
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
        batch.dispose();
    }
}
