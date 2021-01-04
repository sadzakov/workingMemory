package helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;

public class CounterWrongAnswer {

    private static CounterWrongAnswer wrongInstance = new CounterWrongAnswer();

    public GameData gameData;
    private Json json = new Json();
    private FileHandle fileHandle = Gdx.files.local("bin/GameDataWrongAnswer.json");
    private int result;

    private CounterWrongAnswer() {

    }

    public void initializeGameDataWrongAnswer() {
        if (!fileHandle.exists()) {
            gameData = new GameData();
            gameData.setScore(0);
            saveData();
        } else {
            loadData();
        }
    }

    public void saveData() {
        if (gameData != null) {
            fileHandle.writeString(Base64Coder.encodeString(json.prettyPrint(gameData)), false);
        }
    }

    public void loadData() {
        gameData = json.fromJson(GameData.class, Base64Coder.decodeString(fileHandle.readString()));
    }

    public static CounterWrongAnswer getWrongInstance() {
        return wrongInstance;
    }

    public int getResult() {
        return result++;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
