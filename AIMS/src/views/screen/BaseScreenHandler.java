package views.screen;

import controller.BaseController;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.screen.home.HomeScreenHandler;

import java.io.IOException;
import java.util.Hashtable;

public class BaseScreenHandler extends FXMLScreenHandler {

    protected final Stage stage;
    protected HomeScreenHandler homeScreenHandler;
    protected Hashtable<String, String> messages;
    private Scene scene;
    private BaseScreenHandler prev;
    private BaseController bController;

    //không xác định coupling
    private BaseScreenHandler(String screenPath) throws IOException {
        super(screenPath);
        this.stage = new Stage();
    }

    //không xác định coupling
    public BaseScreenHandler(Stage stage, String screenPath) throws IOException {
        super(screenPath);
        this.stage = stage;
    }

    /**
     * @return BaseScreenHandler
     */
    //không xác định coupling
    public BaseScreenHandler getPreviousScreen() {
        return this.prev;
    }

    /**
     * @param prev
     */
    //Data Coupling
    public void setPreviousScreen(BaseScreenHandler prev) {
        this.prev = prev;
    }

    //Control Coupling
    public void show() {
        if (this.scene == null) {
            this.scene = new Scene(this.content);
        }
        this.stage.setScene(this.scene);
        this.stage.show();
    }


    /**
     * @param string
     */
    //Data Coupling
    public void setScreenTitle(String string) {
        this.stage.setTitle(string);
    }

    /**
     * @return BaseController
     */
    //không xác định coupling
    public BaseController getBController() {
        return this.bController;
    }

    /**
     * @param bController
     */
    //Data Coupling
    public void setBController(BaseController bController) {
        this.bController = bController;
    }

    /**
     * @param messages
     */
    //Data Coupling
    public void forward(Hashtable messages) {
        this.messages = messages;
    }


    /**
     * @param HomeScreenHandler
     */
    //Data Coupling
    public void setHomeScreenHandler(HomeScreenHandler HomeScreenHandler) {
        this.homeScreenHandler = HomeScreenHandler;
    }

}
