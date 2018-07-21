package maxlich.game;

import maxlich.game.controller.Controller;
import maxlich.game.model.Model;
import maxlich.game.view.View;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Game {
    private View view;
    private Controller controller;
    private Model model;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringBeans.xml");
        //Game game = context.getBean(Game.class);
    }


  /*  public Game(View view, Controller controller, Model model) {
        this.view = view;
        this.controller = controller;
        this.model = model;
    }*/
}
