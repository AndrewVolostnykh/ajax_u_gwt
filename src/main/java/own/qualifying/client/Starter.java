package own.qualifying.client;

import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import own.qualifying.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.ArrayList;
import java.util.Random;

public class Starter implements EntryPoint {

  //useless
  private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
  private Integer[] savedButtons;

  public void onModuleLoad() {

    final Button sendButton = new Button("Send");
    final TextBox numbersField = new TextBox();

    numbersField.addKeyPressHandler(event -> {
      if(event.getNativeEvent().getKeyCode()!=KeyCodes.KEY_DELETE &&
              event.getNativeEvent().getKeyCode()!=KeyCodes.KEY_BACKSPACE &&
              event.getNativeEvent().getKeyCode()!=KeyCodes.KEY_LEFT &&
              event.getNativeEvent().getKeyCode()!=KeyCodes.KEY_RIGHT){
        String c = event.getCharCode()+"";
        if(RegExp.compile("[^0-9]").test(c))
          numbersField.cancelKey();
      }
    });

    sendButton.addStyleName("sendButton");

    RootPanel.get("nameFieldContainer").add(numbersField);
    RootPanel.get("sendButtonContainer").add(sendButton);

    numbersField.setFocus(true);
    numbersField.selectAll();


    // Create a handler for the sendButton and numbersField
    class MyHandler implements ClickHandler, KeyUpHandler {
      /**
       * Fired when the user clicks on the sendButton.
      */
      public void onClick(ClickEvent event) {

        Random random = new Random();
        String inputedText = numbersField.getText(); // this works!!
        int numberOfButtons = Integer.parseInt(inputedText);
        savedButtons = new Integer[numberOfButtons];


        if(inputedText.isEmpty()){ // check for empty string
          return;
        }

        RootPanel.get("nameFieldContainer").clear();
        RootPanel.get("sendButtonContainer").clear();

        // so , after click to sort button we have to delete old buttons and create new with same numbers but in correct order

        int tempRandom;
        for(int i = 0; i < numberOfButtons; i++){
          tempRandom = random.nextInt(1000);
          savedButtons[i] = tempRandom;
          RootPanel.get("nameFieldContainer").add(new Button("" + savedButtons[i]));
        }

        Button reset = new Button("reset");
        reset.addClickHandler(ev -> Window.Location.reload());
        Button sort = new Button("Sort");
        sort.addClickHandler(e -> {}); // function to sort element and display that in correct order
        RootPanel.get("functionality").add(reset);
        RootPanel.get("functionality").add(sort);
      }

      // useless
      public void onKeyUp(KeyUpEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {

        }
      }
    }

    // Add a handler to send the name to the server
    MyHandler handler = new MyHandler();
    sendButton.addClickHandler(handler);
    numbersField.addKeyUpHandler(handler);

  }

}
