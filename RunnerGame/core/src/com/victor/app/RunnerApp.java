package com.victor.app;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import javafx.stage.Stage;

public class RunnerApp extends Game {

	@Override
	public void create () {

		setScreen(new PantallaInicio(true,true));
	}

	@Override
	public void render () {
		super.render();

	}
	@Override
	public void dispose() {

	}
}
