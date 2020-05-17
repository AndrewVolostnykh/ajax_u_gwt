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

import java.util.Random;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Starter implements EntryPoint {
  private static final String SERVER_ERROR = "An error occurred while "
      + "attempting to contact the server. Please check your network "
      + "connection and try again.";

  private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

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

        if(inputedText.isEmpty()){ // check for empty string
          return;
        }

        RootPanel.get("nameFieldContainer").clear();
        RootPanel.get("sendButtonContainer").clear();

        for(int i = 0; i < numberOfButtons; i++){
          RootPanel.get("nameFieldContainer").add(new Button("" + random.nextInt(1000)));
        }
        
        Button reset = new Button("reset");
        reset.addClickHandler(ev -> Window.Location.reload());
        RootPanel.get("functionality").add(reset);
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
