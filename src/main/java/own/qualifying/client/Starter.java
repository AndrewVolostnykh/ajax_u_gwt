package own.qualifying.client;

import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyCodes;
import own.qualifying.shared.QuickSort;

import java.util.Random;

public class Starter implements EntryPoint {

  //useless
  //private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

  private Integer[] savedButtons;
  private QuickSort sorting;

  public void onModuleLoad() {

    final Button sendButton = new Button("Send");
    final TextBox numbersField = new TextBox();
    final Label question = new Label("How many numbers to display");

    final Button sort = new Button("Sort");
    final Button reset = new Button("Reset");

    numbersField.addKeyPressHandler(event -> { // only numbers checker
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
    RootPanel.get("question").add(question);

    numbersField.setFocus(true);
    numbersField.selectAll();

    //Event listener for click on send button
    sendButton.addClickHandler(event -> {

      Random random = new Random();
      String inputedText = numbersField.getText(); // this works!!
      int numberOfButtons = Integer.parseInt(inputedText);
      savedButtons = new Integer[numberOfButtons];


      if (inputedText.isEmpty()) { // check for empty string
        return;
      }

      RootPanel.get("nameFieldContainer").clear();
      RootPanel.get("sendButtonContainer").clear();
      RootPanel.get("question").clear();

      // so , after click to sort button we have to delete old buttons
      // and create new with same numbers but in correct order

      int tempRandom;
      for (int i = 0; i < numberOfButtons; i++) {
        tempRandom = random.nextInt(1000);
        savedButtons[i] = tempRandom;
        RootPanel.get("nameFieldContainer").add(new Button("" + savedButtons[i]));
      }

      RootPanel.get("functionality").add(reset);
      RootPanel.get("functionality").add(sort);
    });

    reset.addClickHandler(event -> Window.Location.reload());

    sort.addClickHandler(event -> {
      sorting = new QuickSort(savedButtons);
      savedButtons = sorting.getResultArray();

      RootPanel.get("nameFieldContainer").clear();
      RootPanel.get("question").clear();

      for(int i : savedButtons){
        RootPanel.get("nameFieldContainer").add(new Button("" + i));
      }
    });
  }

}
