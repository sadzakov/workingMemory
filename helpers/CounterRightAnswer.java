package helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;

public class CounterRightAnswer {

    private static CounterRightAnswer rightInstance = new CounterRightAnswer();

    public GameData gameData;
    private Json json = new Json();
    private FileHandle fileHandle = Gdx.files.local("bin/GameData.json");
    private int result;

    private CounterRightAnswer() {

    }

    public void initializeGameData() {
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

    public static CounterRightAnswer getRightInstance() {
        return rightInstance;
    }

    public int getResult() {
        return result++;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
