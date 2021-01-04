package com.sofija.sadzakov;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import helpers.CounterRightAnswer;
import helpers.CounterWrongAnswer;
import scenes.MainMenu;

public class GameMain extends Game {

	private SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		CounterRightAnswer.getRightInstance().initializeGameData();
		CounterWrongAnswer.getWrongInstance().initializeGameDataWrongAnswer();
		setScreen(new MainMenu(this));
	}

	@Override
	public void render () {
		super.render();
	}

	public SpriteBatch getBatch() {
		return this.batch;
	}

}
