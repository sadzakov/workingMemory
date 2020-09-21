package wordpics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import helpers.GameInfo;

public class WordPics extends Sprite {

    private World world;
    private Body body;

    public WordPics(World world, float x, float y) {
        super(new Texture("wordpics/apple.jpg"));
        this.world = world;

        createBody();
    }

    void createBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set((getX() + getWidth() / 2f), (getY() + getHeight() / 2f));

        PolygonShape shape = new PolygonShape();
        shape.setAsBox((getWidth() / 2f) / GameInfo.PPM, (getHeight() / 2f) / GameInfo.PPM);
    }



}
